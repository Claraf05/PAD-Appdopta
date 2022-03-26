package es.ucm.fdi.appdopta.database;

import android.content.ContentValues;
import android.content.Context;
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
                    PetTable.WEIGHT_C + "TEXT NOT NULL,);";

    private static final String DELETE_USER_TABLE = "DROP TABLE IF EXISTS " + UserTable.TABLE_NAME;
    private static final String DELETE_PET_TABLE = "DROP TABLE IF EXISTS " + PetTable.TABLE_NAME;

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
}
