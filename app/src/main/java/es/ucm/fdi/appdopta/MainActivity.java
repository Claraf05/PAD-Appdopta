package es.ucm.fdi.appdopta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import es.ucm.fdi.appdopta.features.Fichas.fichaActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_principal_view);

        //Intent intent = new Intent(this, fichaActivity.class);
        Intent intent = new Intent(this, PrincipalView.class);
        startActivity(intent);

    }
}
