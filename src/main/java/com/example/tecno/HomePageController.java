package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.ProductTileView;
import model.adminClass;
import model.customerClass;
import model.productDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    @FXML
    private Label Title;

    @FXML
    private MenuButton dropDown;

    @FXML
    private Button favoritesButton;

    @FXML
    private Button letSeeButton;

    @FXML
    private AnchorPane background;

    private List<ProductTileView> tiles = new ArrayList<>();
    customerClass customer;
    private int count = 0;

    Connection conn = HelloApplication.conn;

    public void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private List getData() throws SQLException {
        List<ProductTileView> tiles = new ArrayList<>();

        String query = "select * from product_listing;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        ResultSet rs = st.executeQuery();


        while (rs.next()) {
            ProductTileView tile = new ProductTileView();
            tile.setProductName(rs.getString(2) + " " + rs.getString(3));

            tile.setProductID(rs.getInt(1));

            tile.setImgURL(rs.getString(4));

//            tile.setImg(new Image(getClass().getResourceAsStream("/img/google_pixel_2_XL.jpg")));

            tiles.add(tile);

            count++;
           /* tile.setProductName("Yeezys");
            tile.setSellerName("by Pawan Kumar");
            tile.setPrice(90000);
            tile.setImgURL("/img/Adidas-Yeezy.png");

            tiles.add(tile);
            tile = new ProductTileView();*/
        }

        return tiles;
    }

    private List getFavoritesData() throws SQLException {
        List<ProductTileView> tiles = new ArrayList<>();

        String query = "select * from (select @input_int:=? p) parm , techno.view_favorite_product_listing s;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setInt(1, customer.getCust_ID());
        ResultSet rs = st.executeQuery();


        while (rs.next()) {
            ProductTileView tile = new ProductTileView();
            tile.setProductName(rs.getString(3) + " " + rs.getString(4));

            tile.setProductID(rs.getInt(2));

            tile.setImgURL(rs.getString(5));

//            tile.setImg(new Image(getClass().getResourceAsStream("/img/google_pixel_2_XL.jpg")));

            tiles.add(tile);

            count++;
        }
        System.out.println(tiles.size());

        return tiles;
    }

    public customerClass getCustomerData() throws SQLException {
        customerClass adminClass = new customerClass();

        String query = "select * from (select @input_int:=? p) parm , techno.view_customer_profile_detail s;"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query,ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setInt(1, this.customer.getCust_ID());
        System.out.println(st);
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt(2));
            adminClass.setCust_ID(rs.getInt(2));
            adminClass.setUsername(rs.getString(3));
            adminClass.setEmail(rs.getString(4));
            adminClass.setPassword(rs.getString(5));
            adminClass.setFirst_name(rs.getString(6));
            adminClass.setLast_name(rs.getString(7));
            adminClass.setAddress(rs.getString(8));
            adminClass.setPhone_no(rs.getString(9));
            adminClass.setProfile_icon_URL(rs.getString(10));
        }

        return adminClass;
    }

    public void switchToProfile(ActionEvent event) throws IOException, SQLException {
        Title.setText("My Profile");
        grid.getChildren().removeAll(grid.getChildren());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("view-profile.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        profileController profileController = fxmlLoader.getController();

        System.out.println(1111);

        profileController.setData(getCustomerData());



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

    public void switchToRecommendation(ActionEvent event) throws IOException {
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
                                infxmlLoader.setLocation(getClass().getResource("product-details(ahmad).fxml"));
                                AnchorPane inanchorPane = infxmlLoader.load();
                                String query = "select * from (select @input_int:=? p) parm , techno.view_product_detail s;"; // query to check if id exists
                                tiles.get(finalI);
                                PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                st.setInt(1, tiles.get(finalI).getProductID());
                                ResultSet rs = st.executeQuery();

                                productDetails product = new productDetails();
                                product.setData(rs);

                                String query2 = "select * from (select @input_int:=? p) parm , techno.view_seller_detail s;"; // query to check if id exists

                                PreparedStatement stat = conn.prepareStatement(query2, ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                stat.setInt(1, rs.getInt(2));
                                ResultSet rst = stat.executeQuery();
                                System.out.println(stat);
                                product.setSellerDetails(rst);


                                ProductPageController productPageController = infxmlLoader.getController();
                                productPageController.setData(product);

                                productPageController.setProduct_id(tiles.get(finalI).getProductID());
                                productPageController.setCustomer_id(this.customer.getCust_ID());

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


    public void switchToFavorites(ActionEvent event) throws IOException {
        grid.getChildren().removeAll(grid.getChildren());
        tiles.removeAll(tiles);
        try {
            tiles.addAll(getFavoritesData());
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
                                infxmlLoader.setLocation(getClass().getResource("product-details(ahmad).fxml"));
                                AnchorPane inanchorPane = infxmlLoader.load();
                                String query = "select * from (select @input_int:=? p) parm , techno.view_product_detail s;"; // query to check if id exists
                                tiles.get(finalI);
                                PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                st.setInt(1, tiles.get(finalI).getProductID());
                                ResultSet rs = st.executeQuery();

                                productDetails product = new productDetails();
                                product.setData(rs);

                                String query2 = "select * from (select @input_int:=? p) parm , techno.view_seller_detail s;"; // query to check if id exists

                                PreparedStatement stat = conn.prepareStatement(query2, ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                stat.setInt(1, rs.getInt(2));
                                ResultSet rst = stat.executeQuery();
                                System.out.println(stat);
                                product.setSellerDetails(rst);


                                ProductPageController productPageController = infxmlLoader.getController();
                                productPageController.setData(product);

                                productPageController.setProduct_id(tiles.get(finalI).getProductID());
                                productPageController.setCustomer_id(this.customer.getCust_ID());

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
//            System.out.println(tiles.size()+"count");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                                infxmlLoader.setLocation(getClass().getResource("product-details(ahmad).fxml"));
                                AnchorPane inanchorPane = infxmlLoader.load();
//                                ProductPageController productPageController = infxmlLoader.getController();

                                String query = "select * from (select @input_int:=? p) parm , techno.view_product_detail s;"; // query to check if id exists
                                tiles.get(finalI);
                                PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                st.setInt(1, tiles.get(finalI).getProductID());
                                ResultSet rs = st.executeQuery();

                                productDetails product = new productDetails();
                                product.setData(rs);

                                String query2 = "select * from (select @input_int:=? p) parm , techno.view_seller_detail s;"; // query to check if id exists

                                PreparedStatement stat = conn.prepareStatement(query2, ResultSet.TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
                                stat.setInt(1, rs.getInt(2));
                                ResultSet rst = stat.executeQuery();
                                System.out.println(stat);
                                product.setSellerDetails(rst);


                                ProductPageController productPageController = infxmlLoader.getController();
                                productPageController.setData(product);

                                productPageController.setProduct_id(tiles.get(finalI).getProductID());
                                productPageController.setCustomer_id(this.customer.getCust_ID());

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
