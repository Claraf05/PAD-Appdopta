package es.ucm.fdi.appdopta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterItemList extends RecyclerView.Adapter<AdapterItemList.ViewHolderDatos> {

    private ArrayList<String> listaDatos;
    private LayoutInflater layoutInflater;
    private Context context;

    public AdapterItemList(Context c, ArrayList<String> listDatos) {
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
    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView petName;
        TextView petSpecies;

        public ViewHolderDatos(View itemView){
            super(itemView);
            petName = itemView.findViewById(R.id.nombreMascota);
            petSpecies = itemView.findViewById(R.id.especieMascota);
        }
    }
}
