package com.example.tecno;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.tecno.HelloApplication.conn;

public class signUpController {

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField emailTextBox;

    @FXML
    private TextField firstNameTextBox;

    @FXML
    private TextField lastNameTextBox;

    @FXML
    private PasswordField passwordTextBox;

    @FXML
    private TextField phoneTextBox;

    @FXML
    private TextField usernameTextBox;



    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToSignIN(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("loginPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void NewSignUp(ActionEvent event) throws SQLException, IOException {
        String query = "insert into customer(username, email, password, first_name, last_name, phone_no) values(?, ?, ?, ?, ?, ?)"; // query to check if id exists

        PreparedStatement st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE); //creating and preparing statements
        st.setString(1, usernameTextBox.getText());
        st.setString(2, emailTextBox.getText());
        st.setString(3, passwordTextBox.getText());
        st.setString(4, firstNameTextBox.getText());
        st.setString(5, lastNameTextBox.getText());
        st.setString(6, phoneTextBox.getText());

        st.executeUpdate();

        switchToSignIN(event);
    }
}
