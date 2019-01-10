package sample;

// Javafx configuration
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    @FXML private TextField password;

    @FXML private TextField fullName;
    @FXML private TextField userNameSignup;
    @FXML private DatePicker dateOfBirth;
    @FXML private TextField emailAddress;
    @FXML private TextField contactNo;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private ToggleGroup gender;
    @FXML private RadioButton toSignUp;

    @FXML private Button logInBtn;
    @FXML private Button signUpBtn;


    // ========================== PRESS FUNCTIONS (User authentication) ========================

    public void onPress(MouseEvent event) {

        if (event.getSource() == logInBtn) {

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
                }
                catch (Exception e) {
                    System.out.println("Wrong username or password");
                    // todo: Add toast ^
                }

                s.getTransaction().commit();
                s.close();
            }
            catch (HibernateException e) {
                // todo: Add toast "Unable to create new user"
                System.out.println(e.getMessage());
            }
        }
        if (event.getSource() == signUpBtn) {

//            if (toSignup.<getValue> == false) {
                // todo: Add toast: 'Please read and accept our terms and conditions to Signup'
//            }

            // todo: Handling corner cases - didn't fill in all boxes, gender not right, not valid email/contact_no
            try {

                User u = new User();
                u.signup(
                        fullName.getText(),
                        userNameSignup.getText(),
                        // todo: make gender a selection box rather than a text field
                        // todo: create password & reconfirm password field in page
                        // password.<getData>,
                        // dateOfBirth.<getData>, needs to return a Date object
                        // gender.<getData>, needs to return a string
                        emailAddress.getText(),
                        contactNo.getText()
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
            }
            catch (HibernateException e) {
                // todo: Add toast "Unable to create new user"
                System.out.println(e.getMessage());
            }
        }
    }


    // ========================== HOVER DESIGN FUNCTIONS ========================

    public void onHover(MouseEvent event) {

        if(logInBtn.isHover()) {

            logInBtn.setStyle("-fx-background-color:#383838;");
            logInBtn.setTextFill(Color.rgb(217,217,217));
        }
        if(signUpBtn.isHover()) {

            signUpBtn.setStyle("-fx-background-color:#383838;");
            signUpBtn.setTextFill(Color.rgb(217,217,217));
        }
    }

    public void endHover(MouseEvent event) {

        if(!logInBtn.isHover())
        {
            logInBtn.setStyle("-fx-background-color:#d9d9d9;");
            logInBtn.setTextFill(Color.rgb(59,56,56));
        }
        if(!signUpBtn.isHover())
        {
            signUpBtn.setStyle("-fx-background-color:#d9d9d9;");
            signUpBtn.setTextFill(Color.rgb(59,56,56));
        }
    }




}

