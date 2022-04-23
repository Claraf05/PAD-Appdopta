package es.ucm.fdi.appdopta.features.user;

public class UserInfo {

    //TODO
    private String id;
    private String username;
    private String passw;
    private int phone;
    private String email;

    //constructor vacio
    public UserInfo() {

    }

    public UserInfo(String id, String username, String passw, int phone, String email){
        this.id = id;
        this.username = username;
        this.passw = passw;
        this.phone = phone;
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassw() {
        return passw;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
