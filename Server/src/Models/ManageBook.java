package Models ;

import java.io.Serializable;

public class ManageBook implements Serializable
{
    int id ;
    int user_id;
    String registration_date  ;
    String book_name ;
    int book_id;
    int quantity;
    String borro_code;
    String MTS;
    public ManageBook()
    {
        super();
    }
    public ManageBook(int id, int user_id, String registration_date, String book_name, int book_id, int quantity,
            String borro_code) {
        this.id = id;
        this.user_id = user_id;
        this.registration_date = registration_date;
        this.book_name = book_name;
        this.book_id = book_id;
        this.quantity = quantity;
        this.borro_code = borro_code;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getRegistration_date() {
        return registration_date;
    }
    public void setRegistration_date(String registration_date) {
        this.registration_date = registration_date;
    }
    public String getBook_name() {
        return book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    public int getBook_id() {
        return book_id;
    }
    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getBorro_code() {
        return borro_code;
    }
    public void setBorro_code(String borro_code) {
        this.borro_code = borro_code;
    }
    @Override
    public String toString() {
        return "ManageBook [id=" + id + ", user_id=" + user_id + ", registration_date=" + registration_date
                + ", book_name=" + book_name + ", book_id=" + book_id + ", quantity=" + quantity + ", borro_code="
                + borro_code + "]";
    }
    

}