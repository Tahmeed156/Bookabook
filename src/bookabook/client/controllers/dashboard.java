package bookabook.client.controllers;

import bookabook.client.Main;

import bookabook.objects.Bookser;
import bookabook.server.models.Book;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.json.JSONException;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;


public class dashboard {

    static String userName;
    static String user;
    static String rentedOutBooks;
    static String rentedBooks;
    static String wallet;

    @FXML
    private Pane parent;
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
    private Label[] lbl;// = {searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl}; //cant do this
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
    StackPane[] stck;// = {searchStk,messagesStk,helpStk,profileStk,logoutStk};

    //borderpane top stuff
    @FXML
    private Button rentOutBtn;
    @FXML
    private Rectangle imgCircle;
    @FXML private Label rentedBLbl;
    @FXML private Label sharedBLbl;
    @FXML private Label walletLbl;
    @FXML private Label userNameLbl;
    @FXML private Label userLbl;


    @FXML private VBox upcoming;
    @FXML private VBox sharedBooks;


    //center HBoxs
    @FXML
    private HBox trendHbx;
    @FXML
    private HBox recHbx;

    public int tIndex = 0;
    public int rIndex = 0;
    int start;

    @FXML
    private VBox tVbox1;
    @FXML
    private VBox tVbox2;
    @FXML
    private VBox tVbox3;
    @FXML
    private VBox rVbox1;
    @FXML
    private VBox rVbox2;
    @FXML
    private VBox rVbox3;
    VBox[] tVbox;
    VBox[] rVbox;


    @FXML
    private Label tBookName1;
    @FXML
    private Label tBookName2;
    @FXML
    private Label tBookName3;
    @FXML
    private Label rBookName1;
    @FXML
    private Label rBookName2;
    @FXML
    private Label rBookName3;
    Label[] tlabel;
    Label[] rlabel;


    @FXML
    private Label tBookAuthor1;
    @FXML
    private Label tBookAuthor2;
    @FXML
    private Label tBookAuthor3;
    @FXML
    private Label rBookAuthor1;
    @FXML
    private Label rBookAuthor2;
    @FXML
    private Label rBookAuthor3;
    Label[] tAuthorLabel;
    Label[] rAuthorLabel;


    @FXML
    private ImageView tBookImage1;
    @FXML
    private ImageView tBookImage2;
    @FXML
    private ImageView tBookImage3;
    @FXML
    private ImageView rBookImage1;
    @FXML
    private ImageView rBookImage2;
    @FXML
    private ImageView rBookImage3;
    ImageView[] timgv;
    ImageView[] rimgv;


    @FXML
    private StackPane tstckLArrow;
    @FXML
    private StackPane tstckRArrow;
    @FXML
    private StackPane rstckLArrow;
    @FXML
    private StackPane rstckRArrow;


    @FXML
    private ImageView tLArrow;
    @FXML
    private ImageView tRArrow;
    @FXML
    private ImageView rLArrow;
    @FXML
    private ImageView rRArrow;
    @FXML
    private ImageView upArrow1;
    @FXML
    private ImageView upArrow2;
    @FXML
    private ImageView downArrow1;
    @FXML
    private ImageView downArrow2;
    ImageView[] arrows;


    String lblStyle = "-fx-text-fill: #3b3838; -fx-font-weight: bold; -fx-font-size: 15";
    // list for upcoming due dates
    List<String>  upBook = new ArrayList<>(Arrays.asList("Harry Potter", "Game of thrones", "Percy Jackson",
            "1984","Fantastic beasts and where to find them","Animal Farm","The Grand Theory"));
    List<String>  upRenter = new ArrayList<>(Arrays.asList("Jon", "Sherlock", "Percy","Antik","Tanjim","Sadat","Kane"));
    List<Integer>  udaysLeft = new ArrayList<>(Arrays.asList(1,3,2,4,5,6,7));
    List<String> uLabel = new ArrayList<>();
    int uIndex;


    // list for booksShared
    List<String>  sBook = new ArrayList<>(Arrays.asList("Harry Potter", "Game of thrones", "Percy Jackson",
            "1984","Fantastic beasts and where to find them","Animal Farm","The Grand Theory"));
    List<String>  sRenter = new ArrayList<>(Arrays.asList("Jon", "Sherlock", "Percy","Antik","Tanjim","Sadat","Kane"));
    List<Integer>  sdaysLeft = new ArrayList<>(Arrays.asList(1,3,2,4,5,6,7));
    List<String> sLabel = new ArrayList<>();
    int sIndex;



    // list for trending
    List<String> tname = new ArrayList<>();
    List<String> tauthor = new ArrayList<>();
    List<Image> timgs = new ArrayList<>();

