package v2.hackupc.guts2018.ciudadnube;
/*
* Map view of problems
* */

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;

import v2.hackupc.guts2018.ciudadnube.Objects.Problem;


public class ProblemsMapActivity extends FragmentActivity implements MapFragment.OnFragmentInteractionListener,
        ProblemFragment.OnListFragmentInteractionListener {

    private GoogleMap mMap;
    private boolean showingMap = true;
    private static final String MAP_FRAGMENT = "map";
    private static final String PROBLEM_FRAGMENT = "problem";
    FrameLayout fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problems_map);

        fragmentContainer = findViewById(R.id.fragment_container);


        final ArrayList<Problem> problems = new ArrayList<>();

        MapFragment mapFragment = MapFragment.newInstance(problems);
        openFragment(mapFragment, MAP_FRAGMENT);

        Button toggleMap = findViewById(R.id.toolbar_button);

        toggleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showingMap){
                    Fragment problemFragment = getSupportFragmentManager().findFragmentByTag(PROBLEM_FRAGMENT);
                    if(problemFragment == null){
                        problemFragment = ProblemFragment.newInstance(1, problems);
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

    }
}
