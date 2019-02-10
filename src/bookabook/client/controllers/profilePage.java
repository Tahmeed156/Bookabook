package bookabook.client.controllers;

import bookabook.client.Main;
import bookabook.objects.Bookser;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class profilePage {
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
    @FXML private Label rentedBLbl;
    @FXML private Label sharedBLbl;
    @FXML private Label walletLbl;
    //middle part
    @FXML
    private Rectangle bigImgCircle;
    @FXML
    private Label bigName;
    @FXML
    private Label user;
    @FXML
    private Label work;
    @FXML
    private Label birthDate;
    @FXML
    private Label email;
    @FXML
    private Label loc;
    @FXML
    private Label contact;

    @FXML
    private Button rentBookBtn;
    @FXML
    private Button editProfileBtn;
    Button[] btn;


    public Integer tIndex = 0;
    public Integer bIndex = 0;

    @FXML
    private VBox tVbox1;
    @FXML
    private VBox tVbox2;
    @FXML
    private VBox tVbox3;
    @FXML
    private VBox bVbox1;
    @FXML
    private VBox bVbox2;
    @FXML
    private VBox bVbox3;
    VBox[] tVbox;
    VBox[] bVbox;


    @FXML
    private Label tBookName1;
    @FXML
    private Label tBookName2;
    @FXML
    private Label tBookName3;
    @FXML
    private Label bBookName1;
    @FXML
    private Label bBookName2;
    @FXML
    private Label bBookName3;
    Label[] tlabel;
    Label[] blabel;


    @FXML
    private Label tBookAuthor1;
    @FXML
    private Label tBookAuthor2;
    @FXML
    private Label tBookAuthor3;
    @FXML
    private Label bBookAuthor1;
    @FXML
    private Label bBookAuthor2;
    @FXML
    private Label bBookAuthor3;
    Label[] tAuthorLabel;
    Label[] bAuthorLabel;


    @FXML
    private ImageView tBookImage1;
    @FXML
    private ImageView tBookImage2;
    @FXML
    private ImageView tBookImage3;
    @FXML
    private ImageView bBookImage1;
    @FXML
    private ImageView bBookImage2;
    @FXML
    private ImageView bBookImage3;
    ImageView[] timgv;
    ImageView[] bimgv;


    @FXML
    private StackPane tstckLArrow;
    @FXML
    private StackPane tstckRArrow;
    @FXML
    private StackPane bstckLArrow;
    @FXML
    private StackPane bstckRArrow;


    @FXML
    private ImageView tLArrow;
    @FXML
    private ImageView tRArrow;
    @FXML
    private ImageView bLArrow;
    @FXML
    private ImageView bRArrow;
    ImageView[] arrows;


    //list for rented out books
    List<String> tname = new ArrayList<>();
    List<String> tauthor = new ArrayList<>();
    List<Image> timgs = new ArrayList<>();
    List<Bookser> rentedBooks;
    List<Integer> tID = new ArrayList<>();

    //list for rented books
    List<String> bname = new ArrayList<>();
    List<String> bauthor = new ArrayList<>();
    List<Image> bimgs = new ArrayList<>();
    List<Bookser> rentedOutBooks;
    List<Integer> bID = new ArrayList<>();

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    // private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";


    public void initialize() throws IOException, ClassNotFoundException {
        parent.getChildren().add(toast.get());
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, searchStk, messagesStk, helpStk, profileStk, logoutStk};

        tlabel = new Label[]{tBookName1, tBookName2, tBookName3};
        blabel = new Label[]{bBookName1, bBookName2, bBookName3};
        tAuthorLabel = new Label[]{tBookAuthor1, tBookAuthor2, tBookAuthor3};
        bAuthorLabel = new Label[]{bBookAuthor1, bBookAuthor2, bBookAuthor3};
        timgv = new ImageView[]{tBookImage1, tBookImage2, tBookImage3};
        bimgv = new ImageView[]{bBookImage1, bBookImage2, bBookImage3};
        tVbox = new VBox[]{tVbox1, tVbox2, tVbox3};
        bVbox = new VBox[]{bVbox1, bVbox2, bVbox3};
        arrows = new ImageView[]{tLArrow, tRArrow, bLArrow, bRArrow};
        btn = new Button[]{rentBookBtn, editProfileBtn};


        Loading l = new Loading();
        new Thread(l).start();


        //upperRightLabels

        Image imgperson = SwingFXUtils.toFXImage(dashboard.proPic, null);
        bigImgCircle.setFill(new ImagePattern(imgperson));

        String rentedOutBooks = dashboard.rentedOutBooks;
        String rentedBooks = dashboard.rentedBooks;
        String wallet = dashboard.wallet;

        user.setText(dashboard.user);
        rentedBLbl.setText(rentedBooks);
        sharedBLbl.setText(rentedOutBooks);
        walletLbl.setText(wallet);
        bigName.setText(dashboard.userName);
    }


    // ========================== ON HOVER FUNCTIONS ========================
    public void onHoverBox(MouseEvent event) {


        for (int i = 0; i < stck.length; i++) {
            if (stck[i].isHover() && i != 4) {
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


        for(int i = 0; i<bVbox.length; i++)
        {
            if(bimgv[i].isHover())
            {
                bVbox[i].setStyle("-fx-background-color: #d9d9d9");
            }
        }

    }

    public void endHoverBox(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (!stck[i].isHover() && i != 4) {
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


        for(int i = 0; i<bVbox.length; i++)
        {
            if(!bimgv[i].isHover())
            {
                bVbox[i].setStyle("-fx-background-color: #ffffff");
            }
        }

    }

    public void onHoverArrow(MouseEvent event) {
        for (int i = 0; i < arrows.length; i++) {
            if (arrows[i].isHover()) {
                if (i == 1 || i == 3) {
                    arrows[i].setImage(new Image(new File(path + "rightAClicked.png").toURI().toString()));
                } else {
                    arrows[i].setImage(new Image(new File(path + "leftAClicked.png").toURI().toString()));
                }
            }

        }
    }

    public void endHoverArrow(MouseEvent event) {
        for (int i = 0; i < arrows.length; i++) {
            if (!arrows[i].isHover()) {
                if (i == 1 || i == 3) {
                    arrows[i].setImage(new Image(new File(path + "rightArrow.png").toURI().toString()));
                } else {
                    arrows[i].setImage(new Image(new File(path + "leftArrow.png").toURI().toString()));
                }
            }

        }
    }

    public void onHoverButton(MouseEvent event) {

        for (int i = 0; i < 2; i++) {
            if (event.getSource() == btn[i]) {
                btn[i].setStyle("-fx-background-color:#d9d9d9; -fx-border-width: 3; -fx-text-fill:#3b3838");
            }
        }
    }

    public void endHoverButton(MouseEvent event) {

        for (int i = 0; i < 2; i++) {
            if (event.getSource() == btn[i]) {
                btn[i].setStyle("-fx-background-color: #3b3838; -fx-border-width: 3; -fx-border-color: #d9d9d9;" +
                        "fx-text-fill: #ffffff");
            }
        }
    }


    // ========================== ON PRESSED FUNCTIONS ========================
    public void pressed(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (stck[i].isPressed() && i != 4) {
                Windows w = new Windows(stck[i], i);
            }
        }

    }

    public void rArrowClicked(MouseEvent event) {
        if (event.getSource() == tRArrow) {
            tIndex = helper.right_arrow_clicked(tname, tauthor, timgs, tstckRArrow, tstckLArrow, tVbox, tlabel,
                    tAuthorLabel, timgv, tIndex, 3);
        }


        if (event.getSource() == bRArrow) {
            bIndex = helper.right_arrow_clicked(bname, bauthor, bimgs, bstckRArrow, bstckLArrow, bVbox, blabel,
                    bAuthorLabel, bimgv, bIndex, 3);

        }
    }

    public void lArrowClicked(MouseEvent event) {
        if (event.getSource() == tLArrow) {
            tIndex = helper.left_arrow_clicked(tname, tauthor, timgs, tstckRArrow, tstckLArrow, tVbox, tlabel,
                    tAuthorLabel, timgv, tIndex, 3);
        }

        if (event.getSource() == bLArrow) {
            bIndex = helper.left_arrow_clicked(bname, bauthor, bimgs, bstckRArrow, bstckLArrow, bVbox, blabel,
                    bAuthorLabel, bimgv, bIndex, 3);
        }
    }

    public void editProClicked(MouseEvent e) {
        Windows w = new Windows(editProfileBtn, 7);
    }

    public void rentABookClicked(MouseEvent e) {
        Windows w = new Windows(rentBookBtn, 6);
    }

    public void bookPageClicked(MouseEvent event) {
        // todo NHS: pass on book id to next page
        for (int i = 0; i < timgv.length; i++) {
            if (event.getSource() == timgv[i]) {
                Windows w = new Windows(timgv[i], "/bookabook/client/fxml/bookDetailsPage.fxml", tID.get(tIndex + i));
            }
        }

        for (int i = 0; i < bimgv.length; i++) {
            if (event.getSource() == bimgv[i]) {
                Windows w = new Windows(bimgv[i], "/bookabook/client/fxml/bookDetailsPage.fxml",bID.get(bIndex + i));
            }
        }
    }

    // One thread to load them alls
    class Loading extends Task {
        @Override
        public Void call() throws Exception {
            try {
                JSONObject response = new JSONObject(Main.connection.getProfile(Integer.parseInt(dashboard.userId)));
                if (Boolean.valueOf(response.getString("success"))){
                    work.setText(response.optString("work","N/A"));
                    birthDate.setText(response.optString("date_of_birth","N/A"));
                    loc.setText(response.optString("location","N/A"));
                    email.setText(response.optString("email","N/A"));
                    contact.setText(response.optString("contact_no","N/A"));
                }

                rentedBooks = Main.connection.latest_books(Integer.valueOf(dashboard.userId), "books/rented", "");
                for (Bookser b : rentedBooks) {
                    tID.add(b.getId());
                    tname.add(b.getName());
                    tauthor.add(b.getAuthor());
                    timgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }

                rentedOutBooks = Main.connection.latest_books(Integer.valueOf(dashboard.userId),"books/rented_out", "");
                for (Bookser b : rentedOutBooks) {
                    bID.add(b.getId());
                    bname.add(b.getName());
                    bauthor.add(b.getAuthor());
                    bimgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }

            } catch (Exception e) {
                toast.set("COULDN'T LOAD BOOKS","#f0ad4e");
                System.out.println("Couldn't load books");
            }


            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //populating top (Rented out books)
                    tIndex = helper.initiate(tname, tauthor, timgs, tstckRArrow, tVbox, tlabel, tAuthorLabel, timgv,
                            tIndex, 3);

                    //populating bottom (rented books)
                    bIndex = helper.initiate(bname, bauthor, bimgs, bstckRArrow, bVbox, blabel, bAuthorLabel, bimgv,
                            bIndex, 3);

                }
            });

            return null;
        }
    }
}
