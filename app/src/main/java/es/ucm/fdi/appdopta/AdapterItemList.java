package es.ucm.fdi.appdopta;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;

public class AdapterItemList extends RecyclerView.Adapter<AdapterItemList.ViewHolderDatos> {

    private ArrayList<Animal> listaDatos;
    private LayoutInflater layoutInflater;
    private Context context;
    AppdoptaDBHelper dbHelper;

    public AdapterItemList(Context c, ArrayList<Animal> listDatos) {
        this.context = c;
        this.layoutInflater = LayoutInflater.from(c);
        this.listaDatos = listDatos;
    }

    @NonNull
    @Override
    // Enlaza AdapterItemList con item_list
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ViewHolderDatos(itemView);
    }

    @Override
    // Enlaza AdapterItemList con class ViewHolderDatos
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        // AIUDA
        //ArrayList<Animal> petList = dbHelper.readListPetData();
        Animal mCurrent = listaDatos.get(position); // Crear clase animla: String->Animal
        TextView t = holder.petName;

        t.setText(mCurrent.getName());
        t = holder.petSpecies;
        t.setText(mCurrent.getSpecies());
        t = holder.petLocation;
        t.setText(mCurrent.getLocation());
        //t.setOnClickListener(new View.OnClickListener(){
            /*@Override
            public void onClick(View view) {
                Intent busqueda = new Intent(Intent.ACTION_VIEW, Uri.parse(mCurrent.getURL()));
                context.startActivity(busqueda);
            }*/
        //});
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView petName;
        TextView petSpecies;
        TextView petLocation;

        public ViewHolderDatos(View itemView){
            super(itemView);
            petName = itemView.findViewById(R.id.nombreMascota);
            petSpecies = itemView.findViewById(R.id.especieMascota);
            petLocation = itemView.findViewById(R.id.ubicacionMascota);

        }
    }
}
