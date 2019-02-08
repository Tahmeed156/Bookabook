package bookabook.client.controllers;

import bookabook.client.Main;
import bookabook.objects.Bookser;
import bookabook.server.models.Book;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class searchPage{
    @FXML private TextField searchTxt;
    @FXML private Pane parent;
    @FXML private VBox left;
    //left side labels;
    @FXML private Label dashBLbl;
    @FXML private Label searchLbl;
    @FXML private Label messagesLbl;
    @FXML private Label helpLbl;
    @FXML private Label profileLbl;
    @FXML private Label logoutLbl;
    @FXML private Label[] lbl;// = {dashBLbl, messagesLbl, helpLbl, profileLbl, logoutLbl}; //cant do this
    //because of how fxml loader acts

    //left side stacks
    @FXML private StackPane dashBStk;
    @FXML private StackPane searchStk;
    @FXML private StackPane messagesStk;
    @FXML private StackPane helpStk;
    @FXML private StackPane profileStk;
    @FXML private StackPane logoutStk;
    StackPane[] stck;// = {dashBStk,messagesStk,helpStk,profileStk,logoutStk};

    //borderpane top stuff
    @FXML private Rectangle imgCircle;
    @FXML private Label rentedBLbl;
    @FXML private Label sharedBLbl;
    @FXML private Label walletLbl;
    @FXML private Label userNameLbl;
    @FXML private Label userLbl;
    @FXML private VBox upperRightVbox;

    //searchButton
    @FXML private Button searchButton;


    public Integer index = 0;

    @FXML private VBox Vbox11;
    @FXML private VBox Vbox21;
    @FXML private VBox Vbox31;
    @FXML private VBox Vbox41;
    @FXML private VBox Vbox51;
    @FXML private VBox Vbox61;

    @FXML private VBox Vbox12;
    @FXML private VBox Vbox22;
    @FXML private VBox Vbox32;
    @FXML private VBox Vbox42;
    @FXML private VBox Vbox52;
    @FXML private VBox Vbox62;
    VBox[] Vbox1;
    VBox[] Vbox2;


    @FXML private Label BookName1;
    @FXML private Label BookName2;
    @FXML private Label BookName3;
    @FXML private Label BookName4;
    @FXML private Label BookName5;
    @FXML private Label BookName6;
    Label[] bookLabel;


    @FXML private Label BookAuthor1;
    @FXML private Label BookAuthor2;
    @FXML private Label BookAuthor3;
    @FXML private Label BookAuthor4;
    @FXML private Label BookAuthor5;
    @FXML private Label BookAuthor6;
    Label[] authorLabel;

    @FXML private Label rent1;
    @FXML private Label rent2;
    @FXML private Label rent3;
    @FXML private Label rent4;
    @FXML private Label rent5;
    @FXML private Label rent6;
    Label[] rentLabel;

    @FXML private Label deposit1;
    @FXML private Label deposit2;
    @FXML private Label deposit3;
    @FXML private Label deposit4;
    @FXML private Label deposit5;
    @FXML private Label deposit6;
    Label[] depositLabel;





    @FXML private ImageView img1;
    @FXML private ImageView img2;
    @FXML private ImageView img3;
    @FXML private ImageView img4;
    @FXML private ImageView img5;
    @FXML private ImageView img6;
    ImageView[] imgv;


    @FXML private StackPane stckLArrow;
    @FXML private StackPane stckRArrow;


    @FXML private ImageView lArrow;
    @FXML private ImageView rArrow;

    // // private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";

    //list for searched items
    List<String> name = new ArrayList<>();
    List<String> author = new ArrayList<>();
    List<Integer> rent = new ArrayList<>();
    List<Integer> depositArr = new ArrayList<>();
    List<Image> imgs = new ArrayList<>();
    List<Bookser> searchResults;

    public void initialize() throws IOException, JSONException, ClassNotFoundException {
        parent.getChildren().add(toast.get());
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, searchStk, messagesStk,helpStk,profileStk,logoutStk};

        bookLabel = new Label[]{BookName1,BookName2,BookName3,BookName4,BookName5,BookName6};
        authorLabel = new Label[]{BookAuthor1,BookAuthor2,BookAuthor3, BookAuthor4, BookAuthor5,BookAuthor6};
        rentLabel = new Label[]{rent1,rent2,rent3,rent4,rent5,rent6};
        depositLabel = new Label[]{deposit1,deposit2,deposit3,deposit4,deposit5,deposit6};
        imgv = new ImageView[]{img1,img2,img3,img4,img5,img6};
        Vbox1 = new VBox[]{Vbox11,Vbox21,Vbox31,Vbox41,Vbox51,Vbox61};
        Vbox2 = new VBox[]{Vbox12,Vbox22,Vbox32,Vbox42,Vbox52,Vbox62};

        // Loading l = new Loading();
        // new Thread(l).start();

        //search Button
        ImageView imgBtn = new ImageView(new Image(new File(path+"searchLogo.jpg").toURI().toString()));
        imgBtn.setFitHeight(25);
        imgBtn.setFitWidth(25);
        searchButton.setGraphic(imgBtn);


        //upperRightLabels
        Image imgperson = SwingFXUtils.toFXImage(dashboard.proPic, null);
        imgCircle.setFill(new ImagePattern(imgperson));

        String rentedOutBooks = dashboard.rentedOutBooks;
        String rentedBooks = dashboard.rentedBooks;
        String wallet = dashboard.wallet;

        userNameLbl.setText(dashboard.userName);
        userLbl.setText(dashboard.user);
        rentedBLbl.setText(rentedBooks);
        sharedBLbl.setText(rentedOutBooks);
        walletLbl.setText(wallet);
    }

    public void onHoverBox(MouseEvent event)
    {


        if(event.getSource()==searchButton)
        {
            searchButton.setStyle("-fx-background-color:#595656");
            ImageView imgBtn = new ImageView(new Image(new File(path+"searchLogoChanged.jpg").toURI().toString()));
            imgBtn.setFitHeight(25);
            imgBtn.setFitWidth(25);
            searchButton.setGraphic(imgBtn);
        }
        else {
            for (int i = 0; i < stck.length; i++) {
                 if(i==1){continue;}
                 if (stck[i].isHover()) {
                    stck[i].setStyle("-fx-background-color:#b9b9b9;");
                    lbl[i].setTextFill(Color.rgb(59, 56, 56));
                }
            }
        }

    }

    public void endHoverBox(MouseEvent event)
    {
        if(event.getSource()==searchButton)
        {
            searchButton.setStyle("-fx-background-color:#000000");
            ImageView imgBtn = new ImageView(new Image(new File(path+"searchLogo.jpg").toURI().toString()));
            imgBtn.setFitHeight(25);
            imgBtn.setFitWidth(25);
            searchButton.setGraphic(imgBtn);
        }
        else {
            for (int i = 0; i < stck.length; i++) {
                if(i==1){continue;}
                if (!stck[i].isHover()) {
                    stck[i].setStyle("-fx-background-color:#3b3838;");
                    lbl[i].setTextFill(Color.rgb(217, 217, 217));
                }
            }
        }
    }

    public void pressed(MouseEvent event)
    {
        for(int i=0; i<stck.length; i++)
        {
            if(stck[i].isPressed() && i!=1)
            {
                Windows w = new Windows(stck[i], i);
            }
        }

    }


    public void rArrowClicked(MouseEvent event)
    {
        index = helper.right_arrow_clicked(name,author,rent,depositArr,imgs,stckRArrow,stckLArrow,Vbox1,Vbox2,
                bookLabel,authorLabel,rentLabel,depositLabel,imgv,index,6);
    }

    public void lArrowClicked(MouseEvent event)
    {
        index = helper.left_arrow_clicked(name,author,rent,depositArr,imgs,stckRArrow,stckLArrow,Vbox1,Vbox2,
                bookLabel,authorLabel,rentLabel,depositLabel,imgv,index,6);
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


    public void bookPageClicked(MouseEvent event) {
        // todo NHS: pass on book id
        for (int i = 0; i < imgv.length; i++) {
            if (event.getSource() == imgv[i]) {
                Windows w = new Windows(imgv[i], "../fxml/bookDetailsPage.fxml", name.get(i));
            }
        }
    }

    public void searchedItem(MouseEvent event) throws IOException, JSONException, ClassNotFoundException {
        searchResults.clear();
        stckRArrow.setVisible(false);
        name.clear();
        author.clear();
        rent.clear();
        depositArr.clear();
        imgs.clear();
        searchResults = Main.connection.latest_books("books/search", searchTxt.getText());
        for (Bookser b: searchResults) {
            name.add(b.getName());
            author.add(b.getAuthor());
            rent.add((int)b.getRent());
            depositArr.add((int)b.getDeposit());
            imgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
        }

        for (int boxIn = 5; boxIn >= 0; boxIn--) {
                Vbox1[boxIn].setVisible(false);
                Vbox2[boxIn].setVisible(false);
                imgv[boxIn].setImage(null);
            }

        //populating the box
        index = helper.initiate(name,author,rent,depositArr,imgs,stckRArrow,Vbox1,Vbox2,
                bookLabel,authorLabel,rentLabel,depositLabel,imgv,index,6);
    }

    class Loading extends Task {
        @Override
        public Void call() throws Exception {
            try {
                searchResults = Main.connection.latest_books("books/search","");
                for (Bookser b: searchResults) {
                    name.add(b.getName());
                    author.add(b.getAuthor());
                    rent.add((int)b.getRent());
                    depositArr.add((int)b.getDeposit());
                    imgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
                }

            }catch(Exception e)
            {
                System.out.println("Couldn't load books");
            }


            Platform.runLater(new Runnable() {
                @Override public void run() {
                    //populating the box
                    index = helper.initiate(name,author,rent,depositArr,imgs,stckRArrow,Vbox1,Vbox2,
                            bookLabel,authorLabel,rentLabel,depositLabel,imgv,index,6);

                }
            });

            return null;
        }
    }








}
