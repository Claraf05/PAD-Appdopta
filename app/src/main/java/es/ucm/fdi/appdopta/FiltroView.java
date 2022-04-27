package es.ucm.fdi.appdopta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Arrays;

import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;

public class FiltroView extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner animalSpinner;
    private Spinner raceSpinner;
    private Spinner locationSpinner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_view);
        Intent intent = getIntent();
        animalSpinner = findViewById(R.id.spinnerAnimal);
        raceSpinner = findViewById(R.id.spinnerRace);
        locationSpinner = findViewById(R.id.spinnerLocation);
        initSpinnerValues();


    }
    public void borrar(View view){
        animalSpinner.setSelection(0);
        raceSpinner.setSelection(0);
        locationSpinner.setSelection(0);
    }
    public void aplicar(View view){
        String animalSelected = animalSpinner.getSelectedItem().toString();
        if(animalSelected.equalsIgnoreCase("dog") || animalSelected.equalsIgnoreCase("chien") || animalSelected.equalsIgnoreCase("perro")) animalSelected = "perro";
        //COMPLETAR



        String raceSelected = raceSpinner.getSelectedItem().toString();
        String locationSelected = locationSpinner.getSelectedItem().toString();
        Intent intent = new Intent();
        intent.putExtra("animalSelected", animalSelected);
        intent.putExtra("raceSelected", raceSelected);
        intent.putExtra("locationSelected", locationSelected);
        setResult(Activity.RESULT_OK, intent);
        finish();
        //FiltroView.super.onBackPressed();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        adapterView.getSelectedItem();
        if(position != 0) {
            switch (adapterView.getId()) {

                case R.id.spinnerAnimal:
                    //Hacer algo aquí

                    //Toast.makeText(this, getText(R.string.selected) + " " + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                    break;
                case R.id.spinnerRace:
                    //Hacer algo aquí
                    //Toast.makeText(this, getText(R.string.selected) + " " + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

                    break;
                case R.id.spinnerLocation:
                    //Hacer algo aquí
                    //Toast.makeText(this, getText(R.string.selected) + " " + adapterView.getSelectedItem().toString(), Toast.LENGTH_LONG).show();

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
        petsType.add(getText(R.string.slcAnimal).toString());

        String[] animales = getResources().getStringArray(R.array.animal);
        petsType.addAll(Arrays.asList(animales));

        animalSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterPet = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, petsType);
        adapterPet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        animalSpinner.setAdapter(adapterPet);

        //  Lista de elementos del spinner 2 --> razas de animal
        ArrayList<String> petsRace = new ArrayList<String>();
        petsRace.add(getText(R.string.slcBreed).toString());

        String[] razas = getResources().getStringArray(R.array.breed);
        petsRace.addAll(Arrays.asList(razas));

        raceSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterRace = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, petsRace);
        adapterRace.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpinner.setAdapter(adapterRace);

        //  Lista de elementos del spinner 3 --> ubicación del animal
        ArrayList<String> petsLocation = new ArrayList<String>();
        petsLocation.add(getText(R.string.slcLoc).toString());

        String[] ubi = getResources().getStringArray(R.array.location);
        petsLocation.addAll(Arrays.asList(ubi));

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

