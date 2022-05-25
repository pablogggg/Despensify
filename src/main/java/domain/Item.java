package domain;

//Esta clase representa una entidad: la tabla item de MySQL. A 
//partir de ella haremos los Insert, Update, Delete y Select.
public class Item {

    private int itemId;
    private int userId;
    private String productname;
    private String quantity;
    private String measurement;

    //Constructor vacio
    public Item() {
    }

    //Constructor que sirve para eliminar usando solo el id de item
    public Item(int itemId) {
        this.itemId = userId;
    }

    //Constructor solo para crear nuevos objetos, no necesitamos
    //agregar un id_item porque se genera solo
    public Item(String productname, String quantity, String measurement) {
        this.productname = productname;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    //Constructor con todos los campos para MODIFICAR la tabla
    public Item(int itemId, String productname, String quantity, String measurement) {
        this.itemId = itemId;
        this.productname = productname;
        this.quantity = quantity;
        this.measurement = measurement;
    }

    //Getters y setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
    
    //Metodo toString

    @Override
    public String toString() {
        return "Item{" + "itemId=" + itemId + ", userId=" + userId + ", productname=" + productname + ", quantity=" + quantity + ", measurement=" + measurement + '}';
    }
    
}