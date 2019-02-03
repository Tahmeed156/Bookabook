package bookabook.client.controllers;

import bookabook.client.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;


public class bookDetailsPage {
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
    @FXML
    private Circle imgCircle;
    @FXML
    private VBox upperRightVbox;

    //body
    @FXML private Circle imgCircleCenter;
    @FXML private VBox middleRightVBox;

    //book
    @FXML private Label BookName;
    @FXML private Label BookAuthor;
    @FXML private ImageView BookImg;
    @FXML private Label BookRent;
    @FXML private Label BookDeposit;

    @FXML private Button rentBtn;
    @FXML private Button messageBtn;
    @FXML private Button sendBtn;
    Button[] btn;

    @FXML private TextField weeks;
    @FXML private TextArea bookReview;

    @FXML private Label print;
    @FXML private Label condition;
    @FXML private Label yearBought;
    @FXML private Label timesRented;

    @FXML private TextArea reviewByRenter;
    @FXML private Text genre; //use '\n' between the genres;
    @FXML private Text similarBook; //use '\n' between the book links;


    //Reviews Section
    @FXML private StackPane stckLArrow;
    @FXML private StackPane stckRArrow;
    @FXML private ImageView lArrow;
    @FXML private ImageView rArrow;



    @FXML private VBox Vbox1;
    @FXML private VBox Vbox2;
    @FXML private VBox Vbox3;
    VBox[] vbx;
    @FXML private Label revName1;
    @FXML private Label revName2;
    @FXML private Label revName3;
    Label[] revName;
    @FXML private Text review1;
    @FXML private Text review2;
    @FXML private Text review3;
    Text[] rev;

    String bookGenre;
    String[] allsimilarBooks;
    Integer index;

    static String s;
    int book_rent;
    int book_deposit;

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    //private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";

    List<String> reviewArr = new ArrayList<>(Arrays.asList("Book quite good",
            "Maybe the world will be set right by this book",
            "Look out for the journey of your lifetime",
            "Dont mess with GRRM he is the fantasy king",
            "Book was goood i guess"));


    List<String> reviewers = new ArrayList<>(Arrays.asList("Mary  4.5 ★","Sherlock  5 ★",
            "Watson 3.5 ★","Bruce  5 ★","Stark  2 ★"));


    public void info(String str)
    {
        s = str;
    }

    public void initialize(String s)
    {
        parent.getChildren().add(toast.get());
        System.out.println(s);
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, searchStk, messagesStk,helpStk,profileStk,logoutStk};
        bookGenre = "Fantasy";
        allsimilarBooks = new String[]{"Harry Potter","Lord of the Rings","Percy Jackson"};


        vbx = new VBox[]{Vbox1,Vbox2,Vbox3};
        revName = new Label[]{revName1,revName2,revName3};
        rev = new Text[]{review1,review2,review3};




        btn = new Button[]{rentBtn,messageBtn,sendBtn};


        //upperRightLabels

