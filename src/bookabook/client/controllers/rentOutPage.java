package bookabook.client.controllers;

import bookabook.client.Main;
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


public class rentOutPage {
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
    @FXML private Button mainPageBtn;
    @FXML private Circle imgCircle;
    @FXML private VBox upperRightVbox;




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
    @FXML private TextField yearBought;

    @FXML private ListView<String> genre;
    ObservableList<String> genreList = FXCollections.observableArrayList("Action","Comedy","Horror",
            "Drama","History","Young-Adult","Kids","Classic","Fiction","Non-Fiction","Science");


    @FXML private ComboBox<String> condition;
    ObservableList<String> condList = FXCollections.observableArrayList("Perfect",
            "A little bit of tear", "Some tear", "Heavily used" );

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    //private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";


    //right image components

    @FXML private Button choose;
    @FXML private Button upload;
    @FXML private Button rent;
    @FXML private ImageView img;

    Button[] picBtn;
    File file;

    @FXML private Rectangle rectangle;
    RadioButton[] rdbtn;

    public void initialize() {
        parent.getChildren().add(toast.get());
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, searchStk, messagesStk,helpStk,profileStk,logoutStk};
        picBtn = new Button[]{choose,upload,mainPageBtn};

        Image imgperson = new Image(new File(path + "woman.png").toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));

        rdbtn = new RadioButton[]{original,whitePrint,newsPrint};

        //upperRightLabels
        Label nameUser = new Label("Ayan Antik Khan ");
        nameUser.setStyle("-fx-font-weight:bold");

        Integer daysLeft = dashboard.daysLeft;
        Integer rentedBooks = dashboard.rentedBooks;
        Integer deposit = dashboard.deposit;

        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Next return: " + daysLeft + " days"),
                new Label("Rented: " + rentedBooks + " Books"),
                new Label("Money deposited:"),
                new Label("Tk " + deposit));

        //genre.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        condition.setItems(condList);
        genre.setItems(genreList);

    }



    public void onHoverBox(MouseEvent event) {
        for (int i = 1; i < stck.length; i++) {
            if (stck[i].isHover()) {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59, 56, 56));
            }
        }


    }

    public void endHoverBox(MouseEvent event) {
        for (int i = 1; i < stck.length; i++) {
            if (!stck[i].isHover()) {
                stck[i].setStyle("-fx-background-color:#3b3838;");
                lbl[i].setTextFill(Color.rgb(217, 217, 217));
            }
        }

    }

    public void pressed(MouseEvent event)
    {
        for(int i=1; i<stck.length; i++)
        {
            if(stck[i].isPressed())
            {
                Windows w = new Windows(stck[i], i);
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
        // to get value of condition combo box
        // condition.getValue();
        // to get the value from genre list
        // ObservableList<String> selectedGenre = genre.getSelectionModel().getSelectedItems();
        // to get value from bookprint

//        for(int i=0; i<3; i++) {
//            if(rdbtn[i].isSelected())
//            {
//                System.out.println(rdbtn[i].getText());
//                break;
//            }
//        }

        String printRBtn = "";
        for(int i=0; i<3; i++) {
            if(rdbtn[i].isSelected())
            {
                printRBtn = rdbtn[i].getText();
                System.out.println(printRBtn);
                break;
            }
        }




//        Book b = new Book();
//        JSONObject bookInfo = new JSONObject();
//        try {
//            bookInfo.put("print", printRBtn);
//            bookInfo.put("condition", condition.getValue());
//            bookInfo.put("review", review.getText());
//            bookInfo.put("year_bought", "2017");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        b.rent(
//                book.getText(),
//                author.getText(),
//                Double.valueOf(rentPrice.getText()),
//                Double.valueOf(deposit.getText()),
//                bookInfo.toString()
//        );
//
//
//
//
//        SessionFactory sf = new Configuration().configure("/bookabook/client/hibernate.cfg.xml").buildSessionFactory();
//        Session s = sf.openSession();
//        s.beginTransaction();
//        s.save(b);
//        s.getTransaction().commit();
//        s.close();



        boolean success = Main.connection.rentOutBook(
                1,
                book.getText(),
                author.getText(),
                Double.valueOf(rentPrice.getText()),
                Double.valueOf(deposit.getText()),
                genre.getSelectionModel().getSelectedItem(),
                printRBtn,
                condition.getValue(),
                review.getText(),
                yearBought.getText()
        );

        if (success) {
            toast.set("SUCCESSFULLY RENTED OUT","#5CB85C");
            Windows w = new Windows(rent, "../fxml/dashboard.fxml");
        }
        else {
            toast.set("UNABLE TO RENT OUT","#D9534F");
        }


    }


    public void btnPressed(ActionEvent e)
    {
        Windows w = new Windows(mainPageBtn, 0);
    }

}


