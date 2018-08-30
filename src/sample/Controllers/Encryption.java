package sample.Controllers;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main.Context;

import java.io.IOException;

public class Encryption {
    @FXML
    private JFXTextArea message;

    @FXML
    private JFXTextField encKey;

    @FXML
    void close(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void decryptClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Decryption.fxml"));
        stage.setTitle("Decryption");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void encryptButtonClicked(MouseEvent event) throws IOException {
        if (!message.getText().equals("") && !encKey.getText().equals("")) {
            Context.getInstance().setMessage(message.getText());
            encrypt(message.getText(), Integer.parseInt(encKey.getText()));
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Encrypted.fxml"));
            stage.setTitle("Encrypted");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }

    }

    @FXML
    void settingClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Setting.fxml"));
        stage.setTitle("Setting");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void viewClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/View.fxml"));
        stage.setTitle("View");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    void encrypt(String msg, int key) {
        int index;
        char[] messageArray = msg.toCharArray();
        String EncriptedMessage = "";
        String[] array = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        if (key > 0) {
        if (key > 25) {
            while (key > 25) {
                key = key % 26;
            }
        }
        if(key==0) {
            key=8;
        }
            for (int i = 0; i < messageArray.length; i++) {
                int x = 0;
                for (int j = 0; j < 26; j++) {
                    if ((Character.toString(messageArray[i]).toLowerCase()).compareTo(array[j]) != 0) {
                        x++;
                        if (x == 26) {
                            EncriptedMessage = EncriptedMessage + Character.toString(messageArray[i]);
                        }
                    }
                }

                for (int j = 0; j < 26; j++) {
                    if ((Character.toString(messageArray[i]).toLowerCase()).compareTo(array[j]) == 0) {
                        index = j;
                        if (index + key > 25) {
                            index = (index + key) - 26;
                            EncriptedMessage = EncriptedMessage + (array[index]);
                        } else {
                            EncriptedMessage = EncriptedMessage + (array[index + key]);
                        }
                    }

                }
            }
        }
        Context.getInstance().setEncMessage(EncriptedMessage);
    }
}
