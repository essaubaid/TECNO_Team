package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class sellerClass {
    private int seller_id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String shop_name;
    private String address;
    private String phone_no;
    private String profile_icon_URL;

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
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


    public void setData(ResultSet rs) throws SQLException {
        while (rs.next()){
            setSeller_id(rs.getInt(1));
            setUsername(rs.getString(2));
            setEmail(rs.getString(3));
            setPassword(rs.getString(4));
            setName(rs.getString(5));
            setShop_name(rs.getString(6));
            setAddress(rs.getString(7));
            setPhone_no(rs.getString(8));
            setProfile_icon_URL(rs.getString(9));
        }



    }
}
