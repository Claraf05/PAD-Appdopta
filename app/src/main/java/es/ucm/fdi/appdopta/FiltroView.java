package es.ucm.fdi.appdopta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;

public class FiltroView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_view);
        Intent intent = getIntent();
        Spinner animalSpinner = findViewById(R.id.spinnerAnimal);
        Spinner raceSpinner = findViewById(R.id.spinnerRace);
        Spinner locationSpinner = findViewById(R.id.spinnerLocation);

        //  Lista de elementos del spinner 1 --> tipos de animal
        ArrayList<String> petsType = new ArrayList<String>();
        petsType.add("Seleccionar animal...");
        petsType.add("Perro");
        petsType.add("Gato");
        petsType.add("Conejo");
        petsType.add("Loro");

        animalSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterPet = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, petsType);
        adapterPet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animalSpinner.setAdapter(adapterPet);

        //  Lista de elementos del spinner 2 --> razas de animal
        ArrayList<String> petsRace = new ArrayList<String>();
        petsRace.add("Seleccionar raza...");
        petsRace.add("Labrador");
        petsRace.add("Golden Retriever");
        petsRace.add("Boyero de Berna");

        raceSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterRace = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, petsRace){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }


        };
        adapterRace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(adapterRace);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getSelectedItem();
        switch (adapterView.getId())
        {

            case R.id.spinnerAnimal:
                //Hacer algo aquí
                Toast.makeText(this, "Has seleccionado " + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                break;
            case R.id.spinnerRace:
                //Hacer algo aquí
                Toast.makeText(this, "Has seleccionado " + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
/*
* TO DO:
* Si todos return false --> boton aplicar = boton atras
*
*
* */