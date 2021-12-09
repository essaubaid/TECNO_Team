package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.StringConverter;
import model.productDetails;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class addproductController implements Initializable{
    public TextField productStorage;
    public TextField productRam;
    public TextField productDescription;
    public Button productAdd;
    public TextField productName;
    public ComboBox<String> categoryName;
    public ComboBox<String> companyName;
    public Button productImgAdd;
    public ImageView productImg;
    public AnchorPane successAdd;
    private int prod_ID;

    private Connection conn =HelloApplication.conn;

    private int cat_ID;
    private int comp_ID;
    private EventHandler<ActionEvent> insertProduct;

    public int getComboBoxesID(String name,String table){

        String query;
        if(table=="category") {
            query = "select cat_ID from category where cat_name like '" + name + "';";
        }
        else
        {
            query = "select comp_ID from company where comp_name like '" + name + "';";
        }
        PreparedStatement st = null; //creating and preparing statements
        try {
            st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            System.out.println(st);
            ResultSet rs = st.executeQuery();
            rs.next();
                System.out.println(rs.getString(1));
            return rs.getInt(1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<String> setComboBoxes(String name){
        String query = "select * from "+name+";";// query to check if id exists

        PreparedStatement st = null; //creating and preparing statements
        try {
            st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = st.executeQuery();

            ArrayList<String> combo= new ArrayList<String>();
            while (rs.next()) {
                combo.add(rs.getString(2));
            }
            return combo;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryName.setItems(FXCollections.observableArrayList(setComboBoxes("category")));
        companyName.setItems(FXCollections.observableArrayList(setComboBoxes("company")));
        productAdd.setOnMouseClicked(clicked ->{

        });

        insertProduct = e -> {
            String query = "INSERT INTO `techno`.`product`\n" +
                    "(cat_ID,comp_ID,prod_name,prod_description,Ram,Storage) VALUES (?,?,?,?,?,?);"; // query to check if id exists

                    cat_ID=getComboBoxesID(categoryName.getValue(),"category");
            comp_ID=getComboBoxesID(companyName.getValue(),"company");

            PreparedStatement st = null; //creating and preparing statements
            try {
                st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                st.setInt(1,cat_ID);
                st.setInt(2,comp_ID);
                st.setString(3,productName.getText());
                st.setString(4,productDescription.getText());
                st.setString(5,productRam.getText());
                st.setString(6,productStorage.getText());
                System.out.println(st);
                System.out.println(st.executeUpdate());
                successAdd.setVisible(true);
                TimeUnit.SECONDS.sleep(2);
                successAdd.setVisible(false);
                query= "select max(prod_ID)from product ;";
                st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = st.executeQuery();
                prod_ID = rs.getInt(1);


            } catch (SQLException ex) {

                ex.printStackTrace();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        };

        EventHandler<ActionEvent> insertProductImg = e -> {


        };

                productAdd.setOnAction(insertProduct);
                   ;

    }
    public void setData(){

    }

    public void setProductImg() throws SQLException {
        String query= "select max(prod_ID)from product ;";
        try {
            PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = null;

            rs = st.executeQuery();

            prod_ID = rs.getInt(1);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image File");
            Window stage = new Stage();
            fileChooser.showOpenDialog(stage);
            File selected = fileChooser.showOpenDialog(null);
            if (selected != null) {
                Path from = Paths.get(selected.toURI());
                Path to = Paths.get("/img/");
                String imgURL ="/img/"+to.getFileName();
                CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES

                };
                query= "UPDATE techno.product SET image_directory = "+ "imgURL"+" WHERE prod_ID = "+prod_ID;
                st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                st.executeUpdate();
                try {
                    Files.copy(from, to, options);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
