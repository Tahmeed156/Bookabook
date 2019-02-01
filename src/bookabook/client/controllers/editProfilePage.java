package bookabook.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONException;
import org.json.JSONObject;
import bookabook.server.models.Book;
import bookabook.server.models.User;

import java.io.File;
import java.time.LocalDate;


public class editProfilePage {
    @FXML private Pane parent;
    @FXML
    private VBox left;
    //left side labels;
    @FXML
    private Label dashBLbl;
    @FXML
    private Label searchLbl;
    @FXML
    private Label messagesLbl;
    @FXML
    private Label helpLbl;
    @FXML
    private Label profileLbl;
    @FXML
    private Label logoutLbl;
    @FXML
    private Label[] lbl;// = {dashBLbl, messagesLbl, helpLbl, profileLbl, logoutLbl}; //cant do this
    //because of how fxml loader acts

    //left side stacks
    @FXML
    private StackPane dashBStk;
    @FXML
    private StackPane searchStk;
    @FXML
    private StackPane messagesStk;
    @FXML
    private StackPane helpStk;
    @FXML
    private StackPane profileStk;
    @FXML
    private StackPane logoutStk;
    StackPane[] stck;// = {dashBStk,messagesStk,helpStk,profileStk,logoutStk};

    //borderpane top stuff
    @FXML private Button profilePageBtn;
    @FXML private Circle imgCircle;
    @FXML private VBox upperRightVbox;




    //Components
    @FXML private TextField name;
    @FXML private DatePicker birthDate;
    @FXML private TextField work;
    @FXML private RadioButton male;
    @FXML private RadioButton female;
    @FXML private ToggleGroup gender;
    @FXML private TextField email;
    @FXML private TextField contactNo;
    @FXML private TextArea about;


    //right image components
    @FXML private Button changeProPic;
    @FXML private Button save;
    @FXML private Button upload;
    @FXML private Circle proPicCircle;

    Button[] picBtn;
    File file;

    RadioButton[] rdbtn;



    // private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";



    public void initialize() {
        parent.getChildren().add(toast.get());
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, searchStk, messagesStk,helpStk,profileStk,logoutStk};
        picBtn = new Button[]{profilePageBtn, changeProPic, upload};

        Image imgperson = new Image(new File(path + "woman.png").toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));
        proPicCircle.setFill(new ImagePattern(imgperson));

        rdbtn = new RadioButton[]{male,female};

        //upperRightLabels
        Label nameUser = new Label("Ayan Antik Khan ");
        nameUser.setStyle("-fx-font-weight:bold");

        Integer daysLeft = 2;
        Integer rentedBooks = 3;
        Integer deposit = 2000;

        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Next return: " + daysLeft + " days"),
                new Label("Rented: " + rentedBooks + " Books"),
                new Label("Money deposited:"),
                new Label("Tk " + deposit));


    }



    public void onHoverBox(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (stck[i].isHover() && i!=4) {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59, 56, 56));
            }
        }


    }

    public void endHoverBox(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (!stck[i].isHover() && i!=4) {
                stck[i].setStyle("-fx-background-color:#3b3838;");
                lbl[i].setTextFill(Color.rgb(217, 217, 217));
            }
        }

    }

    public void pressed(MouseEvent event)
    {
        for(int i=0; i<stck.length; i++)
        {
            if(stck[i].isPressed() && i!=4)
            {
                Windows w = new Windows(stck[i], i);
            }
        }

    }


    public void onHoverButton(MouseEvent event)
    {
        if(event.getSource()==save)
        {
            save.setStyle("-fx-background-color:#92e25d");
        }
        else
        {
            for(int i=0; i<picBtn.length; i++)
            {
                if(event.getSource()==picBtn[i])
                {
                    picBtn[i].setStyle("-fx-background-color:#92a2b9");
                }
            }
        }
    }


    public void endHoverButton(MouseEvent event)
    {
        if(event.getSource()==save)
        {
            save.setStyle("-fx-background-color:#70ad47");
        }
        else
        {
            for(int i=0; i<picBtn.length; i++)
            {
                if(event.getSource()==picBtn[i])
                {
                    picBtn[i].setStyle("-fx-background-color:#44546a");
                }
            }
        }
    }


    public void choosePic(MouseEvent event)
    {
        FileChooser fc = new FileChooser();
        //fc.getExtensionFilters().add( new FileChooser.ExtensionFilter("Text Document","*.txt"));
        file = fc.showOpenDialog(null);

        if (file==null)
        {
            upload.setDisable(true);
        }
        else{
            Image imgperson = new Image(file.toURI().toString());
            proPicCircle.setFill(new ImagePattern(imgperson));
            upload.setDisable(false);
        }

    }

    public void uploadPic(MouseEvent event)
    {
        //UPLOAD TO DATABASE
        //MUST DOOOO
    }

    public void rentBook(MouseEvent event)
    {

        String printRBtn = "";
        for(int i=0; i<3; i++) {
            if(rdbtn[i].isSelected())
            {
                printRBtn = rdbtn[i].getText();
                break;
            }
        }

        //DO THIS
        //MUST DO
        //MOST IMPORTANT PART
    }



    public void btnPressed(ActionEvent e)
    {
        Windows w = new Windows(profilePageBtn, 4);
    }

}


