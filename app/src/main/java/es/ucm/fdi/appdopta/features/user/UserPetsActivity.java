package es.ucm.fdi.appdopta.features.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.appdopta.AdapterItemList;
import es.ucm.fdi.appdopta.Animal;
import es.ucm.fdi.appdopta.PrincipalView;
import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.Fichas.aniadirFichaActivity;
import es.ucm.fdi.appdopta.features.settings.SettingsActivity;

public class UserPetsActivity extends AppCompatActivity {

    private ArrayList<Animal> petsList;
    private AppdoptaDBHelper dbHelper;
    private AdapterItemList adapter;
    private RecyclerView mRecyclerView;
    Bundle user;
    String userid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pets);
        //cogemos los datos del usuario
        user = getIntent().getExtras();
        userid = user.getString("userInfo");

        petsList = new ArrayList<>();
        dbHelper = new AppdoptaDBHelper(this);

        petsList = dbHelper.readListPetDataByUserId(userid);
        mRecyclerView = findViewById(R.id.listaMascotasUser);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        if (petsList.size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            adapter = new AdapterItemList(this, petsList);

            mRecyclerView.setAdapter(adapter);
        }
        else {
            mRecyclerView.setVisibility(View.GONE);
            //Toast.makeText(this, "Todavia no hay animales en nuestras bases de datos", Toast.LENGTH_LONG).show();
            //SI SE DESCOMENTA AÑADIR AL STRING.XML PARA EL IDIOMA//
        }
        dbHelper.close();

    }

    public void aniadirMascota(View view){
        Intent intent = new Intent(getApplicationContext(), aniadirFichaActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);

    }

    public void verMascota(View view){
        Intent intent = new Intent(getApplicationContext(), aniadirFichaActivity.class);
        intent.putExtra("userInfo", userid);
        startActivity(intent);
    }

    public void goToMain(View view){
        Intent intent = new Intent(getApplicationContext(), PrincipalView.class);
        intent.putExtra("userInfo", userid);
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
