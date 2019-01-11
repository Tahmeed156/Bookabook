package sample;

// Javafx configuration
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

// Hibernate configuration
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// Project files
import org.hibernate.query.Query;
import sample.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.prefs.Preferences;

public class logIn {

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


    // ========================== PRESS FUNCTIONS (User authentication) ========================

    public void onPress(MouseEvent event) {

        if (event.getSource() == logInBtn) {

            if(userNameLogin.getText().isEmpty() || password.getText().isEmpty())
            {
                toastlogin.setText("PLEASE FILL ALL THE FIELDS");
                toastlogin.setStyle("-fx-background-color:orange; -fx-text-fill:#ffffff");
            }
            else {
                try {
                    SessionFactory sf = new Configuration().configure("/sample/hibernate.cfg.xml").buildSessionFactory();
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

                        String username = userCon.get("username", "user.username");
                        System.out.println("Successfully logged in as " + username);
                        // todo: Add toast ^ and change screens
                    } catch (Exception e) {
                        System.out.println("Wrong username or password");
                        // todo: Add toast ^
                        toastlogin.setText("Wrong Username or Password");
                        toastlogin.setStyle("-fx-background-color:orange; -fx-text-fill:#ffffff");
                    }

                    s.getTransaction().commit();
                    s.close();
                } catch (HibernateException e) {
                    // todo: Add toast "Unable to create new user"
                    toastlogin.setText("Unable to create new user");
                    toastlogin.setStyle("-fx-background-color:orange; -fx-text-fill:#ffffff");
                    System.out.println(e.getMessage());
                }
            }
        }
        if (event.getSource() == signUpBtn) {
            // todo: Handling corner cases - didn't fill in all boxes, gender not right, not valid email/contact_no
            if(fullName.getText().isEmpty()|| userNameSignup.getText().isEmpty()||
                    passwordSignup.getText().isEmpty()|| conPasswordSignup.getText().isEmpty()||
                    emailAddress.getText().isEmpty()){
                toastSignup.setText("PLEASE FILL ALL THE FIELDS");
                toastSignup.setStyle("-fx-background-color:orange; -fx-text-fill:#ffffff");

                LocalDate date = dateOfBirth.getValue();
                System.out.println(date.toString());
            }

            else if(passwordSignup.getText().equals(conPasswordSignup.getText())) {

                try {

                    User u = new User();
                    LocalDate date = dateOfBirth.getValue();
                    System.out.println(date.toString());
                    u.signup(
                            fullName.getText(),
                            userNameSignup.getText(),
                            password.getText(),
                            // dateOfBirth.<getData>, needs to return a Date object
                            // returns LocalDate : use LocalDate if possible
                            //dateOfBirth.getValue(),
                            emailAddress.getText()
                    );
                    SessionFactory sf = new Configuration().configure("/sample/hibernate.cfg.xml").buildSessionFactory();
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
                    toastSignup.setText("SUCCESSFULLY SIGNED UP");
                    toastSignup.setStyle("-fx-background-color:green; -fx-text-fill:#ffffff");
                } catch (HibernateException e) {
                    // todo: Add toast "Unable to create new user"
                    toastSignup.setText("UNABLE TO CREATE NEW USER");
                    toastSignup.setStyle("-fx-background-color:red; -fx-text-fill:#ffffff");
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

    public void conPassPressed(KeyEvent e)
    {
        if(!passwordSignup.getText().equals(conPasswordSignup.getText()))
        {
            warningPassword.setText("PASSWORDS DON'T MATCH");
            warningPassword.setStyle("-fx-background-color:#ff0000; -fx-text-fill:#ffffff");
        }
        if(passwordSignup.getText().equals(conPasswordSignup.getText()))
        {
            warningPassword.setText("PASSWORDS MATCH");
            warningPassword.setStyle("-fx-background-color:#00ff00; -fx-text-fill:#ffffff");
        }
    }



}

