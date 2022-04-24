package es.ucm.fdi.appdopta.features.user.changeInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.user.UserActivity;
import es.ucm.fdi.appdopta.features.user.UserInfo;

public class ChangeUserActivity extends AppCompatActivity {

    EditText actN;
    EditText newN;
    EditText repN;
    AppdoptaDBHelper dbHelper;
    private Bundle user;
    String userid;
    UserInfo uwu = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);

        this.setTitle("Cambiar nombre de usuario");
        actN = (EditText) findViewById(R.id.actUserName);
        newN = (EditText) findViewById(R.id.newUserName);
        repN = (EditText) findViewById(R.id.repNewUserName);
        dbHelper = new AppdoptaDBHelper(this);

        user = getIntent().getExtras();
        userid = user.getString("userInfo");

        dbHelper.buscarUsuario(uwu, userid);
    }

    public void change(View view){

        String old = actN.getText().toString();
        String us = newN.getText().toString();
        String rep = repN.getText().toString();

        if(old.equals("") || us.equals("") || rep.equals("")) {
            Toast.makeText(ChangeUserActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkuser = dbHelper.checkUserName(old);
            if(checkuser == true) {
                if (us.equals(rep)) {
                    dbHelper.updateUserName(userid, us);
                    Toast.makeText(ChangeUserActivity.this, "Se cambió el usuario con exito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    intent.putExtra("userInfo", userid);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ChangeUserActivity.this, "Los usuarios no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(ChangeUserActivity.this, "Nombre de usuario actual inorrecto", Toast.LENGTH_SHORT).show();
            }
        }

    }
}