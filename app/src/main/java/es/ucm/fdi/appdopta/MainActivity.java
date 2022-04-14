package es.ucm.fdi.appdopta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(this, fichaActivity.class);
        Intent intent = new Intent(this, PrincipalView.class);
        startActivity(intent);

    }
}
