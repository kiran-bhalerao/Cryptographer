package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Setting {
    @FXML
    void aboutClicked(MouseEvent event) throws IOException {
        Stage stage=new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/About.fxml"));
        stage.setTitle("About");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void close(MouseEvent event) {
        Node source= (Node) event.getSource();
        Stage stage= (Stage) source.getScene().getWindow();
        stage.close();
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
    void profileClicked(MouseEvent event) throws IOException {
        Stage stage=new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Profile.fxml"));
        stage.setTitle("Profile");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void viewClicked(MouseEvent event) throws IOException {
//        Node source= (Node) event.getSource();
//        Stage stage= (Stage) source.getScene().getWindow();
//
//        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/View.fxml"));
//        stage.setTitle("View");
        //stage.setResizable(false);
//        stage.setScene(new Scene(root));
//        stage.show();
    }
}
