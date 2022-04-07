package es.ucm.fdi.appdopta.features.Fichas;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import es.ucm.fdi.appdopta.R;

public class aniadirFichaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadirficha);
        Intent intent = getIntent();
    }

}
