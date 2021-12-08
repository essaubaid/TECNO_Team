package model;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class productDetails {

    private String productName;

    private int price;
    private String storage;
    private String ram;
    private String warranty;
    private String description;

    private String sellerName;
    private String shopName;
    private String phoneNo;
    private String shopNo;

    private Image img;
    private String imgURL;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }


    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSellerDetails(@NotNull ResultSet rs) throws SQLException {
        rs.next();
        this.sellerName=rs.getString(2);

        this.shopName= rs.getString(3);
        this.shopNo=rs.getString(4);
        this.phoneNo="0"+rs.getString(5);
    }

    public void setData(@NotNull ResultSet rs) throws SQLException {
        rs.next();


        this.productName = rs.getString(3)+" "+rs.getString(4);
//        System.out.println(rs.getString(10));
        Image image = new Image(getClass().getResourceAsStream(rs.getString(10)));
        this.img=image;
        this.price=rs.getInt(5);
        this.storage=rs.getString(6);
        this.ram=rs.getString(7);
        this.description=rs.getString(9);
        if(rs.getBoolean(8)){
            this.warranty="1 Year";
        }
        else{
            this.warranty="Not Available";
        }

    }
}
