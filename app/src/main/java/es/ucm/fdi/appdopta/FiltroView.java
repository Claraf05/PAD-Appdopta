package es.ucm.fdi.appdopta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;

public class FiltroView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner animalSpinner;
    private Spinner raceSpinner;
    private Spinner locationSpinner;
    private Button applySelection;
    private Button eraseSelection;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_view);
        Intent intent = getIntent();
        animalSpinner = findViewById(R.id.spinnerAnimal);
        raceSpinner = findViewById(R.id.spinnerRace);
        locationSpinner = findViewById(R.id.spinnerLocation);
        initSpinnerValues();
        //applySelection = findViewById(R.)


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        adapterView.getSelectedItem();
        if(i != 0) {
            switch (adapterView.getId()) {

                case R.id.spinnerAnimal:
                    //Hacer algo aquí
                    Toast.makeText(this, "Has seleccionado " + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                    break;
                case R.id.spinnerRace:
                    //Hacer algo aquí
                    Toast.makeText(this, "Has seleccionado " + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                    break;
                case R.id.spinnerLocation:
                    //Hacer algo aquí
                    Toast.makeText(this, "Has seleccionado " + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void initSpinnerValues(){
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
        ArrayAdapter<String> adapterRace = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, petsRace);
        adapterRace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(adapterRace);

        //  Lista de elementos del spinner 3 --> ubicación del animal
        ArrayList<String> petsLocation = new ArrayList<String>();
        petsLocation.add("Seleccionar ubicación...");
        petsLocation.add("Madrid");
        petsLocation.add("Barcelona");
        petsLocation.add("Valencia");

        locationSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, petsLocation);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapterLocation);
    }
}
/*
* TO DO:
* Si todos return false --> boton aplicar = boton atras
* {
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
*
* */

