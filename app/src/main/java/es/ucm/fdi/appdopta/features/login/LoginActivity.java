package es.ucm.fdi.appdopta.features.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import es.ucm.fdi.appdopta.R;

public class LoginActivity extends AppCompatActivity { //h

    EditText username, password, repassword;
    Button signin, signup; //b

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.userLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
    }
}