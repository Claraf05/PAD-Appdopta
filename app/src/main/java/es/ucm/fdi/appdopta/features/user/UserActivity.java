package es.ucm.fdi.appdopta.features.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.features.login.LoginActivity;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;
import es.ucm.fdi.appdopta.features.settings.SettingsActivity;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void goToSettings_U(View view){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    public void changePw_U(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void changeUser_U(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    public void logOut_U(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void goToMain_U(View view){
        //Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        //startActivity(intent);
    }
}