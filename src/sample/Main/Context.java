package sample.Main;
import java.sql.Connection;
import java.sql.DriverManager;

public class Context {
    private final static Context instance = new Context();
    private String username;
    private String password;
    private String encMessage;
    private String decMessage;
    private String message;
    private Connection connection;
    public static Context getInstance() {
        return instance;
    }

    private Context() {
        try {
            Class.forName("org.sqlite.JDBC");
            String path = (System.getenv("APPDATA") != null) ? System.getenv("APPDATA") : System.getProperty("user.home");
            connection = DriverManager.getConnection("jdbc:sqlite:" + path + "/CryptographerDB.sqlite");
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
}
