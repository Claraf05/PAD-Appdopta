package es.ucm.fdi.appdopta;

import android.os.Bundle;
import android.view.View;
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

        petsList = new ArrayList<>();
        dbHelper = new AppdoptaDBHelper(this);
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
            Toast.makeText(this, "Todavia no hay animales en nuestras bases de datos", Toast.LENGTH_LONG).show();
        }
        dbHelper.close();
    }
}
