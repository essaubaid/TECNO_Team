package model;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class adminProductDetails {

    private String productName;
    private int productID;

    private String storage;
    private String ram;

    private String description;


    private Image img;
    private String imgURL;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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



    public int getProductID() {
        return productID;
    }

    public void setProductID(int ID) {
        this.productID = productID;
    }

    public void setData(@NotNull ResultSet rs) throws SQLException {
        rs.next();

        this.productID = rs.getInt(2);
        this.productName = rs.getString(3)+" "+rs.getString(4);
        if(rs.getString(8)!=null){
        Image image = new Image(getClass().getResourceAsStream(rs.getString(8)));
        this.img=image;}

        this.storage=rs.getString(5);
        this.ram=rs.getString(6);
        this.description=rs.getString(7);


    }
}
