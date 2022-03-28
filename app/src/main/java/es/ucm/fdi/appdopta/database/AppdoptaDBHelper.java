package es.ucm.fdi.appdopta.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.UserTable;
import es.ucm.fdi.appdopta.database.AppdoptaDatabase.PetTable;

public class AppdoptaDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Appdopta.db";
    private static final int DB_VERSION = 1;

    // If you change the database schema, you must increment the database version.

    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + UserTable.TABLE_NAME + " (" +
                    UserTable.USERNAME_C + " TEXT UNIQUE NOT NULL PRIMARY KEY," +
                    UserTable.PASSWORD_C + " TEXT NOT NULL);";

    private static final String CREATE_PET_TABLE =
            "CREATE TABLE " + PetTable.TABLE_NAME + " (" +
                    PetTable.ID_OWNER_C + " TEXT NOT NULL PRIMARY KEY," +
                    PetTable.PETNAME_C + " TEXT NOT NULL," +
                    PetTable.GENDER_C + " TEXT NOT NULL," +
                    PetTable.RACE_C + " TEXT NOT NULL," +
                    PetTable.VACCINATIONS_C + " TEXT NOT NULL," +
                    PetTable.WEIGHT_C + "TEXT NOT NULL);";

    private static final String DELETE_USER_TABLE = "DROP TABLE IF EXISTS " +
            UserTable.TABLE_NAME;
    private static final String DELETE_PET_TABLE = "DROP TABLE IF EXISTS " +
            PetTable.TABLE_NAME;

    // creating a constructor for our database handler.
    public AppdoptaDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_USER_TABLE);
        db.execSQL(DELETE_PET_TABLE);
        onCreate(db);
    }

    public boolean insertUserData(String username, String passw){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTable.USERNAME_C, username);
        contentValues.put(UserTable.PASSWORD_C, passw);

        long result = db.insert(UserTable.TABLE_NAME, null, contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean insertPetData(String id, String name, String gend, String race, String vacc, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PetTable.ID_OWNER_C, id);
        contentValues.put(PetTable.PETNAME_C, name);
        contentValues.put(PetTable.GENDER_C, gend);
        contentValues.put(PetTable.RACE_C, race);
        contentValues.put(PetTable.VACCINATIONS_C, vacc);
        contentValues.put(PetTable.WEIGHT_C, weight);

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
        Cursor cursor = db.rawQuery("Select * from "+UserTable.TABLE_NAME+" where "+UserTable.USERNAME_C+" = ?", new String[] {user});
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
