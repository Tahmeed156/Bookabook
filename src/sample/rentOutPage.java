package sample;

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

import java.io.File;



public class rentOutPage {
    @FXML
    private VBox left;
    //left side labels;
    @FXML
    private Label dashBLbl;
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
    private StackPane messagesStk;
    @FXML
    private StackPane helpStk;
    @FXML
    private StackPane profileStk;
    @FXML
    private StackPane logoutStk;
    StackPane[] stck;// = {dashBStk,messagesStk,helpStk,profileStk,logoutStk};

    //borderpane top stuff
    @FXML
    private Circle imgCircle;
    @FXML
    private VBox upperRightVbox;



    //Components
    @FXML private TextField book;
    @FXML private TextField author;
    @FXML private TextField rentPrice;
    @FXML private TextField deposit;
    @FXML private RadioButton original;
    @FXML private RadioButton whitePrint;
    @FXML private RadioButton newsPrint;
    @FXML private ToggleGroup print;
    @FXML private TextArea review;

    @FXML private ListView<String> genre;
    ObservableList<String> genreList = FXCollections.observableArrayList("Action","Comedy","Horror",
            "Drama","History","Young-Adult","Kids","Classic","Fiction","Non-Fiction","Science");


    @FXML private ComboBox<String> condition;
    ObservableList<String> condList = FXCollections.observableArrayList("Perfect",
            "A little bit of tear", "Some tear", "Heavily used" );


    //right image components

    @FXML private Button choose;
    @FXML private Button upload;
    @FXML private Button rent;
    @FXML private ImageView img;

    Button[] picBtn;
    File file;

    @FXML private Rectangle rectangle;





    String path = "E:\\Projects\\CSE\\BookABook\\Code\\rentOutPage\\src\\sample\\Pictures\\";

    public void initialize() {
        lbl = new Label[]{dashBLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, messagesStk, helpStk, profileStk, logoutStk};
        picBtn = new Button[]{choose,upload};

        Image imgperson = new Image(new File(path + "woman.png").toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));

        //upperRightLabels
        Label nameUser = new Label("Jane Micheal ");
        nameUser.setStyle("-fx-font-weight:bold");

        Integer daysLeft = 2;
        Integer rentedBooks = 3;
        Integer deposit = 2000;

        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Next return: " + daysLeft + " days"),
                new Label("Rented: " + rentedBooks + " Books"),
                new Label("Money deposited:"),
                new Label("Tk " + deposit));

        genre.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        condition.setItems(condList);
        genre.setItems(genreList);

    }



    public void onHoverBox(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (stck[i].isHover()) {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59, 56, 56));
            }
        }


    }

    public void endHoverBox(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (!stck[i].isHover()) {
                stck[i].setStyle("-fx-background-color:#3b3838;");
                lbl[i].setTextFill(Color.rgb(217, 217, 217));
            }
        }

    }

    public void pressed(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (stck[i].isPressed()) {
                stck[i].setStyle("-fx-background-color:#383838;");
                lbl[i].setTextFill(Color.rgb(217, 217, 217));
                break;
            }
        }

    }


    public void released(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (event.getSource() == stck[i]) {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59, 56, 56));
                break;
            }
        }

    }


    public void onHoverButton(MouseEvent event)
    {
        if(event.getSource()==rent)
        {
            rent.setStyle("-fx-background-color:#92e25d");
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
        if(event.getSource()==rent)
        {
            rent.setStyle("-fx-background-color:#70ad47");
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
            img.setImage(new Image(file.toURI().toString()));
            upload.setDisable(false);
            rectangle.setVisible(false);
        }

    }

    public void uploadPic(MouseEvent event)
    {


        //UPLOAD TO DATABASE
        //MUST DOOOO
    }

    public void rentBook(MouseEvent event)
    {

        condition.getValue(); //to get value of condition combo box;
        //to get the value from genre list
        ObservableList<String> selectedGenre = genre.getSelectionModel().getSelectedItems();

        //DO THIS
        //MUST DO
        //MOST IMPORTANT PART
    }

}

