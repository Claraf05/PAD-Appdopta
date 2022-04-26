package es.ucm.fdi.appdopta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import es.ucm.fdi.appdopta.Animal;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.StandardUserTable;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.PetTable;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.PetOwnerTable;
import es.ucm.fdi.appdopta.features.register.RegisterActivity;
import es.ucm.fdi.appdopta.features.user.UserInfo;

public class AppdoptaDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Appdopta.db";
    private static final int DB_VERSION = 3;

    // If you change the database schema, you must increment the database version.

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + StandardUserTable.TABLE_NAME + " (" +
                    StandardUserTable.ID_C + " TEXT UNIQUE NOT NULL PRIMARY KEY," +
                    StandardUserTable.USERNAME_C + " TEXT UNIQUE NOT NULL," +
                    StandardUserTable.PASSWORD_C + " TEXT NOT NULL," +
                    //StandardUserTable.NAME_C + " TEXT NOT NULL," +
                    StandardUserTable.PHONE_C + " INTEGER NOT NULL," +
                    StandardUserTable.EMAIL_C + " TEXT NOT NULL," +
                    StandardUserTable.OWNER_C + " INTEGER NOT NULL," + //0 o 1 if the user is a pet owner
                    StandardUserTable.PETS2ADOPT_C + " BLOB);";


    //for users with StandardUserTable.OWNER_C = 1;
    private static final String CREATE_PET_OWNER_TABLE =
            "CREATE TABLE " + PetOwnerTable.TABLE_NAME + " (" +
                    PetOwnerTable.ID_C + " TEXT UNIQUE NOT NULL PRIMARY KEY);";


    private static final String CREATE_PET_TABLE =
            "CREATE TABLE " + PetTable.TABLE_NAME + " (" +
                    //INFO OWNER
                    PetTable.ID_PET_C + " TEXT UNIQUE NOT NULL PRIMARY KEY," +
                    PetTable.ID_OWNER_C + " TEXT NOT NULL," +

                    //INFO PET
                    PetTable.PETNAME_C + " TEXT NOT NULL," +
                    PetTable.GENDER_C + " TEXT NOT NULL," +
                    PetTable.SPECIES_C + " TEXT NOT NULL," +
                    PetTable.RACE_C + " TEXT NOT NULL," +
                    PetTable.BDAY_C + " TEXT NOT NULL," +
                    PetTable.DESCRIPTION_C + " TEXT NOT NULL," +
                    PetTable.LOCAL_C + " TEXT NOT NULL, " +
                    PetTable.IMAGE_C + " BLOB," +

                    //TODAS LAS VACUNAS SON INTEGER QUE PUEDEN SER 1 O 0
                    PetTable.VACC_RABIA_C + " INTEGER NOT NULL," +
                    PetTable.VACC_HEPATITIS_C + " INTEGER NOT NULL," +
                    PetTable.VACC_LEISHMANIASIS_C + " INTEGER NOT NULL," +

                    //INFO CHIP
                    PetTable.CHIP_NUM_C + " INTEGER UNIQUE NOT NULL," +
                    PetTable.CHIP_DATE_C + " TEXT NOT NULL," +
                    PetTable.CHIP_LOCATION_C + " TEXT NOT NULL);";


    private static final String DELETE_USER_TABLE = "DROP TABLE IF EXISTS " +
            StandardUserTable.TABLE_NAME;
    private static final String DELETE_PET_TABLE = "DROP TABLE IF EXISTS " +
            PetTable.TABLE_NAME;
    private static final String DELETE_PET_OWNER_TABLE = "DROP TABLE IF EXISTS " +
            PetOwnerTable.TABLE_NAME;

    // creating a constructor for our database handler.
    public AppdoptaDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PET_TABLE);
        db.execSQL(CREATE_PET_OWNER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_USER_TABLE);
        db.execSQL(DELETE_PET_TABLE);
        db.execSQL(DELETE_PET_OWNER_TABLE);
        onCreate(db);
    }

    public boolean insertUserData(String id, String username, String password,/*String name,*/ int phone,String email,int is_owner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StandardUserTable.ID_C, id);
        contentValues.put(StandardUserTable.USERNAME_C, username);
        contentValues.put(StandardUserTable.PASSWORD_C, password);
        //contentValues.put(StandardUserTable.NAME_C, name);
        contentValues.put(StandardUserTable.PHONE_C, phone);
        contentValues.put(StandardUserTable.EMAIL_C, email);
        contentValues.put(StandardUserTable.OWNER_C, is_owner);

        long result = db.insert(StandardUserTable.TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean insertPetOwnerData(String id_user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetOwnerTable.ID_C, id_user);

        long result = db.insert(PetTable.TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean insertPetData(String id, String id_owner, String petname, String gender, String race, String desc, String specie, String bday, int vacc_rabia, int vacc_hepatitis, int vacc_leishmaniasis, int chip_num, String chip_date, String chip_loc, String localizacion, Bitmap bitmap) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PetTable.ID_PET_C, id);
        contentValues.put(PetTable.ID_OWNER_C, id_owner);
        contentValues.put(PetTable.PETNAME_C, petname);
        contentValues.put(PetTable.GENDER_C, gender);
        contentValues.put(PetTable.RACE_C, race);
        contentValues.put(PetTable.SPECIES_C, specie);
        contentValues.put(PetTable.BDAY_C, bday);

        //image
        ByteArrayOutputStream baos = new ByteArrayOutputStream(20480);
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos);
        byte[] blob = baos.toByteArray();
        contentValues.put(PetTable.IMAGE_C, blob);

        //vaccinations
        contentValues.put(PetTable.VACC_RABIA_C, vacc_rabia);
        contentValues.put(PetTable.VACC_HEPATITIS_C, vacc_hepatitis);
        contentValues.put(PetTable.VACC_LEISHMANIASIS_C, vacc_leishmaniasis);

        //chip info
        contentValues.put(PetTable.CHIP_NUM_C, chip_num);
        contentValues.put(PetTable.CHIP_DATE_C, chip_date);
        contentValues.put(PetTable.CHIP_LOCATION_C, chip_loc);

        contentValues.put(PetTable.DESCRIPTION_C, desc);
        contentValues.put(PetTable.LOCAL_C, localizacion);

        long result = db.insert(PetTable.TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
    public Bitmap buscarImagen(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT "+ PetTable.IMAGE_C+" FROM "+ PetTable.TABLE_NAME+ " where " + PetTable.ID_PET_C +" = ?", new String[] {id});
        Bitmap bitmap = null;
        if(cursor.moveToFirst()){
            byte[] blob = cursor.getBlob(0);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            bitmap = BitmapFactory.decodeStream(bais);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        return bitmap;
    }
    public ArrayList<Animal> readListPetData() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor petsCursor = db.rawQuery("SELECT * FROM " + PetTable.TABLE_NAME, null);

        ArrayList<Animal> petsList = new ArrayList<>();

        if (petsCursor.moveToFirst()) {
            do {
                petsList.add(new Animal(petsCursor.getString(2),  // Columna nombre
                petsCursor.getString(0),  // Columna id
                petsCursor.getString(4),  // Columna especie
                petsCursor.getString(8))); // Columna ubicación

            } while (petsCursor.moveToNext());
        }
        petsCursor.close();
        return petsList;
    }

    public ArrayList<Animal> filterQuery(String especie, String raza, String ubicacion) {
        SQLiteDatabase db = this.getReadableDatabase();

        /* CONTROLAR LA QUERY CUANDO HAY CAMPOS NULOS*/
        Cursor petsCursor = db.rawQuery("SELECT * FROM " + PetTable.TABLE_NAME+ " WHERE "
                + PetTable.SPECIES_C+ " = ? and "
                + PetTable.RACE_C+ " = ? and "
                + PetTable.LOCAL_C+ " = ?", new String[] {especie, raza, ubicacion});

        ArrayList<Animal> petsList = new ArrayList<>();

        if (petsCursor.moveToFirst()) {
            do {
                petsList.add(new Animal(petsCursor.getString(2),  // Columna nombre
                        petsCursor.getString(0),  // Columna id
                        petsCursor.getString(4),  // Columna especie
                        petsCursor.getString(8))); // Columna ubicación

            } while (petsCursor.moveToNext());
        }
        petsCursor.close();
        return petsList;
    }

    public ArrayList<Animal> readListPetDataByUserId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor petsCursor = db.rawQuery("SELECT * FROM " + PetTable.TABLE_NAME + " where "+ PetTable.ID_OWNER_C+ " = ?", new String[] {id});

        ArrayList<Animal> petsList = new ArrayList<>();

        if (petsCursor.moveToFirst()) {
            do {
                petsList.add(new Animal(petsCursor.getString(2),  // Columna nombre
                        petsCursor.getString(0),  // Columna id
                        petsCursor.getString(4),  // Columna especie
                        petsCursor.getString(8))); // Columna ubicación

            } while (petsCursor.moveToNext());
        }
        petsCursor.close();
        return petsList;
    }

    public boolean checkUserName(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+StandardUserTable.TABLE_NAME+" where "+StandardUserTable.USERNAME_C+" = ?", new String[] {user});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkUserPassword(String user, String passw){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+StandardUserTable.TABLE_NAME+" where "+StandardUserTable.USERNAME_C+" = ? and "+StandardUserTable.PASSWORD_C+" = ?", new String[] {user,passw});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkUserId(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+StandardUserTable.TABLE_NAME+" where "+StandardUserTable.ID_C+" = ?", new String[] {id});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkPetId(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from "+PetTable.TABLE_NAME+" where "+PetTable.ID_PET_C+" = ?", new String[] {id});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void buscarUsuario(UserInfo usuario, String userId){

        SQLiteDatabase db = this.getReadableDatabase();
        String name;
        String passw;
        int phone;
        String email;
        String[] parametros = {userId};
        String[] campos = {StandardUserTable.USERNAME_C, StandardUserTable.PASSWORD_C, StandardUserTable.PHONE_C, StandardUserTable.EMAIL_C};


        Cursor c = db.query(StandardUserTable.TABLE_NAME, campos, StandardUserTable.ID_C+"=?", parametros, null, null, null);
        c.moveToFirst();
        //NO SE PORQUE FUNCIONA PERO FUNCIONA, NO TOCAR
        name = c.getString(0);
        passw = c.getString(1);
        phone = c.getInt(2);
        email = c.getString(3);
        c.close();

        usuario.setId(userId);
        usuario.setUsername(name);
        usuario.setPassw(passw);
        usuario.setEmail(email);
        usuario.setPhone(phone);
    }

    public String getUserIdByName(String userName) {
        String id = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String[] parametros = {userName};
        String[] campos = {StandardUserTable.ID_C};

        Cursor c = db.query(StandardUserTable.TABLE_NAME, campos, StandardUserTable.USERNAME_C+"=?", parametros, null, null, null);
        c.moveToFirst();

        id = c.getString(0);
        c.close();

        return id;
    }

    public void updateUserName(String id, String name){
        //TODO ---------------------------
        SQLiteDatabase db = this.getReadableDatabase();
        String[] parametros = {id};
        ContentValues contentValues = new ContentValues();
        contentValues.put(StandardUserTable.USERNAME_C, name);
        db.update(StandardUserTable.TABLE_NAME, contentValues, StandardUserTable.ID_C+"=?", parametros);
    }

    public void updateUserPw(String id, String pw){
        //TODO ---------------------------
        SQLiteDatabase db = this.getReadableDatabase();
        String[] parametros = {id};
        ContentValues contentValues = new ContentValues();
        contentValues.put(StandardUserTable.PASSWORD_C, pw);
        db.update(StandardUserTable.TABLE_NAME, contentValues, StandardUserTable.ID_C+"=?", parametros);

    }

    public void updateUserPhone(String id, int ph){
        //TODO ---------------------------
        SQLiteDatabase db = this.getReadableDatabase();
        String[] parametros = {id};
        ContentValues contentValues = new ContentValues();
        contentValues.put(StandardUserTable.PHONE_C, ph);
        db.update(StandardUserTable.TABLE_NAME, contentValues, StandardUserTable.ID_C+"=?", parametros);
    }

    public void updateUserEmail(String id, String email){
        //TODO ---------------------------
        SQLiteDatabase db = this.getReadableDatabase();
        String[] parametros = {id};
        ContentValues contentValues = new ContentValues();
        contentValues.put(StandardUserTable.EMAIL_C, email);
        db.update(StandardUserTable.TABLE_NAME, contentValues, StandardUserTable.ID_C+"=?", parametros);
    }
}
