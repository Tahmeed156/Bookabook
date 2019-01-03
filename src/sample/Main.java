package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }


    public static void main(String[] args) {

        try {
            User u1 = new User("Tahmeed");
            User u2 = new User("Najib");

            SessionFactory sf = new Configuration().configure("/sample/hibernate.cfg.xml").buildSessionFactory();
            Session s = sf.openSession();
            s.beginTransaction();
            s.save(u1);
            s.save(u2);
            s.getTransaction().commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }

        // launch(args);
    }
}
