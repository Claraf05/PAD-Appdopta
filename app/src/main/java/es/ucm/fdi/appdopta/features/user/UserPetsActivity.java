package es.ucm.fdi.appdopta.features.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.features.Fichas.aniadirFichaActivity;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;

public class UserPetsActivity extends AppCompatActivity {


    Bundle user;
    String userid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pets);
        //cogemos los datos del usuario
        user = getIntent().getExtras();
        userid = user.getString("userInfo");

    }

    public void aniadirMascota(View view){
        Intent intent = new Intent(getApplicationContext(), aniadirFichaActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);

    }
}
