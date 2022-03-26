package es.ucm.fdi.appdopta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class ficha extends AppCompatActivity {

    ToggleButton infoButton;
    LinearLayout dataDue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        Intent intent = getIntent();
        infoButton = findViewById(R.id.infoDue);
        dataDue = findViewById(R.id.layoutDue);

    }
    public void mostrar(View view){
        if(infoButton.isChecked()){
            dataDue.setVisibility(View.VISIBLE);
            Log.d("prueba", "SI");
        }
        else {
            dataDue.setVisibility(View.INVISIBLE);
            Log.d("prueba", "NO");
        }
    }
}