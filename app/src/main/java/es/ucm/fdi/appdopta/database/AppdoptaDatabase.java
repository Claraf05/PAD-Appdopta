package es.ucm.fdi.appdopta.database;

import android.provider.BaseColumns;

public class AppdoptaDatabase {

    private AppdoptaDatabase() {}

   //pa poner lo que salga en cada tabla
    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String USERNAME_C = "username";
        public static final String PASSWORD_C = "password";
    }

    public static class PetTable implements BaseColumns {
        public static final String TABLE_NAME = "pets";
        public static final String ID_OWNER_C = "id_owner";
        public static final String PETNAME_C = "petname";
        public static final String GENDER_C = "sex";
        public static final String RACE_C = "race";
        public static final String VACCINATIONS_C = "vaccinations";
        public static final String WEIGHT_C = "weight";

    }
}
