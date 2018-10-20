package v2.hackupc.guts2018.ciudadnube;
/**
 *  Add image, description
 *
 */

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.squareup.picasso.Picasso;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.s3.transferutility.*;

import java.io.File;

import v2.hackupc.guts2018.ciudadnube.Objects.Problem;

public class AddDetailsActivity extends AppCompatActivity {

    public Problem problem;
    private final static int SELECT_PICTURE = 0;
    private ImageView imageView;
    private EditText descriptionTextView;
    private Uri selectedImageUri;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AWSMobileClient.getInstance().initialize(this).execute();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);


        Bundle b = getIntent().getExtras();
        if(b != null)
            problem  = new Problem((Location)b.getParcelable("LOCATION"));

        imageView = findViewById(R.id.imageView);
        descriptionTextView = findViewById(R.id.description);

        int productImageId = getResources().getIdentifier(
                "ic_photo_placeholder", "drawable", getPackageName());

        Picasso.get().load(android.R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_broken_image)
                .into(imageView);

        findViewById(R.id.add_picture)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        // in onCreate or any event where your want the user to
                        // select a file
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,
                                "Select Picture"), SELECT_PICTURE);
                    }
                });

        // Initialize the Amazon Cognito credentials provider
        CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:30b87aaa-5633-4ef0-bae2-4d36c3ab731d", // Identity pool ID
                Regions.US_EAST_1 // Region
        );

        LambdaInvokerFactory factory = LambdaInvokerFactory.builder().context(getApplicationContext()).region(Regions.US_EAST_1).credentialsProvider(credentialsProvider).build();

        final MyInterface myInterface = factory.build(MyInterface.class);

        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                problem.setDescription(descriptionTextView.getText().toString());
                problem.setImagePath(selectedImageUri.getPath());

                String timeStamp = String.valueOf(System.currentTimeMillis()); // name of file and also id of database entry

//                TransferUtility transferUtility =
//                        TransferUtility.builder()
//                                .context(getApplicationContext())
//                                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
//                                .s3Client(new AmazonS3Client(AWSMobileClient.getInstance().getCredentialsProvider()))
//                                .build();
//
//                TransferObserver uploadObserver =
//                        transferUtility.upload(
//                                timeStamp + ".jpg",
//                                new File(problem.getImagePath()));
//
//                // Attach a listener to the observer to get state update and progress notifications
//                uploadObserver.setTransferListener(new TransferListener() {
//
//                    @Override
//                    public void onStateChanged(int id, TransferState state) {
//                        if (TransferState.COMPLETED == state) {
//                            // Handle a completed upload.
//                            Toast.makeText(AddDetailsActivity.this, "Upload complete", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
//                        float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
//                        int percentDone = (int)percentDonef;
//
//                        Log.d("YourActivity", "ID:" + id + " bytesCurrent: " + bytesCurrent
//                                + " bytesTotal: " + bytesTotal + " " + percentDone + "%");
//                    }
//
//                    @Override
//                    public void onError(int id, Exception ex) {
//                        // Handle errors
//                        Log.e("UploadError", "UploadError", ex);
//                    }
//
//                });

                // If you prefer to poll for the data, instead of attaching a
                // listener, check for the state and progress in the observer.
//                if (TransferState.COMPLETED == uploadObserver.getState()) {
//                    // Handle a completed upload.
//                    Toast.makeText(AddDetailsActivity.this, "Upload complete", Toast.LENGTH_LONG).show();
//                }

                Request r = new Request(problem.getLat(), problem.getLng(), problem.getDescription(), "test", "POST", timeStamp);

                new AsyncTask<Request, Void, Response>() {
                    @Override
                    protected Response doInBackground(Request... params) {
                        // invoke "echo" method. In case it fails, it will throw a
                        // LambdaFunctionException.
                        try {
                            return myInterface.SaveLocationToDB(params[0]);
                        } catch (LambdaFunctionException lfe) {
                            Log.e("Failed to invoke echo", "Failed to invoke echo", lfe);
                            return null;
                        }
                    }

                    @Override
                    protected void onPostExecute(Response result) {
                        if (result == null) {
                            Log.d("RETURN", "NO DATA RETURNED");
                            return;
                        }

                        // Do a toast
                        Toast.makeText(AddDetailsActivity.this, result.getResponse(), Toast.LENGTH_LONG).show();
                    }
                }.execute(r);
            }
        });

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddDetailsActivity.this, ReportMapActivity.class);
                startActivity(i);
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                Picasso.get().load(selectedImageUri).fit()
                        .placeholder(android.R.drawable.ic_menu_gallery)
                        .error(R.drawable.ic_broken_image)
                        .into(imageView);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.company_login:
                Intent i = new Intent(AddDetailsActivity.this, LoginActivity.class);
                startActivity(i);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
