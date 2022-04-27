package es.ucm.fdi.appdopta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.Fichas.fichaActivity;
import es.ucm.fdi.appdopta.features.settings.SettingsActivity;
import es.ucm.fdi.appdopta.features.user.UserActivity;

public class PrincipalView extends AppCompatActivity {

    private ArrayList<Animal> petsList;
    private AppdoptaDBHelper dbHelper;
    private AdapterItemList adapter;
    private RecyclerView mRecyclerView;
    private Bundle user;
    private String userid;

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int resultCode = result.getResultCode();
                    if (resultCode == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if(data!=null) {
                            String animalSelected = data.getStringExtra("animalSelected");
                            String raceSelected = data.getStringExtra("raceSelected");
                            String locationSelected = data.getStringExtra("locationSelected");
                            applyFilter(animalSelected,raceSelected,locationSelected);
                        }

                    }
                }
            }

    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_view);
        //Intent intent = getIntent();
        user = getIntent().getExtras();
        userid = user.getString("userInfo");

        petsList = new ArrayList<>();
        dbHelper = new AppdoptaDBHelper(this);
        petsList = dbHelper.readListPetData();
        mRecyclerView = findViewById(R.id.listaMascotas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        if (petsList.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            updatePetList();
        }
        else {
            mRecyclerView.setVisibility(View.GONE);
            //Toast.makeText(this, "Todavia no hay animales en nuestras bases de datos", Toast.LENGTH_LONG).show();
            //SI SE DESCOMENTA AÑADIR AL STRING.XML PARA EL IDIOMA//
        }
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = petsList.get(position).getId();
                Intent intent = new Intent(PrincipalView.this, fichaActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("idUser", userid);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        dbHelper.close();
    }


    public void applyFilter(String especie, String raza, String ubicacion){
        if(getResources().getString(R.string.slcAnimal).equals(especie)) especie = null;
        if(getResources().getString(R.string.slcBreed).equals(raza)) raza = null;
        if(getResources().getString(R.string.slcLoc).equals(ubicacion)) ubicacion = null;
        dbHelper = new AppdoptaDBHelper(this);
        petsList = dbHelper.filterQuery(especie, raza, ubicacion);
        updatePetList();
        dbHelper.close();
    }


    public void updatePetList(){
        adapter = new AdapterItemList(this, petsList,dbHelper);
        mRecyclerView.setAdapter(adapter);
    }

    public void filter(View view){
        Intent intent = new Intent(PrincipalView.this, FiltroView.class);
        activityResultLauncher.launch(intent);
    }

    public void userInfoActivity(View view){
        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }
    public void goToSettings(View view){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }
}
