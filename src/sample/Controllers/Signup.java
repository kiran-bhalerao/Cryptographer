package sample.Controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main.Context;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Signup {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField ConfirmPassword;

    @FXML
    private JFXTextField question;


    @FXML
    void backClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Splash.fxml"));
        stage.setTitle("Splash");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void enterKeyUsername(KeyEvent event) {
        if (event.getCode() == KeyCode.getKeyCode("Enter"))
            password.requestFocus();
    }

    @FXML
    void enterKeyConfirm(KeyEvent event) {
        if (event.getCode() == KeyCode.getKeyCode("Enter"))
            question.requestFocus();
    }


    @FXML
    void enterKeyPassword(KeyEvent event) {
        if (event.getCode() == KeyCode.getKeyCode("Enter"))
            ConfirmPassword.requestFocus();
    }


    @FXML
    void letsgoClicked(MouseEvent e) {

        boolean hasAlreadUsername = false;
        try {
            PreparedStatement preparedStatement2 = Context.getInstance().getConnection().prepareStatement("SELECT username FROM login");
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()) {
                hasAlreadUsername = (resultSet2.getString("username").equals(username.getText()));
                if (hasAlreadUsername)
                    break;

            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        if (!hasAlreadUsername && !username.getText().equals("") && !ConfirmPassword.getText().equals("") && password.getText().equals(ConfirmPassword.getText()) && !question.getText().equals("")) {
            try {
                PreparedStatement preparedStatement = Context.getInstance().getConnection().prepareStatement("INSERT INTO login VALUES(?,?,?)");
                preparedStatement.setString(1, username.getText());
                preparedStatement.setString(2, password.getText());
                preparedStatement.setString(3, question.getText());


                preparedStatement.executeUpdate();
                preparedStatement.close();

                loginClicked(e);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } else {
            if (hasAlreadUsername) {

            } else {

            }
        }


    }

    @FXML
    void loginClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Login.fxml"));
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void socialClicked(MouseEvent e) throws IOException {
        new ProcessBuilder("x-www-browser", "https://www.facebook.com/kiran.nivruttibhalerao").start();
    }
}
