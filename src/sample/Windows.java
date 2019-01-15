package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.prefs.Preferences;


public class Windows {


    Parent root;
    String[] name = new String[]{"dashboard.fxml", "searchPage.fxml", "", "","","logIn.fxml","rentOutPage.fxml"};

    Windows(StackPane s, int i) {
        try {
            if (name[i].equals("logIn.fxml")) {
                Preferences userCon = Preferences.userRoot().node("bookabook/user");

                // Deleting information from the registry
                userCon.put("username", "");
                userCon.put("id", "");
                userCon.put("full_name", "");
            }
            root = FXMLLoader.load(getClass().getResource(name[i]));
            Scene scene = new Scene(root);
            Stage window = (Stage)(s.getScene().getWindow());
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            System.out.println("Cannot be opened");
        }
    }

    Windows (Button b, int i) {
        try {
            root = FXMLLoader.load(getClass().getResource(name[i]));
            Scene scene = new Scene(root);
            Stage window = (Stage)(b.getScene().getWindow());
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            System.out.println("Cannot be opened");
        }
    }

    Windows (Button b, String n) {
        try {
            root = FXMLLoader.load(getClass().getResource(n));
            Scene scene = new Scene(root);
            Stage window = (Stage)(b.getScene().getWindow());
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            System.out.println("Cannot be opened");
        }
    }


}