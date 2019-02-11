package bookabook.client.controllers;

import bookabook.client.Main;

import bookabook.objects.Bookser;
import bookabook.server.models.Book;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;


public class dashboard {

    public static String userName;
    public static String userId;
    public static BufferedImage proPic;
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
    private Label[] lbl;

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
    StackPane[] stck;

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

    @FXML private Button ret1;
    @FXML private Button ret2;
    @FXML private Button ret3;
    @FXML private Button req1;
    @FXML private Button req2;
    @FXML private Button req3;
    static Button[] btn;



    String lblStyle = "-fx-text-fill: #3b3838; -fx-font-weight: bold; -fx-font-size: 15";
    // list for upcoming due dates
    List<String>  upBook = new ArrayList<>();
    List<String>  upRenter = new ArrayList<>();
    List<Integer>  udaysLeft = new ArrayList<>();
    List<String> uLabel = new ArrayList<>();
    List<Integer> uID = new ArrayList<>();
    int uIndex;


    // list for booksShared
    List<String>  sBook = new ArrayList<>();
    List<String>  sRenter = new ArrayList<>();
    List<Integer>  sdaysLeft = new ArrayList<>();
    List<String> sLabel = new ArrayList<>();
    List<Integer> sID = new ArrayList<>();
    int sIndex;
    static List<String> sStatus = new ArrayList<>();

    // list for trending
    List<String> tname = new ArrayList<>();
    List<String> tauthor = new ArrayList<>();
    List<Image> timgs = new ArrayList<>();
    List<Bookser> trendingBooks;
    List<Integer> tID = new ArrayList<>();

    // list for recommended
    List<String> rname = new ArrayList<>();
    List<String> rauthor = new ArrayList<>();
    List<Image> rimgs = new ArrayList<>();
    List<Bookser> latestBooks;
    List<Integer> rID = new ArrayList<>();

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    // private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";
    // private String path = "Pictures\\";


    public void initialize() throws IOException, ClassNotFoundException {
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
        btn = new Button[]{ret1, ret2, ret3, req1, req2, req3, rentOutBtn};

        // Getting client info from registry
        Preferences userCon = Main.userCon;
        userName = userCon.get("full_name", "BookABook");
        user = userCon.get("username", "admin");
        userId = userCon.get("id","1");

        JSONObject response = new JSONObject(Main.connection.getProfile(Integer.parseInt(dashboard.userId)));
        System.out.println(response.toString());
        if (Boolean.valueOf(response.getString("success"))){
            userCon.put("wallet", response.getString("wallet"));
            userCon.put("books_rented", response.getString("books_rented"));
            userCon.put("books_shared", response.getString("books_shared"));
        }

        // Starting thread for loading books and user information
        Loading t = new Loading();
        new Thread(t).start();

        rentedBooks = userCon.get("books_rented", "0");
        rentedOutBooks = userCon.get("books_shared", "0");
        wallet = userCon.get("wallet", "0");


        // upperLabels
        userNameLbl.setText(userName);
        userLbl.setText(user);
        rentedBLbl.setText(rentedBooks);
        sharedBLbl.setText(rentedOutBooks);
        walletLbl.setText(wallet);

    }


    // ========================== ON HOVER FUNCTIONS ========================

