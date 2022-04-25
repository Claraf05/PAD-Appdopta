package es.ucm.fdi.appdopta.database;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.BaseColumns;

public class AppdoptaDatabase {

    private AppdoptaDatabase() {}

   //pa poner lo que salga en cada tabla

    //tabla users
    public static class StandardUserTable implements BaseColumns {
        public static final String TABLE_NAME = "standard_users";
        //el id del user no se usa para buscar al usuario, para eso se una el USERNAME. +
        //El id sirve solo para localizar a las mascotas del user
        public static final String ID_C = "id";
        public static final String USERNAME_C = "username";
        public static final String PASSWORD_C = "password";
        //public static final String NAME_C = "name";

        public static final String PHONE_C = "phone";
        public static final String EMAIL_C = "email";
        public static final String OWNER_C = "is_owner";
        //buscar como hacer para añadir las mascotas que alguien quiera adoptar
        public static final String PETS2ADOPT_C = "pets2adopt";
    }

    //tabla dueños de mascotas
    public static class PetOwnerTable implements BaseColumns{
            public static final String TABLE_NAME = "pet_owners";
            public static final String ID_C = "id_user";
            public static final String PETNAME_C = "petname";
    }

    //tabla mascotas
    public static class PetTable implements BaseColumns {
        public static final String TABLE_NAME = "pets";
        public static final String ID_PET_C = "id_pet";
        public static final String DESCRIPTION_C = "description";
        public static final String ID_OWNER_C = "id_owner";
        public static final String PETNAME_C = "petname";
        public static final String GENDER_C = "gender";
        public static final String RACE_C = "race";
        public static final String SPECIES_C = "specie";
        public static final String BDAY_C = "bday";
        public static final String LOCAL_C = "local";
        public static final String IMAGE_C = "image";
         //una columna por cada tipiode vacuna
        public static final String VACC_RABIA_C = "vacc_rabia";
        public static final String VACC_HEPATITIS_C = "vacc_hepatitis";
        public static final String VACC_LEISHMANIASIS_C = "vacc_leishmaniasis";
        //1 columna por dato del chip(mirar la view de ficha
        public static final String CHIP_NUM_C = "chip_num";
        public static final String CHIP_DATE_C = "chip_date";
        public static final String CHIP_LOCATION_C = "chip_location";
    }
}
