package bookabook.client;

import bookabook.objects.Bookser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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

    public static void main(String[] args) throws IOException, JSONException, ClassNotFoundException {

//        Socket s = new Socket("127.0.0.1", 9899);
//        ObjectInputStream input = new ObjectInputStream(s.getInputStream());
//        DataOutputStream output = new DataOutputStream(s.getOutputStream());


//        Bookser book = (Bookser) input.readObject();
//        BufferedImage image = ImageIO.read(input);
//        ImageIO.write(image, "png", new File("D:\\Bookabook\\src\\bookabook\\client\\Pictures\\saved\\"
//                + book.getName() + ".png"));
//        System.out.println("Image received!");
//        input.skipBytes(100);

//        try {
//            String line = (String) input.readObject();
//
//            // test: displaying shit
//            System.out.println("Book name: " + book.getName());
//            System.out.println("Line: " + line);
//        }
//        catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        //    BufferedImage image = ImageIO.read(inputStream);
//    ImageIO.write(image, "png", new File("E:\\Coding\\Code\\Gava\\src\\server\\upload\\" + String.valueOf(std.getId()) + ".png"));

        launch(args);
    }
}