    public void onHoverBox(MouseEvent event) {


        for (int i = 1; i < stck.length; i++) {
            if (stck[i].isHover()) {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59, 56, 56));
            }
        }

        for(int i = 0; i<tVbox.length; i++)
        {
            if(timgv[i].isHover())
            {
                tVbox[i].setStyle("-fx-background-color: #d9d9d9");
            }
        }


        for(int i = 0; i<rVbox.length; i++)
        {
            if(rimgv[i].isHover())
            {
                rVbox[i].setStyle("-fx-background-color: #d9d9d9");
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


        for(int i = 0; i<tVbox.length; i++)
        {
            if(!timgv[i].isHover())
            {
                tVbox[i].setStyle("-fx-background-color: #ffffff");
            }
        }


        for(int i = 0; i<rVbox.length; i++)
        {
            if(!rimgv[i].isHover())
            {
                rVbox[i].setStyle("-fx-background-color: #ffffff");
            }
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

    public void onHoverButton(MouseEvent event) {

        for (int i = 0; i < btn.length; i++) {
            if (event.getSource() == btn[i]) {
                if(i==6){ btn[i].setStyle("-fx-background-color:#92a2b9"); }
                else { btn[i].setStyle("-fx-background-color:#d9d9d9; -fx-border-width: 3; -fx-text-fill:#3b3838");}
            }
        }

    }

    public void endHoverButton(MouseEvent event) {

        for (int i = 0; i < btn.length; i++) {
            if (event.getSource() == btn[i]) {
                if(i==6){ btn[i].setStyle("-fx-background-color:#44546a"); }
                else { btn[i].setStyle("-fx-background-color: #3b3838; -fx-border-width: 3; -fx-border-color: #d9d9d9; " +
                        "fx-text-fill: #ffffff");}
            }
        }

    }

    // ========================== ON PRESSED FUNCTIONS ========================
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
                    upArrow2, sIndex, 3, true, 3);
        }
    }

    public void dArrowClicked(MouseEvent event) {
        if(event.getSource()==downArrow1){
            uIndex = helper.down_arrow_clicked(uLabel, upcoming, 5, 370, lblStyle, downArrow1,
                    upArrow1, uIndex, 3, true, 0);
        }
        else{
            sIndex = helper.down_arrow_clicked(sLabel, sharedBooks, 5, 370, lblStyle, downArrow2,
                    upArrow2, sIndex, 3, true, 3);
        }
    }

    public void btnPressed(ActionEvent e) {
        // to open rent out page
        Windows w = new Windows(rentOutBtn, 6);
    }

    public void bookPageClicked(MouseEvent event) {
        for (int i = 0; i < timgv.length; i++) {
            if (event.getSource() == timgv[i]) {
                Windows w = new Windows(timgv[i], "/bookabook/client/fxml/bookDetailsPage.fxml", tID.get(tIndex+i));
            }
        }

        for (int i = 0; i < rimgv.length; i++) {
            if (event.getSource() == rimgv[i]) {
                Windows w = new Windows(rimgv[i], "/bookabook/client/fxml/bookDetailsPage.fxml",rID.get(rIndex+i));
            }
        }
    }

    public void requestClicked(MouseEvent e) throws IOException, ClassNotFoundException {
        for(int i=3; i<6; i++) {
            if (btn[i] == e.getSource()) {
                if (btn[i].getText().equals("Request")) {
                    JSONObject response = new JSONObject(Main.connection.requestBook(
                            sID.get(sIndex - (i - 3)),
                            Integer.parseInt(userId)
                    ));

                    if (Boolean.valueOf(response.getString("success"))) {
                        toast.set("REQUEST SUCCESSFULLY SENT", "#5cb85c");
                    } else {
                        toast.set("UNABLE TO SEND REQUEST", "#f0ad4e");
                    }
                } else {
                    JSONObject response = new JSONObject(Main.connection.confirmBook(
                            sID.get(sIndex - (i - 3)),
                            Integer.parseInt(userId)
                    ));
                    if (Boolean.valueOf(response.getString("success"))) {
                        Windows w = new Windows(btn[i], 0);
                        toast.set("SUCCESSFULLY CONFIRMED", "#5cb85c");
                    } else {
                        toast.set("UNABLE TO CONFIRM", "#f0ad4e");
                    }
                }
                break;
            }
        }
    }

    public void returnClicked(MouseEvent e) throws IOException, ClassNotFoundException {
        for(int i=0; i<3; i++){
            if(btn[i] == e.getSource())
            {
                JSONObject response = new JSONObject(Main.connection.returnBook(
                        uID.get(uIndex - i),
                        Integer.parseInt(userId)
                ));

                if(Boolean.valueOf(response.getString("success")))
                {
                    Windows w = new Windows(btn[i], 0);
                    toast.set("BOOK SUCCESSFULLY RETURNED","#5cb85c");
                }
                else {toast.set("UNABLE TO RETURN BOOK","#f0ad4e");}
                break;
            }
        }

    }


    // One thread class to load them all
    class Loading extends Task {
        @Override
        public Void call() throws Exception {
            try {
                // Gets profile picture from the server
                if (dashboard.proPic==null) {
                    Main.connection.getProPic();
                }

                // sets profile picture
                Image imgperson = SwingFXUtils.toFXImage(dashboard.proPic, null);
                imgCircle.setFill(new ImagePattern(imgperson));

                JSONArray response_arr = new JSONArray(Main.connection.getBooks(
                        "books/upcoming",
                        Integer.parseInt(dashboard.userId)
                ));

                for (int i=0; i<response_arr.length(); i++) {
                    JSONObject upcoming = response_arr.getJSONObject(i);
                    // bookname
                    upBook.add(upcoming.getString("book_name"));
                    // renter
                    upRenter.add(upcoming.getString("renter_name"));
                    // time remaining
                    udaysLeft.add((int)upcoming.getDouble("date"));
                    // book id
                    uID.add(upcoming.getInt("book_id"));
                }



                JSONArray response_arr1 = new JSONArray(Main.connection.getBooks(
                        "books/shared",
                        Integer.parseInt(dashboard.userId)
                ));

                for (int i=0; i<response_arr1.length(); i++) {
                    JSONObject shared = response_arr1.getJSONObject(i);
                    // bookname
                    sBook.add(shared.getString("book_name"));
                    // renter
                    sRenter.add(shared.getString("rentee_name"));
                    // time remaining
                    sdaysLeft.add((int)shared.getDouble("date"));
                    // book id
                    sID.add(shared.getInt("book_id"));
                    // get book status
                    sStatus.add(shared.getString("status"));
                }




                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // initiate uLabel and sLabel lists
                        for(int i=0; i<upBook.size(); i++)
                        {
                            uLabel.add(upBook.get(i) + " | " + upRenter.get(i) + "\n" + udaysLeft.get(i) + " days");
                        }
                        for(int i=0; i<sBook.size(); i++)
                        {
                            sLabel.add(sBook.get(i) + " | " + sRenter.get(i) + "\n" + sdaysLeft.get(i) + " days");
                        }

                        //populate upcoming
                        uIndex = helper.initiate(uLabel, upcoming, 5, 370, lblStyle, downArrow1, uIndex,
                                3, true, 0);
                        sIndex = helper.initiate(sLabel, sharedBooks, 5, 370, lblStyle, downArrow2, sIndex,
                                3, true, 3);
                    }
                });

                // Getting trending books from server
                trendingBooks = Main.connection.latest_books(Integer.valueOf(dashboard.userId),"books/trending", "");
                // Thread.sleep(5000);
                for (Bookser b : trendingBooks) {
                    tID.add(b.getId());
                    tname.add(b.getName());
                    tauthor.add(b.getAuthor());
                    timgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        // populating trending
                        tIndex = helper.initiate(tname, tauthor, timgs, tstckRArrow, tVbox, tlabel, tAuthorLabel, timgv,
                                tIndex, 3);

                    }
                });

                // Getting latest books from server
                latestBooks = Main.connection.latest_books(Integer.valueOf(dashboard.userId),"books/latest", "");
                for (Bookser b : latestBooks) {
                    rID.add(b.getId());
                    rname.add(b.getName());
                    rauthor.add(b.getAuthor());
                    rimgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }


            } catch (Exception e) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        toast.set("COULDN'T LOAD BOOKS","#f0ad4e");
                    }});
                e.printStackTrace();
                System.out.println("Couldn't load books");
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // populating latest
                    rIndex = helper.initiate(rname, rauthor, rimgs, rstckRArrow, rVbox, rlabel, rAuthorLabel, rimgv,
                            rIndex, 3);
                 }
            });

            return null;
        }
    }
}

