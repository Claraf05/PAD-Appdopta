package es.ucm.fdi.appdopta.features.settings;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Locale;

import es.ucm.fdi.appdopta.PrincipalView;
import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.features.login.LoginActivity;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;
import es.ucm.fdi.appdopta.features.user.UserActivity;
import es.ucm.fdi.appdopta.features.user.changeInfo.ChangePasswordActivity;
import es.ucm.fdi.appdopta.features.user.changeInfo.ChangeUserActivity;

public class SettingsActivity extends AppCompatActivity {

    private Bundle user;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        this.setTitle("Ajustes");
        //TODO coger los extras del intent para coger el usuario y mostrar su info por los ajustes
        user = getIntent().getExtras();
        userid = user.getString("userInfo");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    public void changePw_S(View view){
        Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void changeUser(View view){
        Intent intent = new Intent(getApplicationContext(), ChangeUserActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void logOut(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void goToMain(View view){
        Intent intent = new Intent(getApplicationContext(), PrincipalView.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void userInfoActivity(View view){
        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }
    public void espa??ol(View view){
        changeLocal(new Locale("es"));
    }
    public void ingles(View view){
        changeLocal(new Locale("en"));
    }
    public void frances(View view){
        changeLocal(new Locale("fr"));
    }

    private void changeLocal(Locale locale){
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        this.getResources().updateConfiguration(config,this.getResources().getDisplayMetrics());
        Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
        intent.putExtra("userInfo", userid);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}