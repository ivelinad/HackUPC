package v2.hackupc.guts2018.ciudadnube;
/*
* check details of problem
* */
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import v2.hackupc.guts2018.ciudadnube.Objects.Problem;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        if(b != null){
            Problem problem = (Problem)b.getSerializable("PROBLEM");
            ImageView imageView = findViewById(R.id.imageView2);
            TextView descriptionTv = findViewById(R.id.description);
            Button solved = findViewById(R.id.solved);

            Picasso.get().load(problem.getImageUrl()).fit().centerCrop()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .error(R.drawable.ic_broken_image)
                    .into(imageView);

            descriptionTv.setText(problem.getDescription());


        } else {
            Log.d("mytag", "No problem was passed down");
        }



    }

}
