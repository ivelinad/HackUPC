package v2.hackupc.guts2018.ciudadnube;
/*
* Company login
* */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final TextInputEditText emailTv = findViewById(R.id.email);
        final TextInputEditText passwordTv = findViewById(R.id.password);

        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailTv.length() > 0){
                    String email = emailTv.getText().toString();
                    if(passwordTv.length() > 0){
                        String password = passwordTv.getText().toString();

                        if(email.equals("trashman") && password.equals("a")){
                            Intent i = new Intent(LoginActivity.this, ProblemsViewActivity.class);
                            startActivity(i);
                        }


                    } else {
                        // Show pass error
                        passwordTv.setError("Enter the password");
                    }
                } else {
                    // show email error
                    emailTv.setError("Enter the email");
                }
            }
        });
    }

}
