package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.prefs.Preferences;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Preferences userCon = Preferences.userRoot().node("bookabook/user");
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));


        try {
            String username = userCon.get("username", "");
            if (username.equals("") || username.equals(""))
                throw new Exception("User not found in registry");
            System.out.println("Logged in as " + username);
            root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
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
