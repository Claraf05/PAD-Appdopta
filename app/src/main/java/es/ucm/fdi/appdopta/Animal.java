package es.ucm.fdi.appdopta;

public class Animal {
    private String nombre;
    private String id;
    private String especie;
    private String ubicacion;

    public Animal(){

    }
    public Animal(String nombre, String id, String especie, String ubicacion){
        this.nombre = nombre;
        this.id = id;
        this.especie = especie;
        this.ubicacion = ubicacion;
    }

    public String getId(){return id;}
    public String getName(){return nombre;}
    public String getSpecies(){return especie;}
    public String getLocation(){return ubicacion;}

}
