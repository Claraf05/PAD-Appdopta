package es.ucm.fdi.appdopta.features.user.changeInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.ucm.fdi.appdopta.R;

public class ChangeUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);
    }

    public void change(View view){
        //TODO acuerdate de checkear si el nombre de usuario ya existe con dbhelper.checkuser igual que hago en el register
        //TODO despues ya llamar al metodo de cambiar nombre de usuario
    }
}