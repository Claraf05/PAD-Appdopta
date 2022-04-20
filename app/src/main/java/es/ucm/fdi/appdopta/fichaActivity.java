package es.ucm.fdi.appdopta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class fichaActivity extends AppCompatActivity implements OnMapReadyCallback {

    ToggleButton infoButton;
    LinearLayout dataDue;
    RadioGroup mRadio;
    ScrollView desc;
    LinearLayout dataChip,botonesZoom;
    View fMap;
    CardView card;
    String localidad;
    double Lat, Lng;
    GoogleMap g;
    int zoom[] = {1,5,10,15,20};
    int zoomP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        Intent intent = getIntent();
        infoButton = findViewById(R.id.infoDue);
        dataDue = findViewById(R.id.layoutDue);
        mRadio = findViewById(R.id.radioGroup);
        desc = findViewById(R.id.scrollViewDesc);
        dataChip = findViewById(R.id.dataChip);
        botonesZoom = findViewById(R.id.botonesZoom);
        fMap = findViewById(R.id.map);
        card = findViewById(R.id.cardView);

        Geocoder geo = new Geocoder(this);
        String loc = "San Blas";
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
            Log.d("prueba", "SI");
        }
        else {
            dataDue.setVisibility(View.INVISIBLE);
            Log.d("prueba", "NO");
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
}