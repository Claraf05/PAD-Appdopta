package es.ucm.fdi.appdopta.features.Fichas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import es.ucm.fdi.appdopta.Animal;
import es.ucm.fdi.appdopta.PrincipalView;
import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.settings.SettingsActivity;
import es.ucm.fdi.appdopta.features.user.UserActivity;
import es.ucm.fdi.appdopta.features.user.UserInfo;

public class fichaActivity extends AppCompatActivity implements OnMapReadyCallback {
    TextView nombre, especie, raza, sexo, fechaNacimiento, nombreDue, correoDue, teleDue, descripcion, chipDate, chipNum, chipLoc;
    Bundle user;
    ToggleButton infoButton;
    LinearLayout dataDue;
    RadioGroup mRadio;
    ScrollView desc;
    LinearLayout dataChip,botonesZoom;
    View fMap;
    CardView card;
    String localidad, idAn;
    ImageView imagen;
    UserInfo usuario;
    String userid;
    double Lat, Lng;
    GoogleMap g;
    int zoom[] = {1,5,10,15,20};
    int zoomP;
    Button adoptButton;

    AppdoptaDBHelper dbHelper = new AppdoptaDBHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        Intent intent = getIntent();
        user = getIntent().getExtras();
        idAn = user.getString("id");
        userid = user.getString("idUser");

        Log.d("PRUEBA", idAn);
        nombre = findViewById(R.id.nombreAnimal);
        especie = findViewById(R.id.especieAnimal);
        raza = findViewById(R.id.razaAnimal);
        sexo = findViewById(R.id.sexoAnimal);
        fechaNacimiento = findViewById(R.id.fechaAnimal);
        nombreDue = findViewById(R.id.nombreDue);
        correoDue = findViewById(R.id.correoDue);
        teleDue = findViewById(R.id.telefonoDue);
        descripcion = findViewById(R.id.desc);
        chipDate = findViewById(R.id.fechaChip);
        chipNum = findViewById(R.id.numChip);
        chipLoc = findViewById(R.id.localizacionChip);

        infoButton = findViewById(R.id.infoDue);
        dataDue = findViewById(R.id.layoutDue);
        mRadio = findViewById(R.id.radioGroup);
        desc = findViewById(R.id.scrollViewDesc);
        dataChip = findViewById(R.id.dataChip);
        botonesZoom = findViewById(R.id.botonesZoom);
        fMap = findViewById(R.id.map);
        card = findViewById(R.id.cardView);
        imagen = findViewById(R.id.imagen);

        //cuando tengamos aqui el id de la mascota,descomentar
        imagen.setImageBitmap(dbHelper.buscarImagen(idAn));
        Animal a = dbHelper.buscarPet(idAn);

        nombre.setText(a.getNombre());
        especie.setText(a.getEspecie());
        raza.setText(a.getRace());
        sexo.setText(a.getGender());
        fechaNacimiento.setText(a.getBday());
        descripcion.setText(a.getDesc());
        chipDate.setText(a.getChipDate());
        chipNum.setText(a.getChipNum());
        chipLoc.setText(a.getChipLoc());
        String loc = a.getLocation();
        UserInfo u = new UserInfo();
        dbHelper.buscarUsuario(u, a.getIdOwner());
        nombreDue.setText(u.getUsername());
        correoDue.setText(u.getEmail());
        teleDue.setText(u.getPhone() + "");

        adoptButton = (Button)findViewById(R.id.BotonAdoptar);
        adoptButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "¡Gracias por adoptar!💖", Toast.LENGTH_SHORT).show();
            }
        });

        Geocoder geo = new Geocoder(this);
        try{
            List<Address> ads = geo.getFromLocationName(loc + "Spain",1);
            if(ads!=null && !ads.isEmpty()){
                Address ad = ads.get(0);
                localidad = ad.getFeatureName();
                Lat = ad.getLatitude();
                Lng = ad.getLongitude();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SupportMapFragment mapFragment= (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    public void desc(View view){
        card.setVisibility(View.VISIBLE);
        desc.setVisibility(View.VISIBLE);
        dataChip.setVisibility(View.INVISIBLE);
        fMap.setVisibility(View.INVISIBLE);
        botonesZoom.setVisibility(View.INVISIBLE);
    }
    public void micro(View view){
        card.setVisibility(View.VISIBLE);
        desc.setVisibility(View.INVISIBLE);
        dataChip.setVisibility(View.VISIBLE);
        fMap.setVisibility(View.INVISIBLE);
        botonesZoom.setVisibility(View.INVISIBLE);
    }
    public void showMap(View view){
        card.setVisibility(View.INVISIBLE);
        desc.setVisibility(View.INVISIBLE);
        dataChip.setVisibility(View.INVISIBLE);
        fMap.setVisibility(View.VISIBLE);
        botonesZoom.setVisibility(View.VISIBLE);
    }
    public void mostrar(View view){
        if(infoButton.isChecked()){
            dataDue.setVisibility(View.VISIBLE);
        }
        else {
            dataDue.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        zoomP = 3;
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(Lat,Lng)).title(localidad));
        googleMap.moveCamera((CameraUpdateFactory.newLatLngZoom(new LatLng(Lat,Lng),zoom[zoomP])));
        g = googleMap;
        fMap.setVisibility(View.INVISIBLE);
        botonesZoom.setVisibility(View.INVISIBLE);
    }
    public void zoomI(View view){
        if(zoomP < 4) {
            zoomP++;
            g.moveCamera((CameraUpdateFactory.newLatLngZoom(new LatLng(Lat,Lng),zoom[zoomP])));
        }

    }
    public void zoomO(View view){
        if(zoomP > 0){
            zoomP--;
            g.moveCamera((CameraUpdateFactory.newLatLngZoom(new LatLng(Lat,Lng),zoom[zoomP])));
        }

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