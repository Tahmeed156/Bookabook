package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;


public class logIn {
    @FXML private TextField userName;
    @FXML private TextField password;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField dateOfBirth;
    @FXML private TextField emailAddress;
    @FXML private TextField contactNo;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private ToggleGroup gender;
    @FXML private RadioButton toSignUp;
    @FXML private Button logInBtn;
    @FXML private Button signUpBtn;

    public void onHover(MouseEvent event)
    {
        if(logInBtn.isHover())
        {
            logInBtn.setStyle("-fx-background-color:#383838;");
            logInBtn.setTextFill(Color.rgb(217,217,217));
        }
        if(signUpBtn.isHover())
        {
            signUpBtn.setStyle("-fx-background-color:#383838;");
            signUpBtn.setTextFill(Color.rgb(217,217,217));
        }
    }

    public void endHover(MouseEvent event)
    {
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

