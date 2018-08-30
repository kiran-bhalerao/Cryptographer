package sample.Main;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.sql.Connection;
import java.sql.DriverManager;

public class Context {

    private final static Context instance = new Context();

    String username;
    String password;


    String encMessage;
    String decMessage;
    String message;

    //private String path=System.getenv("APPDATA");
    private String path = System.getProperty("user.home");
    public Connection connection;


    public static Context getInstance() {
        return instance;
    }

    public Context() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:"+path+"/CryptographerDB.sqlite");
        } catch (Exception e) {
            System.out.println(e.getMessage() + " connection class problem !");
            connection = null;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEncMessage() {
        return encMessage;
    }

    public void setEncMessage(String encMessage) {
        this.encMessage = encMessage;
    }

    public String getDecMessage() {
        return decMessage;
    }

    public void setDecMessage(String decMessage) {
        this.decMessage = decMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }






//    public static Popup createPopup(final String message) {
//        final Popup popup = new Popup();
//        popup.setAutoFix(true);
//        popup.setAutoHide(true);
//        popup.setHideOnEscape(true);
//        Label label = new Label(message);
//        label.setOnMouseReleased(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent e) {
//                popup.hide();
//            }
//        });
//        label.getStylesheets().add("/sample/Styles/style.css");
//        label.getStyleClass().add("popup");
//        popup.getContent().add(label);
//        return popup;
//    }
//
//    public static void showPopupMessage(final String message, final Stage stage) {
//        final Popup popup = createPopup(message);
//        popup.setOnShown(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent e) {
//                popup.setX(stage.getX() + 1.2*stage.getWidth()/2 - popup.getWidth()/2);
//                popup.setY(stage.getY() + 1.2*stage.getHeight()/2 - popup.getHeight()/2);
//            }
//        });
//        popup.show(stage);
//    }
}
