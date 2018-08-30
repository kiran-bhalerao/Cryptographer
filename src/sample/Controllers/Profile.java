package sample.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main.Context;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Profile implements Initializable{
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private TextField cpassword;

    @FXML
    private TextField nickName;

    @FXML
    void close(MouseEvent e) {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void enterKeyCPassword(KeyEvent event) {
        if (event.getCode() == KeyCode.getKeyCode("Enter"))
            nickName.requestFocus();
    }

    @FXML
    void enterKeyNickName(KeyEvent event) {
        if (event.getCode() == KeyCode.getKeyCode("Enter")) {
            saveData();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void enterKeyPassword(KeyEvent event) {
        if (event.getCode() == KeyCode.getKeyCode("Enter"))
            cpassword.requestFocus();
    }

    @FXML
    void enterKeyusername(KeyEvent event) {
        if (event.getCode() == KeyCode.getKeyCode("Enter"))
            password.requestFocus();
    }

    public void saveData() {
        if (!username.getText().equals("") && !password.getText().equals("") && !cpassword.getText().equals("") && !nickName.getText().equals("") && password.getText().equals(cpassword.getText())) {
            try {
                int count = 0;
                System.out.println(Context.getInstance().getUsername());
                PreparedStatement preparedStatement = Context.getInstance().getConnection().prepareStatement("UPDATE login SET password=? where username=?");
                preparedStatement.setString(1, password.getText());
                preparedStatement.setString(2, Context.getInstance().getUsername());
                count += preparedStatement.executeUpdate();
                preparedStatement.close();

                PreparedStatement preparedStatement1 = Context.getInstance().getConnection().prepareStatement("UPDATE login SET username=? where username=?");
                preparedStatement1.setString(1, username.getText());
                preparedStatement1.setString(2, Context.getInstance().getUsername());
                count += preparedStatement1.executeUpdate();
                preparedStatement1.close();

                if (count > 0)
                    Context.getInstance().setUsername(username.getText());

                //System.out.println(Context.getInstance().getUsername());

            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("else");
        }
    }

    @FXML
    void saveClicked(ActionEvent event) {
        saveData();
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(Context.getInstance().getUsername());
        Platform.runLater(() -> password.requestFocus());
    }
}
