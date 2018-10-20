package v2.hackupc.guts2018.ciudadnube;
/*
* Map view of problems
* */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.Arrays;

import v2.hackupc.guts2018.ciudadnube.Objects.Problem;


public class ProblemsViewActivity extends FragmentActivity implements MapFragment.OnFragmentInteractionListener,
        ProblemListFragment.OnListFragmentInteractionListener {

    private GoogleMap mMap;
    private boolean showingMap = true;
    private static final String MAP_FRAGMENT = "map";
    private static final String PROBLEM_FRAGMENT = "problem";
    FrameLayout fragmentContainer;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems_view);

        fragmentContainer = findViewById(R.id.fragment_container);


        // Download data and show it
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:30b87aaa-5633-4ef0-bae2-4d36c3ab731d", // Identity pool ID
                Regions.US_EAST_1 // Region
        );

        LambdaInvokerFactory factory = LambdaInvokerFactory.builder().context(getApplicationContext()).region(Regions.US_EAST_1).credentialsProvider(credentialsProvider).build();

        final MyInterface myInterface = factory.build(MyInterface.class);

        AllDataRequest dataRequest = new AllDataRequest();
        final ArrayList<Problem> problems = new ArrayList<>();

//        Request r = new Request(problem.getLat(), problem.getLng(), problem.getDescription(), "test", "POST", timeStamp);

        new AsyncTask<AllDataRequest, Void, AllDataResponse>() {
            @Override
            protected AllDataResponse doInBackground(AllDataRequest... params) {
                // invoke "echo" method. In case it fails, it will throw a
                // LambdaFunctionException.
                try {
                    return myInterface.GetAllData(params[0]);
                } catch (LambdaFunctionException lfe) {
                    Log.e("Failed to invoke echo", "Failed to invoke echo", lfe);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(AllDataResponse result) {
                if (result == null) {
                    Log.d("RETURN", "NO DATA RETURNED");
                    return;
                }
                for (Object response: result.getResponse()){
                    response = (AllDataResponse) response;
                    Log.d("response", response.toString());
                    problems.add(new Problem(new Location("")));
                }
                // Do a toast
                Toast.makeText(ProblemsViewActivity.this, Arrays.toString(result.getResponse().toArray()), Toast.LENGTH_LONG).show();
            }
        }.execute(dataRequest);


        // TODO remove dummy data




//        for(int i = 0; i<12;i++){
//            Location dummyLoc = new Location("");
//            dummyLoc.setLatitude(0.0d +i*0.0001);//your coords of course
//            dummyLoc.setLongitude(0.0d+i*0.001);
//
//            Problem problem = new Problem(dummyLoc);
//            problem.setDescription("DUMMMY DEEEEEEEEEEEEESCRIPTIONNNNNNNNNNNNNNNNNNNNNNN");
//            problem.setImageUrl("https://a0.awsstatic.com/libra-css/images/logos/aws_logo_smile_1200x630.png");
//            problems.add(problem);
//        }
//
//        for(int i = 0; i<40;i++){
//            Location dummyLoc2 = new Location("");
//            dummyLoc2.setLatitude(41.3918234d+i*0.003);//your coords of course
//            dummyLoc2.setLongitude(2.1155787d-i*0.0004);
//            Problem problem = new Problem(dummyLoc2);
//            problem.setDescription("DUMMMY DEEEEEEEEEEEEESCRIPTIONNNNNNNNNNNNNNNNNNNNNNN");
//            problem.setImageUrl("https://a0.awsstatic.com/libra-css/images/logos/aws_logo_smile_1200x630.png");
//            problems.add(problem);
//        }
        // TODO----------------------


        MapFragment mapFragment = MapFragment.newInstance(problems);
        openFragment(mapFragment, MAP_FRAGMENT);

        Button toggleMap = findViewById(R.id.toolbar_button);

        toggleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showingMap){
                    Fragment problemFragment = getSupportFragmentManager().findFragmentByTag(PROBLEM_FRAGMENT);
                    if(problemFragment == null){
                        problemFragment = ProblemListFragment.newInstance(1, problems);
                    }
                    openFragment(problemFragment, PROBLEM_FRAGMENT);
                    showingMap = false;
                } else {
                    Fragment mapFragment = getSupportFragmentManager().findFragmentByTag(MAP_FRAGMENT);
                    if(mapFragment == null){
                        mapFragment = MapFragment.newInstance(problems);
                    }
                    openFragment(mapFragment, MAP_FRAGMENT);
                    showingMap = true;
                }
            }
        });

    }

    private void openFragment(Fragment fragment, String TAG) {

        // Create new transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(fragmentContainer.getId(), fragment, TAG);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_buttons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.company_login:
                // toggleview
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(Problem item) {
        Intent i = new Intent(ProblemsViewActivity.this, DetailsActivity.class);
        i.putExtra("PROBLEM", item);
        startActivity(i);
    }
}
