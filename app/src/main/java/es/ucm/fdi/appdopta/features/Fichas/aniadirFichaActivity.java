package es.ucm.fdi.appdopta.features.Fichas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import es.ucm.fdi.appdopta.PrincipalView;
import es.ucm.fdi.appdopta.R;
import es.ucm.fdi.appdopta.database.AppdoptaDBHelper;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;
import es.ucm.fdi.appdopta.features.settings.SettingsActivity;
import es.ucm.fdi.appdopta.features.user.UserActivity;
import es.ucm.fdi.appdopta.features.user.UserInfo;
import es.ucm.fdi.appdopta.features.user.UserPetsActivity;

public class aniadirFichaActivity extends AppCompatActivity {
    Bundle user;
    EditText correoD, telefonoD, localizacionD, especieM, razaM, nombreM, nacimientoM;
    RadioGroup sexo;
    RadioButton selectedSex;
    TextView descripcion, username, chiplocal, fechaChip,numChip;
    CheckBox rabia,hepatitis,leishmaniasis;
    AppdoptaDBHelper dbHelper = new AppdoptaDBHelper(this);
    String idDue;
    ImageView previewImage;
    Bitmap bitmap;
    UserInfo usuario = new UserInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadirficha);
        Intent intent = getIntent();
        user = getIntent().getExtras();
        idDue = user.getString("userInfo");
        //idDue = intent.getStringExtra("id");

        username = findViewById(R.id.userName);

        correoD =(EditText) findViewById(R.id.correoDueno);
        telefonoD = (EditText) findViewById(R.id.telefonoDueno);
        localizacionD = (EditText) findViewById(R.id.localizacionDueno);

        //insertamos datos de la BBDD
        dbHelper.buscarUsuario(usuario,idDue);

        username.setText(usuario.getUsername());
        correoD.setText(usuario.getEmail());
        telefonoD.setText(String.valueOf(usuario.getPhone()));



        especieM = (EditText) findViewById(R.id.especieMasc);
        razaM = (EditText) findViewById(R.id.razaMasc);
        nombreM = (EditText) findViewById(R.id.nombreMasc);

        sexo = (RadioGroup) findViewById(R.id.grupoSexoMasc);
        selectedSex = (RadioButton) findViewById(sexo.getCheckedRadioButtonId());

        nacimientoM = (EditText) findViewById(R.id.nacimientoMasc);
        chiplocal = findViewById(R.id.localizacionChipMasc);
        fechaChip = findViewById(R.id.fechaChipMasc);
        numChip = findViewById(R.id.numChipMasc);


        descripcion = (TextView) findViewById(R.id.editTextTextMultiLineMasc);

        rabia = (CheckBox) findViewById(R.id.checkBox);
        hepatitis = (CheckBox) findViewById(R.id.checkBox2);
        leishmaniasis = (CheckBox) findViewById(R.id.checkBox3);

        previewImage = (ImageView) findViewById(R.id.imagePreview);

    }

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK
                        && result.getData() != null) {
                    Uri photoUri = result.getData().getData();
                    previewImage.setImageURI(photoUri);


                    try {
                        InputStream imageStream = getContentResolver().openInputStream(photoUri);
                        bitmap = BitmapFactory.decodeStream(imageStream);

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
    );

    public void seleccionarImagen(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        launcher.launch(intent);
    }

    public void ConfirmarFicha(View view){
        String usern = username.getText().toString();
       // String correo = correoD.getText().toString();
       // String telefono = telefonoD.getText().toString();
        String localizacion = localizacionD.getText().toString();

        String especie = especieM.getText().toString();
        String raza = razaM.getText().toString();
        String nombreMasc = nombreM.getText().toString();

        String sexoMasc = selectedSex.getText().toString();
        String bday = nacimientoM.getText().toString();
        String desc = descripcion.getText().toString();
        String locChip = chiplocal.getText().toString();
        String fechChip = fechaChip.getText().toString();
        int nChip = 0;
        if(!numChip.getText().toString().isEmpty()){
            nChip = Integer.parseInt(numChip.getText().toString());
        }


        int rabiaV, hepatitisV,leishmaniasisV;
        if(rabia.isChecked()) rabiaV = 1;
        else rabiaV = 0;
        if(hepatitis.isChecked()) hepatitisV = 1;
        else hepatitisV = 0;
        if(leishmaniasis.isChecked()) leishmaniasisV = 1;
        else leishmaniasisV = 0;

        if(especie.isEmpty() || raza.isEmpty() || nombreMasc.isEmpty() || sexoMasc.isEmpty() || bday.isEmpty() /*|| nChip == 0*/){
            Toast.makeText(aniadirFichaActivity.this, R.string.fieldsNotCompleted, Toast.LENGTH_SHORT).show();
        }
        else if(bitmap.getWidth() > 2000 || bitmap.getHeight() > 2000){
            Toast.makeText(aniadirFichaActivity.this, R.string.tooBig, Toast.LENGTH_SHORT).show();
        }
        else{
            //assign an id to a pet
            boolean idcount;
            int id = new Random().nextInt(3000);
            do{
                id = new Random().nextInt(3000);
                idcount = dbHelper.checkPetId(String.valueOf(id));
            }
            while(idcount);
            //String idDue = dbHelper.getuserId(usern);
            dbHelper.insertPetData(String.valueOf(id), idDue, nombreMasc, sexoMasc, raza, desc,especie, bday, rabiaV,hepatitisV,leishmaniasisV, nChip, fechChip,locChip, localizacion, bitmap);
            Intent intent = new Intent(getApplicationContext(), PrincipalView.class);
            intent.putExtra("userInfo", idDue);
            startActivity(intent);
        }


    }

    public void goBack(View view){
        Intent intent = new Intent(getApplicationContext(), UserPetsActivity.class);
        intent.putExtra("userInfo", idDue);
        startActivity(intent);
    }

    public void goToMain(View view){
        Intent intent = new Intent(getApplicationContext(), PrincipalView.class);
        intent.putExtra("userInfo", idDue);
        startActivity(intent);
    }

    public void userInfoActivity(View view){
        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        intent.putExtra("userInfo", idDue);
        startActivity(intent);
    }
    public void goToSettings(View view){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        intent.putExtra("userInfo", idDue);
        startActivity(intent);
    }

}
