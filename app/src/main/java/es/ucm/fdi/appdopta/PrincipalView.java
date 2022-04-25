package es.ucm.fdi.appdopta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_view);
        //Intent intent = getIntent();
        user = getIntent().getExtras();
        userid = user.getString("userInfo");


        petsList = new ArrayList<>();
        dbHelper = new AppdoptaDBHelper(this);
        initValues();
        petsList = dbHelper.readListPetData();
        mRecyclerView = findViewById(R.id.listaMascotas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        if (petsList.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter = new AdapterItemList(this, petsList,dbHelper);

            mRecyclerView.setAdapter(adapter);
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
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        dbHelper.close();
    }

    public void initValues(){
//(String id, String id_owner, String petname, String gender, String race, String desc, String specie, String bday,
// //int vacc_rabia, int vacc_parvovirus, int vacc_moquillo, int vacc_polivalente, int chip_num, String chip_date, String chip_loc) {

        /*dbHelper.insertPetData("1", "1", "1", "1", "chiguagua", "1", "Perro",
                    "1", 1, 2, 3, 1, 2, "1", "Madrid");
        dbHelper.insertPetData("1", "", "", "", ":)", "", "Gato",
                "", 1, 2, 3, 1, 2, "", "Madrid");
        dbHelper.insertPetData("1", "", "", "", "Labrador", "", "Perro",
                "", 1, 2, 3, 1, 2, "", "Valencia");*/
    }
    /*public void OnclickButtonListener() {

        ImageView filtro = findViewById(R.id.filtro);

        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent=new Intent(this,MapsActivity.class);
                //startActivity(intent);
                //Toast.makeText(this, "Has pulsado el filtro", Toast.LENGTH_LONG).show();
                //SI SE DESCOMENTA AÑADIR AL STRING.XML PARA EL IDIOMA//
            }
        });
    }*/

    public void filtrar(View view){
        Intent intent = new Intent(this, FiltroView.class);
        startActivity(intent);
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
