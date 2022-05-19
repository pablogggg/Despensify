package domain;

//Esta clase representa una entidad: la tabla user de MySQL. A 
//partir de ella haremos los Insert, Update, Delete y Select.

public class User {
    private int userId; 
    private String username;
    private String password;
    
    //Constructor vacio
    public User(){
    }
    
    //Constructor que sirve para deletear usando solo el id de usuario
    public User(int userId){
        this.userId = userId;
    }
    
    //Constructor solo para crear nuevos objetos, no necesitamos
    //agregar un id_user porque se genera solo
    public User(String user, String password){
        this.username = user;
        this.password = password;
    }
    
    //Constructor con todos los campos para MODIFICAR la tabla
    public User(int userId, String user, String password){
        this. userId = userId;
        this.username = user;
        this.password = password;
    }
    
    //Getters y Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //Metodo ToString
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", user=" + username + ", password=" + password + '}';
    }
}
