package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class View implements Initializable{
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vBox;

    @FXML
    void close(MouseEvent event) {

    }

    @FXML
    void decryptClicked(MouseEvent event) throws IOException {
        Node source= (Node) event.getSource();
        Stage stage= (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Decryption.fxml"));
        stage.setTitle("Decryption");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void encryptClicked(MouseEvent event) throws IOException {
        Node source= (Node) event.getSource();
        Stage stage= (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Encryption.fxml"));
        stage.setTitle("Encryption");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void settingClicked(MouseEvent event) throws IOException {
        Node source= (Node) event.getSource();
        Stage stage= (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Setting.fxml"));
        stage.setTitle("Setting");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollpane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    JFXButton button = new JFXButton("hey");
    button.setStyle("-fx-pref-width: 752;-fx-pref-height: 50");
    HBox hBox = new HBox();
    hBox.getChildren().add(button);
    hBox.setAlignment(Pos.CENTER);
    hBox.setStyle("-fx-background-color: rgba(225,225,225,0.7)");
    vBox.getChildren().add(hBox);
    vBox.setSpacing(20);
    }
}
