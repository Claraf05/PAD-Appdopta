package es.ucm.fdi.appdopta.features.user.changeInfo;

import androidx.appcompat.app.AppCompatActivity;
import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.login.LoginActivity;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;
import es.ucm.fdi.appdopta.features.user.UserActivity;
import es.ucm.fdi.appdopta.features.user.UserInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText actP;
    EditText newP;
    EditText repP;
    AppdoptaDBHelper dbHelper;
    private Bundle user;
    String userid;
    UserInfo uwu = new UserInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        this.setTitle("Cambiar contraseña");
        actP = (EditText) findViewById(R.id.oldPwtxt);
        newP = (EditText) findViewById(R.id.newPwtxt);
        repP = (EditText) findViewById(R.id.repnewPwtxt);
        dbHelper = new AppdoptaDBHelper(this);

        user = getIntent().getExtras();
        userid = user.getString("userInfo");

        dbHelper.buscarUsuario(uwu, userid);

    }

    public void change_P(View view){
        String old = actP.getText().toString();
        String pass = newP.getText().toString();
        String rep = repP.getText().toString();

        if(old.equals("") || pass.equals("") || rep.equals("")) {
            Toast.makeText(ChangePasswordActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean checkuser = dbHelper.checkUserPassword(uwu.getUsername(), old);
            if(checkuser == true) {
                if (pass.equals(rep)) {
                    dbHelper.updateUserPw(userid, pass);
                    Toast.makeText(ChangePasswordActivity.this, "Se cambió la contraseña con exito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    intent.putExtra("userInfo", userid);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ChangePasswordActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(ChangePasswordActivity.this, "Contraseña actual inorrecta", Toast.LENGTH_SHORT).show();
            }
        }
    }
}