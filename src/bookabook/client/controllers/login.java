package bookabook.client.controllers;


// Javafx configuration
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

// Hibernate configuration
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Project files
import org.hibernate.query.Query;
import server.models.User;

import java.time.LocalDate;
import java.util.prefs.Preferences;

public class login {

    @FXML private Pane parent;
    @FXML private TextField userNameLogin;
    @FXML private PasswordField password;
    @FXML private Label toastlogin;

    @FXML private TextField fullName;
    @FXML private TextField userNameSignup;
    @FXML private PasswordField passwordSignup;
    @FXML private PasswordField conPasswordSignup;
    @FXML private Label warningPassword;

    @FXML private DatePicker dateOfBirth;
    @FXML private TextField emailAddress;
    @FXML private Label toastSignup;


    @FXML private Button logInBtn;
    @FXML private Button signUpBtn;

    public void initialize()
    {
        parent.getChildren().add(toast.get());
    }


    // ========================== PRESS FUNCTIONS (User authentication) ========================

    public void onPress(MouseEvent event) {

        if (event.getSource() == logInBtn) {

            if(userNameLogin.getText().isEmpty() || password.getText().isEmpty())
            {
                toast.set("PLEASE FILL ALL THE FIELDS");
                toast.setColor("#f0ad4e");
//                toastlogin.setText("PLEASE FILL ALL THE FIELDS");
//                toastlogin.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill:#ffffff");
            }
            else {
                try {
                    SessionFactory sf = new Configuration().configure("/bookabook/client/hibernate.cfg.xml").buildSessionFactory();
                    Session s = sf.openSession();
                    s.beginTransaction();

                    Query query = s.createQuery("from User where username = :u and password = :p");
                    query.setParameter("u", userNameLogin.getText());
                    query.setParameter("p", password.getText());
                    try {
                        User u = (User) query.uniqueResult();
                        System.out.println(u.showName());
                        // todo: Add toast "Login successful"
                        Preferences userCon = Preferences.userRoot().node("bookabook/user");

                        // Saving information in the registry
                        userCon.put("username", u.getUsername());
                        userCon.put("id", String.valueOf(u.getId()));
                        userCon.put("full_name", u.getFull_name());

                        String username = userCon.get("username", "");
                        System.out.println("Successfully logged in as " + username);
                        // todo: Add toast ^ and change screens
                        toast.set("LOGIN SUCCESSFUL");
                        toast.setColor("#5cb85c");
                        Windows w = new Windows(logInBtn, "../fxml/dashboard.fxml");
                    } catch (Exception e) {
                        toast.set("WRONG USERNAME OR PASSWORD");
                        toast.setColor("#D9534F");
//                        toastlogin.setText("Wrong Username or Password");
//                        toastlogin.setStyle("-fx-background-color: #d9534f; -fx-text-fill:#ffffff");
                        // todo: Add toast ^
                    }

                    s.getTransaction().commit();
                    s.close();
                } catch (HibernateException e) {
                    // todo: Add toast "Unable to create new user"
                    toast.set("UNABLE TO CREATE NEW USER");
                    toast.setColor("#f0ad4e");
//                    toastlogin.setText("Unable to create new user");
//                    toastlogin.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill:#ffffff");
                    System.out.println(e.getMessage());
                }
            }
        }
        if (event.getSource() == signUpBtn) {

            if(fullName.getText().isEmpty()|| userNameSignup.getText().isEmpty()||
                passwordSignup.getText().isEmpty()|| conPasswordSignup.getText().isEmpty()||
                emailAddress.getText().isEmpty()) {
                toast.set("PLEASE FILL ALL THE FIELDS");
                toast.setColor("#f0ad4e");
//                toastSignup.setText("PLEASE FILL ALL THE FIELDS");
//                toastSignup.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill:#ffffff");
            }
            else if (!passwordSignup.getText().equals(conPasswordSignup.getText())) {
                toast.set("RE-ENTER PASSWORDS");
                toast.setColor("#f0ad4e");
//                toastlogin.setText("RE-ENTER PASSWORDS");
                passwordSignup.getText().equals(conPasswordSignup.getText()); //WHAT DOES THIS DO TAHMEED
//                toastlogin.setStyle("-fx-background-color: #f0ad4e; -fx-text-fill:#ffffff");
            }
            else if(passwordSignup.getText().equals(conPasswordSignup.getText())) {

                try {
                    User u = new User();

                    LocalDate localDate = dateOfBirth.getValue();
                    // Converts LocalDate object into Date object
                    java.util.Date date = java.sql.Date.valueOf(localDate);

                    u.signup(
                            fullName.getText(),
                            userNameSignup.getText(),
                            passwordSignup.getText(),
                            date, // passes a date object
                            emailAddress.getText()
                    );
                    SessionFactory sf = new Configuration().configure("/bookabook/client/hibernate.cfg.xml").buildSessionFactory();
                    Session s = sf.openSession();
                    s.beginTransaction();
                    s.save(u);
                    s.getTransaction().commit();
                    s.close();

                    Preferences userCon = Preferences.userRoot().node("bookabook/user");
                    // Saving information in the registry
                    userCon.put("username", u.getUsername());
                    userCon.put("id", String.valueOf(u.getId()));
                    userCon.put("full_name", u.getFull_name());

                    String username = userCon.get("username", "user.username");
                    System.out.println("Successfully signed up as " + username);
                    // todo: Add toast ^ and change screens
                    toast.set("SUCCESSFULLY SIGNED UP");
                    toast.setColor("#5CB85C");
//                    toastSignup.setText("SUCCESSFULLY SIGNED UP");
//                    toastSignup.setStyle("-fx-background-color: #5cb85c; -fx-text-fill:#ffffff");
                    Windows w = new Windows(logInBtn, "../fxml/dashboard.fxml");
                } catch (HibernateException e) {
                    // todo: Add toast "Unable to create new user"
                    toast.set("UNABLE TO CREATE NEW USER");
                    toast.setColor("#D9534F");
//                    toastSignup.setText("UNABLE TO CREATE NEW USER");
//                    toastSignup.setStyle("-fx-background-color: #d9534f; -fx-text-fill:#ffffff");
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    // ========================== HOVER DESIGN FUNCTIONS ========================

    public void onHover(MouseEvent event) {

        if(logInBtn.isHover()) {

            logInBtn.setStyle("-fx-background-color:#3b3838; -fx-border-width:3; -fx-border-color:#d9d9d9");
            logInBtn.setTextFill(Color.rgb(217,217,217));
        }
        if(signUpBtn.isHover()) {

            signUpBtn.setStyle("-fx-background-color:#3b3838; -fx-border-width:3; -fx-border-color:#d9d9d9");
            signUpBtn.setTextFill(Color.rgb(217,217,217));
        }
    }

    public void endHover(MouseEvent event) {

        if(!logInBtn.isHover())
        {
            logInBtn.setStyle("-fx-background-color:#d9d9d9; -fx-border-width:3; -fx-border-color:#d9d9d9");
            logInBtn.setTextFill(Color.rgb(59,56,56));
        }
        if(!signUpBtn.isHover())
        {
            signUpBtn.setStyle("-fx-background-color:#d9d9d9; -fx-border-width:3; -fx-border-color:#d9d9d9");
            signUpBtn.setTextFill(Color.rgb(59,56,56));
        }
    }


    // ========================== CONFIRM PASSWORD FUNCTIONS ========================

    public void PassPressed(KeyEvent e)
    {
        if(!passwordSignup.getText().equals(conPasswordSignup.getText())) {
            warningPassword.setText("PASSWORDS DON'T MATCH");
            warningPassword.setStyle("-fx-background-color: #d9534f; -fx-text-fill:#ffffff; -fx-border-radius: 5");
        }
        if(passwordSignup.getText().equals(conPasswordSignup.getText())) {
            warningPassword.setText("PASSWORDS MATCH");
            warningPassword.setStyle("-fx-background-color: #5cb85c; -fx-text-fill:#ffffff; -fx-border-radius: 5");
        }
    }



}

