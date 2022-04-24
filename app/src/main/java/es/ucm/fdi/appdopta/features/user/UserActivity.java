package es.ucm.fdi.appdopta.features.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.login.LoginActivity;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;
import es.ucm.fdi.appdopta.features.settings.SettingsActivity;
import es.ucm.fdi.appdopta.features.user.changeInfo.ChangePasswordActivity;
import es.ucm.fdi.appdopta.features.user.changeInfo.ChangeUserActivity;

public class UserActivity extends AppCompatActivity {

    EditText userN;
    EditText userT;
    EditText userE;
    AppdoptaDBHelper dbHelper;
    private Bundle user;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.setTitle("Ajustes Usuario");
        userN = (EditText) findViewById(R.id.Username_U);
        userT = (EditText) findViewById(R.id.userPhone_U);
        userE = (EditText) findViewById(R.id.userEmail_U);
        dbHelper = new AppdoptaDBHelper(this);

        user = getIntent().getExtras();
        userid = user.getString("userInfo");

        UserInfo uwu = new UserInfo();
        dbHelper.buscarUsuario(uwu, userid);

        userN.setText(uwu.getUsername());
        userT.setText(String.valueOf(uwu.getPhone()));
        userE.setText(uwu.getEmail());
    }


    public void goToPets(View view){
        Intent intent = new Intent(getApplicationContext(), UserPetsActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void goToSettings_U(View view){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void changePw_U(View view){
        Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void changeUser_U(View view){
        Intent intent = new Intent(getApplicationContext(), ChangeUserActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void changeEmail_U(View view){
        String em = String.valueOf(userE.getText());
        dbHelper.updateUserEmail(userid, em);
        Toast.makeText(UserActivity.this, R.string.updatedEmail, Toast.LENGTH_SHORT).show();
    }

    public void changePhone_U(View view){
        int ph = Integer.parseInt(String.valueOf(userT.getText()));
        dbHelper.updateUserPhone(userid, ph);
        Toast.makeText(UserActivity.this, R.string.updatedPhone, Toast.LENGTH_SHORT).show();
    }

    public void logOut_U(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void goToMain_U(View view){
        //Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        //startActivity(intent);
    }
}