package model;

public class Seller {
    int shopkeeper_id;
    String username;
    String email;
    String password;
    String shopkeeper_name;
    String shop_name;
    String address;
    String phone_no;
    String image_directory;

    public String getImage_directory() {
        return image_directory;
    }

    public void setImage_directory(String image_directory) {
        this.image_directory = image_directory;
    }



    public int getShopkeeper_id() {
        return shopkeeper_id;
    }

    public void setShopkeeper_id(int shopkeeper_id) {
        this.shopkeeper_id = shopkeeper_id;
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

    public String getShopkeeper_name() {
        return shopkeeper_name;
    }

    public void setShopkeeper_name(String shopkeeper_name) {
        this.shopkeeper_name = shopkeeper_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
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

    public Seller(int shopkeeper_id, String username, String email, String password, String shopkeeper_name, String shop_name, String address, String phone_no, String image_directory) {
        this.shopkeeper_id = shopkeeper_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.shopkeeper_name = shopkeeper_name;
        this.shop_name = shop_name;
        this.address = address;
        this.phone_no = phone_no;
        this.image_directory = image_directory;
    }
}
