package sample.Controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import java.util.ResourceBundle;

public class Decryption implements Initializable {
    @FXML
    private JFXTextArea message;

    @FXML
    private JFXTextField decrKey;

    @FXML
    void decryptButtonClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        if (!message.getText().equals("") && !decrKey.getText().equals("")) {
            // System.out.println("decryptButtonClicked "+message.getText());
            Context.getInstance().setDecMessage(message.getText());
            decrypt(message.getText(), Integer.parseInt(decrKey.getText()));

            Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Decrypted.fxml"));
            stage.setTitle("Decrypted");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    void decrypt(String msg, int key) {
        int index;
        char[] messageArray = msg.toCharArray();
        String decriptedMessage = "";
        String[] array = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        if (key > 0) {
            if (key > 25) {
                while (key > 25) {
                    key = key % 26;
                }
            }
            if (key == 0) {
                key = 8;
            }
            for (char aMessageArray : messageArray) {
                int x = 0;
                for (int j = 0; j < 26; j++) {
                    if ((Character.toString(aMessageArray).toLowerCase()).compareTo(array[j]) != 0) {
                        x++;
                        if (x == 26) {
                            decriptedMessage = decriptedMessage + Character.toString(aMessageArray);
                        }
                    }
                }
                for (int j = 0; j < 26; j++) {
                    if ((Character.toString(aMessageArray).toLowerCase()).compareTo(array[j]) == 0) {
                        index = j;
                        if (index - key < 0) {
                            index = 26 + (index - key);
                            decriptedMessage = decriptedMessage + (array[index]);
                        } else {
                            decriptedMessage = decriptedMessage + (array[index - key]);
                        }
                    }

                }
            }
        }
        Context.getInstance().setDecMessage(decriptedMessage);
    }

    @FXML
    void encriptClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Encryption.fxml"));
        stage.setTitle("Encryption");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void settingClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Setting.fxml"));
        stage.setTitle("Setting");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void viewClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/View.fxml"));
        stage.setTitle("View");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        decrKey.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,9}?"))
                decrKey.setText(oldValue);
        });
    }
}
