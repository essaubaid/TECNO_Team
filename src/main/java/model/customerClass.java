package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class customerClass {
    int cust_ID;
    private String username;
    private String email;
    private String password;
    private String first_name;
    private String last_name;
    private String address;
    private String phone_no;
    private String profile_icon_URL;

    public int getCust_ID() {
        return cust_ID;
    }

    public void setCust_ID(int cust_ID) {
        this.cust_ID = cust_ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getProfile_icon_URL() {
        return profile_icon_URL;
    }

    public void setProfile_icon_URL(String profile_icon_URL) {
        this.profile_icon_URL = profile_icon_URL;
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

    public void setData(ResultSet rs) throws SQLException {
        while (rs.next()){
            setCust_ID(rs.getInt(1));
            setUsername(rs.getString(2));
            setEmail(rs.getString(3));
            setPassword(rs.getString(4));
            setFirst_name(rs.getString(5));
            setLast_name(rs.getString(6));
            setAddress(rs.getString(7));
            setPhone_no(rs.getString(8));
            setProfile_icon_URL(rs.getString(9));
        }



    }
}
