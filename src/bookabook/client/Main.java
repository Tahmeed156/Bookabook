package bookabook.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.prefs.Preferences;

public class Main extends Application {

    public static Preferences userCon = Preferences.userRoot().node("bookabook/user");
    public static Client connection;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Configuring an object to be used, starting sockets
        connection = new Client();
        // Configuring Preference API to write registry
        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));

        try {
            String username = userCon.get("username", "");
            if (username.equals(""))
                throw new Exception("User not found in registry");
            System.out.println("Logged in as " + username);
            root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally {
            primaryStage.setTitle("Book A Book");
            primaryStage.setScene(new Scene(root, 1200, 800));
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
