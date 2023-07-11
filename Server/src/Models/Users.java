package Models;

import java.io.Serializable;

public class Users  implements Serializable{
    int id ;
    String name ;
    String email;
    String password;
    String address;
    String phone ;
    String date;
    String code ;
    String role;
    public Users() {
        super();
    }
    public Users(int id, String name, String email, String password, String address, String phone, String date,
            String code, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.code = code;
        this.role = role;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString() {
        return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
                + address + ", phone=" + phone + ", date=" + date + ", code=" + code + ", role=" + role + "]";
    };
    
}
