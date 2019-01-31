package bookabook.client.controllers;


// Javafx configuration
import bookabook.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                toast.set("PLEASE FILL ALL THE FIELDS","#f0ad4e");
            }
            else {

                String u = userNameLogin.getText();
                String p = password.getText();
                boolean success = Main.connection.login(u, p);

                if (success) {
                    Preferences userCon = Main.userCon;

                    // Saving information in the registry
                    userCon.put("username", u);
                    // bug TMD # Returning user_id and full_name from the server
                    // userCon.put("id", String.valueOf(u.getId()));
                    // userCon.put("full_name", );

                    toast.set("LOGIN SUCCESSFUL","#5cb85c");
                    Windows w = new Windows(logInBtn, "../fxml/dashboard.fxml");
                }
                else {
                    toast.set("WRONG USERNAME OR PASSWORD","#D9534F");
                }

            }
        }
        if (event.getSource() == signUpBtn) {

            if(fullName.getText().isEmpty()|| userNameSignup.getText().isEmpty()||
                passwordSignup.getText().isEmpty()|| conPasswordSignup.getText().isEmpty()||
                emailAddress.getText().isEmpty()) {
                toast.set("PLEASE FILL ALL THE FIELDS","#f0ad4e");
            }
            else if (!passwordSignup.getText().equals(conPasswordSignup.getText())) {
                toast.set("RE-ENTER PASSWORDS","#f0ad4e");
            }
            else if(passwordSignup.getText().equals(conPasswordSignup.getText())) {

                LocalDate localDate = dateOfBirth.getValue();
                String dob = localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                String u = userNameSignup.getText();
                boolean success = Main.connection.signup(
                        fullName.getText(),
                        userNameSignup.getText(),
                        passwordSignup.getText(),
                        dob,
                        emailAddress.getText()
                );

                if (success) {
                    Preferences userCon = Main.userCon;

                    // Saving information in the registry
                    userCon.put("username", u);
                    // bug: add user_id, full_name to registry
                    // userCon.put("id", String.valueOf(u.getId()));
                    // userCon.put("full_name", u.getFull_name());

                    toast.set("SUCCESSFULLY SIGNED UP","#5CB85C");
                    Windows w = new Windows(logInBtn, "../fxml/dashboard.fxml");
                }
                else {
                    toast.set("UNABLE TO CREATE NEW USER","#D9534F");
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

