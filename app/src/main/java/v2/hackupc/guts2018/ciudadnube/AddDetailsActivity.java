package v2.hackupc.guts2018.ciudadnube;
/**
 *  Add image, description
 *
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import v2.hackupc.guts2018.ciudadnube.Objects.Problem;

public class AddDetailsActivity extends AppCompatActivity {

    public Problem problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);


        Bundle b = getIntent().getExtras();
        if(b != null)
            problem  = (Problem)b.getSerializable("PROBLEM");

    }
}
