package es.ucm.fdi.appdopta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.ucm.fdi.appdopta.features.Fichas.aniadirFichaActivity;
import es.ucm.fdi.appdopta.features.Fichas.fichaActivity;
import es.ucm.fdi.appdopta.features.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_principal_view);

        //Intent intent = new Intent(this, fichaActivity.class);
        Intent intent = new Intent(this, FiltroView.class);
        startActivity(intent);


    }
}
