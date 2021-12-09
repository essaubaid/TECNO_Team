package model;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class stockTile {
    private int productID;
    private String productName;
    private String storage;
    private String ram;
    private String description;
    private String Warranty;
    private  boolean inStock;
    private Image img;
    private String imgURL;
    private double price;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

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

    public String getWarranty() {
        return Warranty;
    }

    public void setWarranty(String warranty) {
        Warranty = warranty;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setData(ResultSet rs) throws SQLException {
        rs.next();

        productID = rs.getInt(2);
        this.productName = rs.getString(3);
        this.storage=rs.getString(4);
        this.ram=rs.getString(5);
        this.description=rs.getString(6);
        System.out.println(rs.getString(9)+"PPPPPP");
        if(rs.getString(9)!=null){
            Image image = new Image(getClass().getResourceAsStream(rs.getString(9)));
            this.img=image;}
        this.price=rs.getInt(10);
        this.inStock= rs.getBoolean(8);
        if(rs.getBoolean(7)){
            this.Warranty="1 Year";
        }
        else{
            this.Warranty="Not Available";
        }

    }
}
