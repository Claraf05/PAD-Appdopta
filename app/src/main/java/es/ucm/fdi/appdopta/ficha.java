package es.ucm.fdi.appdopta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.ToggleButton;

public class ficha extends AppCompatActivity {

    ToggleButton infoButton;
    LinearLayout dataDue;
    RadioGroup mRadio;
    ScrollView desc;
    LinearLayout dataChip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        Intent intent = getIntent();
        infoButton = findViewById(R.id.infoDue);
        dataDue = findViewById(R.id.layoutDue);
        mRadio = findViewById(R.id.radioGroup);
        desc = findViewById(R.id.scrollViewDesc);
        dataChip = findViewById(R.id.dataChip);

    }
    public void desc(View view){
        desc.setVisibility(View.VISIBLE);
        dataChip.setVisibility(View.INVISIBLE);
    }
    public void micro(View view){
        desc.setVisibility(View.INVISIBLE);
        dataChip.setVisibility(View.VISIBLE);
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