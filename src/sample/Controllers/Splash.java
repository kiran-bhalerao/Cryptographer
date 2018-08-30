package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main.Context;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class Splash implements Initializable{
    @FXML
    void loginClicked(MouseEvent event) throws IOException {
        Node source= (Node) event.getSource();
        Stage stage= (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Login.fxml"));
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void signupClicked(MouseEvent event) throws IOException {
        Node source= (Node) event.getSource();
        Stage stage= (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Signup.fxml"));
        stage.setTitle("Signup");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void socialClicked(MouseEvent e) throws IOException {
        new ProcessBuilder("x-www-browser", "https://www.facebook.com/kiran.nivruttibhalerao").start();
    }

    @FXML
    void close(MouseEvent event) {
        Node source= (Node) event.getSource();
        Stage stage= (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Context.getInstance().setUsername("");
            Context.getInstance().setPassword("");

            PreparedStatement preparedStatement = Context.getInstance().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS login(username text PRIMARY KEY ,password text NOT NULL ,nickname text)");
            preparedStatement.executeUpdate();
            preparedStatement.close();

            PreparedStatement preparedStatement3 = Context.getInstance().getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS messages(mid INTEGER PRIMARY KEY AUTOINCREMENT ,title text,message text,user text)");
            preparedStatement3.executeUpdate();
            preparedStatement3.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
