package es.ucm.fdi.appdopta;

public class Animal {
    private String nombre;
    private String id;
    private String especie;
    private String ubicacion;
    private String idOwner, gender, race, bday, desc, rabia, hepa, leis, chipNum, chipDate, chipLoc;

    public Animal(){

    }
    public Animal(String id, String idOwner, String nombre, String gender, String especie, String race, String bday, String desc, String loc, String rabia, String hepa, String leis, String chipNum, String chipDate, String chipLoc){
        this.id = id;
        this.idOwner =idOwner;
        this.nombre = nombre;
        this.gender = gender;
        this.especie = especie;
        this.race = race;
        this.bday = bday;
        this.desc = desc;
        this.ubicacion = loc;
        this.rabia = rabia;
        this.hepa = hepa;
        this.leis = leis;
        this.chipNum = chipNum;
        this.chipDate = chipDate;
        this.chipLoc = chipLoc;
    }

    public String getId(){return id;}
    public String getName(){return nombre;}
    public String getSpecies(){return especie;}
    public String getLocation(){return ubicacion;}

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public String getBday() {
        return bday;
    }

    public String getDesc() {
        return desc;
    }

    public String getRabia() {
        return rabia;
    }

    public String getHepa() {
        return hepa;
    }

    public String getLeis() {
        return leis;
    }

    public String getChipNum() {
        return chipNum;
    }

    public String getChipDate() {
        return chipDate;
    }

    public String getChipLoc() {
        return chipLoc;
    }
}
