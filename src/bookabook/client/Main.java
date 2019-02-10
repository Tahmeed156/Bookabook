package bookabook.client;

import bookabook.objects.Bookser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class Main extends Application {

    // Configuring Preference API to write registry
    public static Preferences userCon = Preferences.userRoot().node("bookabook/user");
    public static Client connection;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // try to  connect to server
        new Thread(){
            @Override
            public void run() {
                try {
                    // Configuring an object to be used, starting sockets
                    connection = new Client();
                }catch (Exception e)
                {
                    System.out.println("Can't connect with server");
                }
            }
        }.start();


        Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));

        // If username found in registry go to dashboard on startup
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
            primaryStage.resizableProperty().setValue(Boolean.FALSE);
            primaryStage.setTitle("Book A Book");
            primaryStage.setScene(new Scene(root, 1200, 800));
            primaryStage.show();
        }
    }

    public static void main(String[] args){ launch(args); }
}
