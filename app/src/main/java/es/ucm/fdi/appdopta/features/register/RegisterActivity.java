package es.ucm.fdi.appdopta.features.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button loginButt, registerButt; //
    AppdoptaDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Registro");

        username = (EditText) findViewById(R.id.usernameRegister);
        password = (EditText) findViewById(R.id.passwordRegister);
        repassword = (EditText) findViewById(R.id.repeatpassRegister);
        dbHelper = new AppdoptaDBHelper(this);

    }
    public void register(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String repassw = repassword.getText().toString();

        if(user.equals("") || pass.equals("") || repassw.equals("")) {
            Toast.makeText(RegisterActivity.this, "Por favor rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            if(pass.equals(repassw)){
                Boolean checkuser = dbHelper.checkUserName(user);
                if(checkuser == false){
                    Boolean reg = dbHelper.insertUserData(user, pass);
                    if(reg == true){
                        Toast.makeText(RegisterActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void login(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}