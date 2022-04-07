package es.ucm.fdi.appdopta.features.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.login.LoginActivity;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;
import es.ucm.fdi.appdopta.features.settings.SettingsActivity;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.StandardUserTable;

public class UserActivity extends AppCompatActivity {

    private String u;
    private Bundle userinfo;
    AppdoptaDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        this.setTitle("Ajustes Usuario");
        userinfo = getIntent().getExtras();
        u = userinfo.getString("username", u);
        dbHelper = new AppdoptaDBHelper(this);
    }

    public void goToSettings_U(View view){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    //TODO
    public void changePw_U(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    //TODO
    public void changeUser_U(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    //TODO
    public void changeTlf(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String here = StandardUserTable.USERNAME_C + " = ?";

        //contentValues.put(StandardUserTable.PHONE_C, phone);

        db.update(StandardUserTable.TABLE_NAME, contentValues, here, new String[]{});
    }

    //TODO
    public void changeEmail(){

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