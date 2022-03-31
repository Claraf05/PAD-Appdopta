package es.ucm.fdi.appdopta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.StandardUserTable;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.PetTable;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.PetOwnerTable;

public class AppdoptaDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Appdopta.db";
    private static final int DB_VERSION = 1;

    // If you change the database schema, you must increment the database version.

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + StandardUserTable.TABLE_NAME + " (" +
                    StandardUserTable.ID_C + " INTEGER UNIQUE NOT NULL PRIMARY KEY," +
                    StandardUserTable.USERNAME_C + " TEXT UNIQUE NOT NULL," +
                    StandardUserTable.PASSWORD_C + " TEXT NOT NULL," +
                    StandardUserTable.NAME_C + " TEXT NOT NULL," +
                    StandardUserTable.PHONE_C + " INTEGER NOT NULL," +
                    StandardUserTable.EMAIL_C + " TEXT NOT NULL," +
                    StandardUserTable.OWNER_C + " INTEGER NOT NULL," + //0 o 1 if the user is a pet owner
                    StandardUserTable.PETS2ADOPT_C + " BLOB);";


    //for users with StandardUserTable.OWNER_C = 1;
    private static final String CREATE_PET_OWNER_TABLE =
            "CREATE TABLE " + PetOwnerTable.TABLE_NAME + " (" +
                    PetOwnerTable.ID_C + " INTEGER UNIQUE NOT NULL PRIMARY KEY," +
                    PetOwnerTable.POSTCOD_C + "INTEGER NOT NULL);";


    private static final String CREATE_PET_TABLE =
            "CREATE TABLE " + PetTable.TABLE_NAME + " (" +
                    //INFO OWNER
                    PetTable.ID_PET_C + " INTEGER UNIQUE NOT NULL PRIMARY KEY," +
                    PetTable.ID_OWNER_C + " INTEGER NOT NULL," +

                    //INFO PET
                    PetTable.PETNAME_C + " TEXT NOT NULL," +
                    PetTable.GENDER_C + " TEXT NOT NULL," +
                    PetTable.SPECIES_C + " TEXT NOT NULL," +
                    PetTable.RACE_C + " TEXT NOT NULL," +
                    PetTable.BDAY_C + " TEXT NOT NULL," +
                    PetTable.DESCRIPTION_C + " TEXT NOT NULL," +

                    //TODAS LAS VACUNAS SON INTEGER QUE PUEDEN SER 1 O 0
                    PetTable.VACC_RABIA_C + " INTEGER NOT NULL," +
                    PetTable.VACC_PARVOVIRUS_C + " INTEGER NOT NULL," +
                    PetTable.VACC_MOQUILLO_C + " INTEGER NOT NULL," +
                    PetTable.VACC_POLIVALENTE_C + " INTEGER NOT NULL," +

                    //INFO CHIP
                    PetTable.CHIP_NUM_C + " INTEGER UNIQUE NOT NULL," +
                    PetTable.CHIP_DATE_C + " TEXT NOT NULL," +
                    PetTable.CHIP_LOCATION_C + "TEXT NOT NULL);";


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

    public boolean insertUserData(int id, String username, String password,String name, int phone,String email,int is_owner){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(StandardUserTable.ID_C, id);
        contentValues.put(StandardUserTable.USERNAME_C, username);
        contentValues.put(StandardUserTable.PASSWORD_C, password);
        contentValues.put(StandardUserTable.NAME_C, name);
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

    public boolean insertPetOwnerData(int id_user, int post_code) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetOwnerTable.ID_C, id_user);
        contentValues.put(PetOwnerTable.POSTCOD_C, post_code);

        long result = db.insert(PetTable.TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean insertPetData(int id, int id_owner, String petname, String gender, String race, String specie, String bday, int vacc_rabia, int vacc_parvovirus, int vacc_moquillo, int vacc_polivalente, int chip_num, int chip_date, int chip_loc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetTable.ID_OWNER_C, blaba);
        contentValues.put(PetTable.PETNAME_C, name);
        contentValues.put(PetTable.GENDER_C, gend);
        contentValues.put(PetTable.RACE_C, race);

        long result = db.insert(PetTable.TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
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
        Cursor cursor = db.rawQuery("Select * from "+UserTable.TABLE_NAME+" where "+UserTable.USERNAME_C+" = ? and "+UserTable.PASSWORD_C+" = ?", new String[] {user,passw});

        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}
