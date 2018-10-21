package v2.hackupc.guts2018.ciudadnube;
/*
* check details of problem
* */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;
import com.squareup.picasso.Picasso;

import v2.hackupc.guts2018.ciudadnube.Lambda.MyInterface;
import v2.hackupc.guts2018.ciudadnube.Lambda.SolveIssueRequest;
import v2.hackupc.guts2018.ciudadnube.Lambda.SolveIssueResponse;
import v2.hackupc.guts2018.ciudadnube.Objects.Problem;

public class DetailsActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        if(b != null){
            final Problem problem = (Problem)b.getSerializable("PROBLEM");
            ImageView imageView = findViewById(R.id.imageView2);
            TextView descriptionTv = findViewById(R.id.description);
            Button solved = findViewById(R.id.solved);

            Picasso.get().load(problem.getUrl()).fit().centerCrop()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(R.drawable.ic_broken_image)
                    .into(imageView);

            descriptionTv.setText(problem.getDescription());

            solved.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                            getApplicationContext(),
                            "us-east-1:30b87aaa-5633-4ef0-bae2-4d36c3ab731d", // Identity pool ID
                            Regions.US_EAST_1 // Region
                    );

                    LambdaInvokerFactory factory = LambdaInvokerFactory.builder().context(getApplicationContext()).region(Regions.US_EAST_1).credentialsProvider(credentialsProvider).build();

                    final MyInterface myInterface = factory.build(MyInterface.class);

                    SolveIssueRequest r = new SolveIssueRequest(problem.getLat(), problem.getLng(), problem.getDescription(), problem.getUrl(), problem.getUrl().substring(0, problem.getUrl().length() - 4));
                    
                    new AsyncTask<SolveIssueRequest, Void, SolveIssueResponse>() {
                        @Override
                        protected SolveIssueResponse doInBackground(SolveIssueRequest... params) {
                            // invoke "echo" method. In case it fails, it will throw a
                            // LambdaFunctionException.
                            try {
                                return myInterface.SolveIssue(params[0]);
                            } catch (LambdaFunctionException lfe) {
                                Log.e("Failed to invoke echo", "Failed to invoke echo", lfe);
                                return null;
                            }
                        }

                        @Override
                        protected void onPostExecute(SolveIssueResponse result) {
                            if (result == null) {
                                Log.d("RETURN", "NO DATA RETURNED");
                                return;
                            }

                            // Do a toast
                            Toast.makeText(DetailsActivity.this, "Issue solved", Toast.LENGTH_LONG).show();
                            // go back to previous activity
                            Intent i = new Intent(DetailsActivity.this, ProblemsViewActivity.class);
                            startActivity(i);
                        }
                    }.execute(r);
                }
            });

        } else {
            Log.d("mytag", "No problem was passed down");
        }



    }

}
