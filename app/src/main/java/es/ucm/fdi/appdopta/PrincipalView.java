package es.ucm.fdi.appdopta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;

public class PrincipalView extends AppCompatActivity {
    private ArrayList<Animal> petsList;
    private AppdoptaDBHelper dbHelper;
    private AdapterItemList adapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_view);
        Intent intent = getIntent();

        petsList = new ArrayList<>();
        dbHelper = new AppdoptaDBHelper(this);
        initValues();
        petsList = dbHelper.readListPetData();
        mRecyclerView = findViewById(R.id.listaMascotas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        if (petsList.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter = new AdapterItemList(this, petsList);

            mRecyclerView.setAdapter(adapter);
        }
        else {
            mRecyclerView.setVisibility(View.GONE);
            //Toast.makeText(this, "Todavia no hay animales en nuestras bases de datos", Toast.LENGTH_LONG).show();
        }
        dbHelper.close();
    }

    public void initValues(){
//(String id, String id_owner, String petname, String gender, String race, String desc, String specie, String bday,
// //int vacc_rabia, int vacc_parvovirus, int vacc_moquillo, int vacc_polivalente, int chip_num, String chip_date, String chip_loc) {

            dbHelper.insertPetData("1", "1", "1", "1", "chiguagua", "1", "Perro",
                    "1", 1, 2, 3, 1, 2, "1", "Madrid");
        dbHelper.insertPetData("1", "", "", "", ":)", "", "Gato",
                "", 1, 2, 3, 1, 2, "", "Madrid");
        dbHelper.insertPetData("1", "", "", "", "Labrador", "", "Perro",
                "", 1, 2, 3, 1, 2, "", "Valencia");
    }
    /*public void OnclickButtonListener() {

        ImageView filtro = findViewById(R.id.filtro);

        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(this,MapsActivity.class);
                //startActivity(intent);
                //Toast.makeText(this, "Has pulsado el filtro", Toast.LENGTH_LONG).show();
            }
        });
    }*/

    public void filtrar(View view){
        Intent intent = new Intent(this, FiltroView.class);
        startActivity(intent);
    }
}
