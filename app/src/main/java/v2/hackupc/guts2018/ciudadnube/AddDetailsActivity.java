package v2.hackupc.guts2018.ciudadnube;
/**
 *  Add image, description
 *
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import v2.hackupc.guts2018.ciudadnube.Objects.Problem;

import static java.security.AccessController.getContext;

public class AddDetailsActivity extends AppCompatActivity {

    public Problem problem;
    private final static int SELECT_PICTURE = 0;
    private ImageView imageView;
    private EditText descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        Button confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                problem.setDescription(descriptionTextView.getText().toString());

                // send stuff to amazon boi
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
                Uri selectedImageUri = data.getData();
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
