package bookabook.client.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;


public class Windows {


    Parent root;
    String[] name = new String[]{"dashboard.fxml", "searchPage.fxml", "messenger.fxml",
            "helpPage.fxml","profilePage.fxml","login.fxml","rentOutPage.fxml",
            "editProfilePage.fxml","profileRentedBooksPage.fxml","profileRentedOutBooksPage.fxml","bookDetailsPage.fxml"};

    Windows(StackPane s, int i) {
        try {
            if (name[i].equals("login.fxml")) {
                Preferences userCon = Preferences.userRoot().node("bookabook/user");

                // Deleting information from the registry
                userCon.put("username", "");
                userCon.put("id", "");
                userCon.put("full_name", "");
            }
            cornerCase(name[i]);
            root = FXMLLoader.load(getClass().getResource("../fxml/"+name[i]));
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
            cornerCase(name[i]);
            root = FXMLLoader.load(getClass().getResource("../fxml/"+name[i]));
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
            cornerCase(n);
            root = FXMLLoader.load(getClass().getResource(n));
            Scene scene = new Scene(root);
            Stage window = (Stage)(b.getScene().getWindow());
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println("Cannot be opened");
        }
    }

    Windows (ImageView b, String n, String book) {
        try {
            cornerCase(n);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(n));
            root = loader.load();
            bookDetailsPage bdp = loader.getController();
            bdp.initialize(book);
            Scene scene = new Scene(root);
            Stage window = (Stage)(b.getScene().getWindow());
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            System.out.println("Cannot be opened");
        }
    }

    public void cornerCase(String a)
    {
        if(a.equals("login.fxml"))
        {
            toast.set("SUCCESSFULLY LOGGED OUT","#5cb85c");
        }
    }




}