    List<Bookser> trendingBooks;

    // list for recommended
    List<String> rname = new ArrayList<>();
    List<String> rauthor = new ArrayList<>();
    List<Image> rimgs = new ArrayList<>();

    List<Bookser> latestBooks;

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    //private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";


    public void initialize() throws IOException, JSONException, ClassNotFoundException, InterruptedException {
        parent.getChildren().add(toast.get());


        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, searchStk, messagesStk, helpStk, profileStk, logoutStk};

        tlabel = new Label[]{tBookName1, tBookName2, tBookName3};
        rlabel = new Label[]{rBookName1, rBookName2, rBookName3};
        tAuthorLabel = new Label[]{tBookAuthor1, tBookAuthor2, tBookAuthor3};
        rAuthorLabel = new Label[]{rBookAuthor1, rBookAuthor2, rBookAuthor3};
        timgv = new ImageView[]{tBookImage1, tBookImage2, tBookImage3};
        rimgv = new ImageView[]{rBookImage1, rBookImage2, rBookImage3};
        tVbox = new VBox[]{tVbox1, tVbox2, tVbox3};
        rVbox = new VBox[]{rVbox1, rVbox2, rVbox3};
        arrows = new ImageView[]{tLArrow, tRArrow, rLArrow, rRArrow,upArrow1,downArrow1,upArrow2,downArrow2};


        //initiate uLabel and sLabel lists
        for(int i=0; i<upBook.size(); i++)
        {
            uLabel.add(upBook.get(i) + " | " + upRenter.get(i) + "\n" + udaysLeft.get(i) + " days");
        }


        for(int i=0; i<sBook.size(); i++)
        {
            sLabel.add(sBook.get(i) + " | " + sRenter.get(i) + "\n" + sdaysLeft.get(i) + " days");
        }

        Loading t = new Loading();
        new Thread(t).start();

        Preferences userCon = Main.userCon;

        // todo NHS: take user full name as input
        userName = userCon.get("full_name", "BookABook");
        user = userCon.get("username", "admin");
        rentedBooks = userCon.get("books_rented", "0");
        rentedOutBooks = userCon.get("books_shared", "0");
        wallet = userCon.get("wallet", "0");

