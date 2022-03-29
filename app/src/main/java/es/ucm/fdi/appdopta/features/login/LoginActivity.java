package es.ucm.fdi.appdopta.features.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;
import es.ucm.fdi.appdopta.features.settings.SettingsActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginButt, registerButt;
    AppdoptaDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Appdopta");

        username = (EditText) findViewById(R.id.userLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        dbHelper = new AppdoptaDBHelper(this);
    }

    public void login(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(user.equals("") || pass.equals("")) {
            Toast.makeText(LoginActivity.this, "Por favor rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{

            Boolean checkuser = dbHelper.checkUserPassword(user,pass);
            if(checkuser == true){
                CharSequence txt = "Bienvenido: " + user;
                Toast.makeText(LoginActivity.this, txt, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(LoginActivity.this, "El usuario no existe o se introdujo algun valor incorrecto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void register(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }
}