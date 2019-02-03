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
    private Circle imgCircle;
    @FXML
    private VBox upperRightVbox;

    //middle part
    @FXML
    private Circle bigImgCircle;
    @FXML
    private Label bigName;
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
    @FXML
    private Button rentOutBtn;
    @FXML
    private Button rentedBtn;
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

    //list for rented books
    List<String> bname = new ArrayList<>();
    List<String> bauthor = new ArrayList<>();
    List<Image> bimgs = new ArrayList<>();
    List<Bookser> rentedOutBooks;

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    //private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";


    public void initialize() {
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
        btn = new Button[]{rentBookBtn, editProfileBtn, rentOutBtn, rentedBtn};


        Loading l = new Loading();
        new Thread(l).start();


        //profilePicture
        File file4 = new File(path + "woman.png");
        Image imgperson = new Image(file4.toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));

        Image bigimgperson = new Image(file4.toURI().toString());
        bigImgCircle.setFill(new ImagePattern(bigimgperson));


        //upperRightLabels
        Label nameUser = new Label(dashboard.userName);
        nameUser.setStyle("-fx-font-weight:bold");

        String rentedOutBooks = dashboard.rentedOutBooks;
        String rentedBooks = dashboard.rentedBooks;
        String wallet = dashboard.wallet;

        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Rented Out: " + rentedOutBooks + " Books"),
                new Label("Rented: " + rentedBooks + " Books"),
                new Label("Money deposited:"),
                new Label("Tk " + wallet));


        bigName.setText("Ayan Antik Khan");
        work.setText("Intern at the king's guard");
        birthDate.setText("11th March 2018");
        loc.setText("Winterfell");
        email.setText("jmike@gmail.com");
        contact.setText("54654685");


    }

    public void onHoverBox(MouseEvent event) {


        for (int i = 0; i < stck.length; i++) {
            if (stck[i].isHover() && i != 4) {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59, 56, 56));
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

    }

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

        for (int i = 0; i < 4; i++) {
            if (event.getSource() == btn[i]) {
                if (i == 0 || i == 1) {
                    btn[i].setStyle("-fx-background-color:#92a2b9");
                } else {
                    btn[i].setStyle("-fx-background-color:#92e25d");
                }
            }
        }

    }


    public void endHoverButton(MouseEvent event) {

        for (int i = 0; i < 4; i++) {
            if (event.getSource() == btn[i]) {
                if (i == 0 || i == 1) {
                    btn[i].setStyle("-fx-background-color:#44546a");
                } else {
                    btn[i].setStyle("-fx-background-color:#70ad47");
                }
            }
        }

    }


    public void editProClicked(MouseEvent e) {
        Windows w = new Windows(editProfileBtn, 7);
    }

    public void rentABookClicked(MouseEvent e) {
        Windows w = new Windows(rentBookBtn, 6);
    }

    public void rentedBooksClicked(MouseEvent e) {
        Windows w = new Windows(rentedBtn, 8);
    }

    public void rentedOutBooksClicked(MouseEvent e) {
        Windows w = new Windows(rentOutBtn, 9);
    }


    class Loading extends Task {
        @Override
        public Void call() throws Exception {
            try {
                rentedBooks = Main.connection.latest_books("books/rented", "");
                for (Bookser b : rentedBooks) {
                    tname.add(b.getName());
                    tauthor.add(b.getAuthor());
                    timgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }

                rentedOutBooks = Main.connection.latest_books("books/rent_out", "");
                for (Bookser b : rentedOutBooks) {
                    bname.add(b.getName());
                    bauthor.add(b.getAuthor());
                    bimgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }

            } catch (Exception e) {
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