        //profilePicture
        File file4 = new File(path + "woman.png");
        Image imgperson = new Image(file4.toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));


        //upperLabels
        userNameLbl.setText(userName);
        userLbl.setText(user);
        rentedBLbl.setText(rentedBooks);
        sharedBLbl.setText(rentedOutBooks);
        walletLbl.setText(wallet);


        //populate upcoming
        uIndex = helper.initiate(uLabel, upcoming, 5, 370, lblStyle, downArrow1, uIndex,
                3, true, 0);
        sIndex = helper.initiate(sLabel, sharedBooks, 5, 370, lblStyle, downArrow2, sIndex,
                3, true, 0);
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

    public void pressed(MouseEvent event) {
        for (int i = 1; i < stck.length; i++) {
            if (stck[i].isPressed()) {
                Windows w = new Windows(stck[i], i);
            }
        }

    }

    public void rArrowClicked(MouseEvent event) {
        if (event.getSource() == tRArrow) {
            tIndex = helper.right_arrow_clicked(tname, tauthor, timgs, tstckRArrow, tstckLArrow, tVbox, tlabel,
                    tAuthorLabel, timgv, tIndex, 3);
        }

        if (event.getSource() == rRArrow) {
            rIndex = helper.right_arrow_clicked(rname, rauthor, rimgs, rstckRArrow, rstckLArrow, rVbox, rlabel,
                    rAuthorLabel, rimgv, rIndex, 3);

        }
    }

    public void lArrowClicked(MouseEvent event) {
        if (event.getSource() == tLArrow) {
            tIndex = helper.left_arrow_clicked(tname, tauthor, timgs, tstckRArrow, tstckLArrow, tVbox, tlabel,
                    tAuthorLabel, timgv, tIndex, 3);
        }

        if (event.getSource() == rLArrow) {
            rIndex = helper.left_arrow_clicked(rname, rauthor, rimgs, rstckRArrow, rstckLArrow, rVbox, rlabel,
                    rAuthorLabel, rimgv, rIndex, 3);
        }
    }

    public void uArrowClicked(MouseEvent event) {
        if(event.getSource()==upArrow1){
            uIndex = helper.up_arrow_clicked(uLabel, upcoming, 5, 370, lblStyle, downArrow1,
                    upArrow1, uIndex, 3, true, 0);
        }
        else{
            sIndex = helper.up_arrow_clicked(sLabel, sharedBooks, 5, 370, lblStyle, downArrow2,
                    upArrow2, sIndex, 3, true, 0);
        }
    }

    public void dArrowClicked(MouseEvent event) {
        if(event.getSource()==downArrow1){
            uIndex = helper.down_arrow_clicked(uLabel, upcoming, 5, 370, lblStyle, downArrow1,
                    upArrow1, uIndex, 3, true, 0);
        }
        else{
            sIndex = helper.down_arrow_clicked(sLabel, sharedBooks, 5, 370, lblStyle, downArrow2,
                    upArrow2, sIndex, 3, true, 0);
        }
    }


    public void onHoverArrow(MouseEvent event) {
        for (int i = 0; i < arrows.length; i++) {
            if (arrows[i].isHover()) {
                if (i == 1 || i == 3) {
                    arrows[i].setImage(new Image(new File(path + "rightAClicked.png").toURI().toString()));
                } else if( i==0 || i==2){
                    arrows[i].setImage(new Image(new File(path + "leftAClicked.png").toURI().toString()));
                } else if( i==4 || i==6){
                    arrows[i].setImage(new Image(new File(path + "upAClicked.png").toURI().toString()));
                }else {
                    arrows[i].setImage(new Image(new File(path + "downAClicked.png").toURI().toString()));
                }
            }

        }
    }

    public void endHoverArrow(MouseEvent event) {
        for (int i = 0; i < arrows.length; i++) {
            if (!arrows[i].isHover()) {
                if (i == 1 || i == 3) {
                    arrows[i].setImage(new Image(new File(path + "rightArrow.png").toURI().toString()));
                } else if( i==0 || i==2){
                    arrows[i].setImage(new Image(new File(path + "leftArrow.png").toURI().toString()));
                } else if( i==4 || i==6){
                    arrows[i].setImage(new Image(new File(path + "upArrow.png").toURI().toString()));
                }else {
                    arrows[i].setImage(new Image(new File(path + "downArrow.png").toURI().toString()));
                }
            }

        }
    }

    public void btnPressed(ActionEvent e) {
        Windows w = new Windows(rentOutBtn, 6);
    }


    public void onHoverButton(MouseEvent event) {

        rentOutBtn.setStyle("-fx-background-color:#92a2b9");

    }


    public void endHoverButton(MouseEvent event) {

        rentOutBtn.setStyle("-fx-background-color:#44546a");

    }

    public void bookPageClicked(MouseEvent event) {
        // todo NHS: pass on book id to next page
        for (int i = 0; i < timgv.length; i++) {
            if (event.getSource() == timgv[i]) {
                Windows w = new Windows(timgv[i], "../fxml/bookDetailsPage.fxml", tlabel[i].getText());
            }
        }

        for (int i = 0; i < rimgv.length; i++) {
            if (event.getSource() == rimgv[i]) {
                Windows w = new Windows(rimgv[i], "../fxml/bookDetailsPage.fxml", rlabel[i].getText());
            }
        }
    }

    public void onLabelHover(MouseEvent event) {
        ((Label) event.getSource()).setUnderline(true);
    }


    public void endLabelHover(MouseEvent event) {
        ((Label) event.getSource()).setUnderline(false);
    }

    public void labelClicked(MouseEvent event) {
        Windows w = new Windows((Label) event.getSource(), "../fxml/bookDetailsPage.fxml", ((Label) event.getSource()).getText());
    }

    class Loading extends Task {
        @Override
        public Void call() throws Exception {
            try {
                trendingBooks = Main.connection.latest_books("books/trending", "");
                //Thread.sleep(5000);
                for (Bookser b : trendingBooks) {
                    tname.add(b.getName());
                    tauthor.add(b.getAuthor());
                    timgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }

                latestBooks = Main.connection.latest_books("books/latest", "");
                for (Bookser b : latestBooks) {
                    rname.add(b.getName());
                    rauthor.add(b.getAuthor());
                    rimgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }


                // todo NHS: use this thread to get back info on rent out and rented books

            } catch (Exception e) {
                System.out.println("Couldn't load books");
            }


            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //populating trending
                    tIndex = helper.initiate(tname, tauthor, timgs, tstckRArrow, tVbox, tlabel, tAuthorLabel, timgv,
                            tIndex, 3);

                    //populating latest
                    tIndex = helper.initiate(rname, rauthor, rimgs, rstckRArrow, rVbox, rlabel, rAuthorLabel, rimgv,
                            rIndex, 3);

                    // todo NHS: populate rented and rented out books
                    // todo NHS: rented -> how long;
                    // todo NHS: rented out -> who rented , how long;
                 }
            });

            return null;
        }
    }
}