        //profilePicture
        Image imgperson = new Image(new File(path+"woman.png").toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));


        Label nameUser = new Label(dashboard.userName);
        nameUser.setStyle("-fx-font-weight:bold");

        String rentedOutBooks = dashboard.rentedOutBooks;
        String rentedBooks = dashboard.rentedBooks;
        String wallet = dashboard.wallet;

        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Rented Out: "+rentedOutBooks+" Books"),
                new Label("Rented: "+rentedBooks+" Books"),
                new Label("Money deposited:"),
                new Label("Tk "+wallet));



        //middleCenterLabels

        //profilePicture
        Image imgperson1 = new Image(new File(path+"jon.png").toURI().toString());
        imgCircleCenter.setFill(new ImagePattern(imgperson1));


        Label nameUser1 = new Label("Jon Snow ");
        nameUser1.setStyle("-fx-font-weight:bold");


        Integer renterRentedBooks = 3;
        //create a ratings Label???
        Integer reviewsOfrenter = 10;


        middleRightVBox.getChildren().addAll(nameUser1,
                new Label("Rented: "+renterRentedBooks+" Books"),
                new Label("Reviews: "+reviewsOfrenter));

        //setting book details
        book_rent = 10;
        book_deposit = 300;
        BookName.setText("Game of Thrones");
        BookAuthor.setText("George R R Martin");
        BookRent.setText("Tk "+book_rent+" per week");
        BookDeposit.setText("Tk "+book_deposit);


        BookImg.setImage(new Image(new File(path+"book.png").toURI().toString()));

        print.setText("Original");
        condition.setText("Optimal");
        yearBought.setText("2017");
        timesRented.setText("3");
        reviewByRenter.setText("The book is undoubtedly a masterpiece created by George R R Martin. Best thing" +
                "that happened to the world after J.K.Rowling.");





        genre.setText(bookGenre);


        for(String a:allsimilarBooks)
        {
            similarBook.setText(similarBook.getText().concat(a + '\n'));
        }


        //populating reviews
        for(index = 0; index<3; index++)
        {
            vbx[index].setVisible(true);
            revName[index].setText(reviewers.get(index));
            rev[index].setText(reviewArr.get(index));
        }

        //making right arrow visible
        if(reviewers.size()>3)
        {
            stckRArrow.setVisible(true);
        }


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
            if(stck[i].isPressed())
            {
                Windows w = new Windows(stck[i], i);
            }
        }

    }





    public void onHoverButton(MouseEvent event)
    {
        for(int i=0; i<btn.length; i++)
        {
            if(event.getSource()==btn[i])
            {
                btn[i].setStyle("-fx-background-color:#92a2b9");
            }
        }

    }


    public void endHoverButton(MouseEvent event)
    {
        for(int i=0; i<btn.length; i++)
        {
            if(event.getSource()==btn[i])
            {
                btn[i].setStyle("-fx-background-color:#44546a");
            }
        }

    }

    public void onHoverArrow(MouseEvent event)
    {
        if(rArrow.isHover())
        {
            rArrow.setImage(new Image(new File(path+"rightAClicked.png").toURI().toString()));
        }

        if(lArrow.isHover())
        {
            lArrow.setImage(new Image(new File(path+"leftAClicked.png").toURI().toString()));
        }
    }

    public void endHoverArrow(MouseEvent event)
    {
        if(!rArrow.isHover())
        {
            rArrow.setImage(new Image(new File(path+"rightArrow.png").toURI().toString()));
        }

        if(!lArrow.isHover())
        {
            lArrow.setImage(new Image(new File(path+"leftArrow.png").toURI().toString()));
        }
    }



    public void rArrowClicked(MouseEvent event)
    {
        //if right arrow clicked and more books available
        if(index<reviewers.size()) {
            //set left arrow visible
            stckLArrow.setVisible(true);
            //making boxes invisible
            for (int boxIn = 2; boxIn >= 0; boxIn--) {
                vbx[boxIn].setVisible(false);
            }

            //populating remaining boxes
            int i = 0;
            while (index < reviewers.size() && i < 3) {
                vbx[i].setVisible(true);
                revName[i].setText(reviewers.get(index));
                rev[i].setText(reviewArr.get(index));
                index++;
                i++;
            }

            //making arrow disabled if no more books
            //System.out.println(tIndex);
            if (index >= reviewers.size()) {
                stckRArrow.setVisible(false);
            }
        }
    }

    public void lArrowClicked(MouseEvent event)
    {

        //finding out the (index+1) value of the rightmost box
        if(index%3==0) {
            index-=3;
        }
        else{
            index -= index%3;
        }

        //populate these boxes
        int boxIn = 0;
        while(boxIn<=2)
        {
            revName[boxIn].setText(reviewers.get(index-(3-boxIn)));
            rev[boxIn].setText(reviewArr.get(index-(3-boxIn)));
            vbx[boxIn].setVisible(true);
            boxIn++;
        }
        //System.out.println(tIndex);

        //if leftmost box contain last book then left arrow will be invisible
        if((index-3)<=0)
        {
            stckLArrow.setVisible(false);
        }
        stckRArrow.setVisible(true);
    }


    public void rentBtnPressed(MouseEvent e){

        if(weeks.getText().isEmpty()){
            toast.set("PLEASE FILL IN THE NO OF WEEKS TEXTFIELD","#f0ad4e");
        }
        else {
            Boolean success = Main.connection.rentBook(
                    1,
                    Integer.valueOf(weeks.getText()),
                    2,
                    2
            );

            if (success) {
                toast.set("SUCCESSFULLY RENTED", "#5CB85C");
                Preferences userCon = Main.userCon;
                dashboard.rentedBooks = String.valueOf(Integer.valueOf(dashboard.rentedBooks) + 1);
                userCon.put("books_rented", dashboard.rentedBooks);

                Integer payment = (Integer.valueOf(weeks.getText()) * book_rent) + book_deposit;
                dashboard.wallet = String.valueOf(Double.valueOf(dashboard.wallet) - payment);
                userCon.put("wallet",dashboard.wallet);

                Windows w = new Windows(rentBtn, "../fxml/profilePage.fxml");
            } else {
                toast.set("UNABLE TO RENT", "#D9534F");
            }
        }

    }

    public void messageBtnPressed(MouseEvent e){
        Windows w = new Windows(messageBtn, "../fxml/messenger.fxml");
    }

    public void sendBtnPressed(MouseEvent e){
        if(bookReview.getText().isEmpty())
        {
            toast.set("PLEASE ENTER YOUR REVIEW","#f0ad4e");
        }
        else
        {
            reviewers.add(0,dashboard.userName);
            reviewArr.add(0,bookReview.getText());

            stckLArrow.setVisible(false);
            //populating reviews
            for(index = 0; index<3; index++)
            {
                vbx[index].setVisible(true);
                revName[index].setText(reviewers.get(index));
                rev[index].setText(reviewArr.get(index));
            }

            //making right arrow visible
            if(reviewers.size()>3)
            {
                stckRArrow.setVisible(true);
            }
        }
    }





}
