package sample.Controllers;

import com.jfoenix.controls.JFXCheckBox;
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

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Decrypted implements Initializable {
    @FXML
    private JFXTextArea message;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXCheckBox saveCheckBox;

    @FXML
    void close(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void copyClicked(MouseEvent event) throws SQLException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        if (saveCheckBox.isSelected()) {
            // add to database..
            int lng = Context.getInstance().getDecMessage().length();
            if (lng > 40) {
                lng = 40;
            }
            PreparedStatement preparedStatement = Context.getInstance().getConnection().prepareStatement("INSERT into messages(title,message,user)values(?,?,?)");
            preparedStatement.setString(1, Context.getInstance().getDecMessage().substring(0, lng));
            preparedStatement.setString(2, Context.getInstance().getDecMessage());
            preparedStatement.setString(3, Context.getInstance().getUsername());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(message.getText());
        clipboard.setContents(strSel, null);

    }

    @FXML
    void encryptClicked(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXMLs/Encryption.fxml"));
        stage.setTitle("Encryption");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int lng = Context.getInstance().getDecMessage().length();
        if (lng > 40)
            lng = 40;

        title.setText(Context.getInstance().getDecMessage().substring(0, lng));
        message.setText(Context.getInstance().getDecMessage());
    }
}
