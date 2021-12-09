package com.example.tecno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Seller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    public Button updateSeller;
    public TableView<Seller> sellerTable;
    public TableColumn shopkprName;
    public TableColumn user;
    public TableColumn email;
    public TableColumn pass;
    public TableColumn shop;
    public TableColumn address;
    public TableColumn phoneNo;
    public TableColumn img_dir;
    public Button addSeller;
    public Button deleteSeller;
    String query ;
    PreparedStatement st;
    ResultSet rs;
    Seller seller =null;
    ObservableList<Seller> SellerList = FXCollections.observableArrayList();

    private Connection conn = HelloApplication.conn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loaddata();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query = "select * from shopkeeper;"; // query to check if id exists

        //creating and preparing statements
        try {
            st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);


        rs = st.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void refreshTable() throws SQLException {
SellerList.clear();
query="select * from Shopkeeper;";
        st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery();
        while (rs.next()){

            SellerList.add(new Seller(rs.getInt("shopkeeper_ID")
                    ,rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("shopkeeper_name"),
                    rs.getString("shop_name"),
                    rs.getString("address"),
                    rs.getString("phone_no"),
                    rs.getString("image_directory")));
            //sellerTable.setItems(SellerList);
        }
    }
    private void loaddata() throws SQLException {
            refreshTable();
         shopkprName.setCellValueFactory(new PropertyValueFactory<>("shopkeeper_name"));
         user.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        shop.setCellValueFactory(new PropertyValueFactory<>("shop_name"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNo.setCellValueFactory(new PropertyValueFactory<>("phone_no"));
        img_dir.setCellValueFactory(new PropertyValueFactory<>("image_directory"));


    }
}
