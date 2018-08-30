package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
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
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private JFXButton letsgoClicked;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

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
    void forgetClicked(MouseEvent event) {

    }

    @FXML
    void letsgoClicked(MouseEvent event) throws IOException {

        //check conditions
        if (!username.getText().equals("") && !password.getText().equals("") && getLogin(username.getText(), password.getText())) {

            Context.getInstance().setUsername(username.getText());

            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Encryption.fxml"));
            stage.setTitle("Encryption");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    private boolean getLogin(String uname, String pass) {
        boolean isLogin = false;
        try {
            PreparedStatement preparedStatement = Context.getInstance().getConnection().prepareStatement("SELECT * FROM login");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("username").equals(uname) && resultSet.getString("password").equals(pass)) {
                    isLogin = true;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isLogin;
    }

    @FXML
    void socialClicked(MouseEvent e) throws IOException {
        new ProcessBuilder("x-www-browser", "https://www.facebook.com/kiran.nivruttibhalerao").start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> username.requestFocus());
    }
}
