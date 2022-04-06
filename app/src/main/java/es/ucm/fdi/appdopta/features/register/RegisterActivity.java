package es.ucm.fdi.appdopta.features.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, repassword, phone, email;
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
        phone = (EditText) findViewById(R.id.phoneRegister);
        email = (EditText) findViewById(R.id.emailRegister);
        dbHelper = new AppdoptaDBHelper(this);

    }
    public void register(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String repassw = repassword.getText().toString();
        String ph = phone.getText().toString();
        String mail = email.getText().toString();

        //assign an id to a user
        boolean idcount;
        int id = new Random().nextInt(3000);
        do{
            id = new Random().nextInt(3000);
            idcount = dbHelper.checkUserId(String.valueOf(id));
        }
        while(idcount);

        //authentication
        if(user.equals("") || pass.equals("") || repassw.equals("") || ph.equals("") || mail.equals("")) {
            Toast.makeText(RegisterActivity.this, "Por favor rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            if(pass.equals(repassw)){
                Boolean checkuser = dbHelper.checkUserName(user);
                if(checkuser == false){
                    Boolean reg = dbHelper.insertUserData(String.valueOf(id), user, pass, Integer.parseInt(ph), mail, 0);
                    if(reg == true){
                        Toast.makeText(RegisterActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
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
        startActivity(intent);
    }
}