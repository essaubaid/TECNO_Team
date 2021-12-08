package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class adminClass {

    int admin_ID;
    String username;
    String email;
    String password;
    String first_name;
    String last_name;
    String address;
    String phone_no;
    String admin_icon_URL;

    public int getAdmin_ID() {
        return admin_ID;
    }

    public void setAdmin_ID(int admin_ID) {
        this.admin_ID = admin_ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAdmin_icon_URL() {
        return admin_icon_URL;
    }

    public void setAdmin_icon_URL(String admin_icon_URL) {
        this.admin_icon_URL = admin_icon_URL;
    }

    public void setData(ResultSet rs) throws SQLException {
        while (rs.next()){
            setAdmin_ID(rs.getInt(1));
            setUsername(rs.getString(2));
            setEmail(rs.getString(3));
            setPassword(rs.getString(4));
            setFirst_name(rs.getString(5));
            setLast_name(rs.getString(6));
            setAddress(rs.getString(7));
            setPhone_no(rs.getString(8));
            setAdmin_icon_URL(rs.getString(9));
        }



    }
}
