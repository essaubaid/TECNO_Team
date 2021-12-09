package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.tecno.HelloApplication.conn;

public class sellerHomePageController {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Label Title;

    sellerClass seller;
    private List<ProductTileView> tiles = new ArrayList<>();

    private List getData() throws SQLException {
        List<ProductTileView> tiles = new ArrayList<>();

        String query = "select * from (select @input_int:=? p) parm , techno.view_seller_stock_tiles s;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setInt(1, seller.getSeller_id());
        ResultSet rs = st.executeQuery();


        while (rs.next()) {
            ProductTileView tile = new ProductTileView();
            tile.setProductName(rs.getString(3) + " " + rs.getString(4));

            tile.setProductID(rs.getInt(2));

            tile.setImgURL(rs.getString(5));

//            tile.setImg(new Image(getClass().getResourceAsStream("/img/google_pixel_2_XL.jpg")));

            tiles.add(tile);

        }

        return tiles;
    }


    public void switchToProfile(ActionEvent event) throws IOException, SQLException {
        Title.setText("My Profile");
        grid.getChildren().removeAll(grid.getChildren());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("view-seller-profile.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        sellerProfileController profileController = fxmlLoader.getController();

        System.out.println(seller.getSeller_id()+"setData");
        profileController.setData(getSellerData());



        grid.add(anchorPane, 1, 1); //(child,column,row)
        //set grid width
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);

        //set grid height
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);

        GridPane.setMargin(anchorPane, new Insets(10, 5, 10, 70));
    }

    public sellerClass getSellerData() throws SQLException {
        sellerClass adminClass = new sellerClass();

        String query = "select * from (select @input_int:=? p) parm , techno.view_seller_profile_detail s;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setInt(1, this.seller.getSeller_id());
        System.out.println(st);
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt(2));
            adminClass.setSeller_id(rs.getInt(2));
            adminClass.setUsername(rs.getString(3));
            adminClass.setEmail(rs.getString(4));
            adminClass.setPassword(rs.getString(5));
            adminClass.setName(rs.getString(6));
            adminClass.setShop_name(rs.getString(7));
            adminClass.setAddress(rs.getString(8));
            adminClass.setPhone_no(rs.getString(9));
            adminClass.setProfile_icon_URL(rs.getString(10));
        }

        return adminClass;
    }


    public void initialized() {
        grid.getChildren().removeAll(grid.getChildren());
        tiles.removeAll(tiles);
        try {
            tiles.addAll(getData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int column = 0;
        int row = 1;

        for (int i = 0; i < tiles.size(); i++) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Tile.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                TilesController tilesController = fxmlLoader.getController();

                tilesController.setData(tiles.get(i));

                if (column == 3) {
                    column = 0;
                    row++;
                }

                int finalI = i;
                int finalI1 = i;
                anchorPane.setOnMouseClicked(
                        click -> {
                            try {

                                Title.setText("Product");
                                grid.getChildren().removeAll(grid.getChildren());

                                FXMLLoader infxmlLoader = new FXMLLoader();
                                infxmlLoader.setLocation(getClass().getResource("stock-details.fxml"));
                                AnchorPane inanchorPane = infxmlLoader.load();
//                                ProductPageController productPageController = infxmlLoader.getController();

                                String query = "select * from (select @input_int:=? p) parm , techno.view_seller_stock_details_tile s;"; // query to check if id exists
                                tiles.get(finalI);
                                PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                st.setInt(1, tiles.get(finalI).getProductID());
                                ResultSet rs = st.executeQuery();

                                stockTile stock = new stockTile();
                                stock.setData(rs);


                                stockTileController stockTileController = infxmlLoader.getController();
                                stockTileController.setData(stock);


                                grid.add(inanchorPane, 1, 1); //(child,column,row)
                                //set grid width
                                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                                grid.setMaxWidth(Region.USE_PREF_SIZE);

                                //set grid height
                                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                                grid.setMaxHeight(Region.USE_PREF_SIZE);

                                GridPane.setMargin(inanchorPane, new Insets(10, 5, 10, 70));
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                );

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10, 5, 10, 5));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
