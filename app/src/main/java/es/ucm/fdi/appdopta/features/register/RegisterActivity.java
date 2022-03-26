package es.ucm.fdi.appdopta.features.register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.ucm.fdi.appdopta.R;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signin, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.usernameRegister);
        password = (EditText) findViewById(R.id.passwordRegister);
        repassword = (EditText) findViewById(R.id.repeatpassRegister);
        signup = (Button) findViewById(R.id.registerButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}