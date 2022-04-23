package es.ucm.fdi.appdopta.features.user.changeInfo;

import androidx.appcompat.app.AppCompatActivity;
import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.user.UserInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText actP;
    EditText newP;
    EditText repP;
    AppdoptaDBHelper dbHelper;
    private Bundle user;
    String userid;

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

        UserInfo uwu = new UserInfo();
        dbHelper.buscarUsuario(uwu, userid);

    }

    public void change_P(View view){
        //TODO terminar usando la autenticacion del register de referencia
    }
}