package es.ucm.fdi.appdopta.features.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity { //h

    EditText username, password;
    Button loginButt, registerButt;
    AppdoptaDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.userLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        loginButt = (Button) findViewById(R.id.loginButton);
        registerButt = (Button) findViewById(R.id.registerButton);
        dbHelper = new AppdoptaDBHelper(this);

        loginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

        registerButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


    }
}