package bookabook.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

import java.io.File;


public class bookDetailsPage {
    @FXML private Pane parent;
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
    @FXML private Button reviewBtn;
    Button[] btn;

    @FXML private Label print;
    @FXML private Label condition;
    @FXML private Label yearBought;
    @FXML private Label timesRented;

    @FXML private Text reviewByRenter;
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

    String[] allBookGenre;
    String[] allsimilarBooks;
    Integer index;

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    // private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";

    String[] reviewArr = new String[]{"Book quite good",
            "Maybe the world will be set right by this book",
            "Look out for the journey of your lifetime",
            "Dont mess with GRRM he is the fantasy king",
            "Book was goood i guess"};

    String[] reviewers = new String[]{"Mary","Sherlock","Watson","Bruce","Stark"};



    public void initialize()
    {
        parent.getChildren().add(toast.get());
        lbl = new Label[]{dashBLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk,messagesStk,helpStk,profileStk,logoutStk};
        allBookGenre = new String[]{"Mystery","Action","Fantasy"};
        allsimilarBooks = new String[]{"Harry Potter","Lord of the Rings","Percy Jackson"};


        vbx = new VBox[]{Vbox1,Vbox2,Vbox3};
        revName = new Label[]{revName1,revName2,revName3};
        rev = new Text[]{review1,review2,review3};




        btn = new Button[]{rentBtn,messageBtn,reviewBtn};


        //upperRightLabels

        //profilePicture
        Image imgperson = new Image(new File(path+"woman.png").toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));


        Label nameUser = new Label("Jane Micheal ");
        nameUser.setStyle("-fx-font-weight:bold");

        Integer daysLeft = 2;
        Integer rentedBooks = 3;
        Integer deposit = 2000;

        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Next return: "+daysLeft+" days"),
                new Label("Rented: "+rentedBooks+" Books"),
                new Label("Money deposited:"),
                new Label("Tk "+deposit));



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
        BookName.setText("Game of Thrones");
        BookAuthor.setText("George R R Martin");
        BookRent.setText("Tk 10 per week");
        BookDeposit.setText("Tk 300");


        BookImg.setImage(new Image(new File(path+"book.png").toURI().toString()));

        print.setText("Original");
        condition.setText("Optimal");
        yearBought.setText("2017");
        timesRented.setText("3");
        reviewByRenter.setText("The book is undoubtedly a masterpiece created by George R R Martin dbkjbdkhbkjd" +
                "sskcishkjbksboubxijnxinixsnknxsinsijnisxnisnxxsninxssxbcbhn");




        for(String a:allBookGenre)
        {
            genre.setText(genre.getText().concat(a + "\n"));
        }

        for(String a:allsimilarBooks)
        {
            similarBook.setText(similarBook.getText().concat(a + '\n'));
        }


        //populating reviews
        for(index = 0; index<3; index++)
        {
            vbx[index].setVisible(true);
            revName[index].setText(reviewers[index]);
            rev[index].setText(reviewArr[index]);
        }

        //making right arrow visible
        if(reviewers.length>3)
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
        if(index<reviewers.length) {
            //set left arrow visible
            stckLArrow.setVisible(true);
            //making boxes invisible
            for (int boxIn = 2; boxIn >= 0; boxIn--) {
                vbx[boxIn].setVisible(false);
            }

            //populating remaining boxes
            int i = 0;
            while (index < reviewers.length && i < 3) {
                vbx[i].setVisible(true);
                revName[i].setText(reviewers[index]);
                rev[i].setText(reviewArr[index]);
                index++;
                i++;
            }

            //making arrow disabled if no more books
            //System.out.println(tIndex);
            if (index >= reviewers.length) {
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
            revName[boxIn].setText(reviewers[index-(3-boxIn)]);
            rev[boxIn].setText(reviewArr[index-(3-boxIn)]);
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





}
