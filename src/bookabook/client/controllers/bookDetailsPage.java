package bookabook.client.controllers;

import bookabook.client.Main;
import bookabook.objects.Bookser;
import com.mysql.cj.xdevapi.JsonArray;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;


public class bookDetailsPage {
    public static BufferedImage book_image;
    public static BufferedImage renter_image;

    int renter_id;

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
    private VBox upperRightVbox;
    @FXML private Rectangle imgCircle;
    @FXML private Label rentedBLbl;
    @FXML private Label sharedBLbl;
    @FXML private Label walletLbl;
    @FXML private Label userNameLbl;
    @FXML private Label userLbl;

    //body
    @FXML private Circle imgCircleCenter;
    @FXML private VBox middleRightVBox;
    //Labels
    @FXML private Label owner;
    @FXML private Label address;
    @FXML private Label contact;

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


    @FXML private Label lb1;
    @FXML private Label lb2;
    @FXML private Label lb3;
    @FXML private Label lb4;
    @FXML private Label lb5;
    Label[] lb;

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

    static int id;
    int book_rent;
    int book_deposit;

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    // private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";

    List<String> reviewArr = new ArrayList<>();
//    (Arrays.asList("Book quite good",
//            "Maybe the world will be set right by this book",
//            "Look out for the journey of your lifetime",
//            "Dont mess with GRRM he is the fantasy king",
//            "Book was goood i guess"));


    List<String> reviewers = new ArrayList<>();
//    Arrays.asList("Mary  4.5 ★","Sherlock  5 ★",
//            "Watson 3.5 ★","Bruce  5 ★","Stark  2 ★"));



    public void initialize(int book_id) throws IOException, ClassNotFoundException, JSONException {
        parent.getChildren().add(toast.get());
        System.out.println(book_id);
        id = book_id;
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, searchStk, messagesStk,helpStk,profileStk,logoutStk};
        bookGenre = "Fantasy";
        allsimilarBooks = new String[]{"Harry Potter","Lord of the Rings","Percy Jackson"};
        lb = new Label[]{lb1,lb2,lb3,lb4,lb5};


        vbx = new VBox[]{Vbox1,Vbox2,Vbox3};
        revName = new Label[]{revName1,revName2,revName3};
        rev = new Text[]{review1,review2,review3};




        btn = new Button[]{rentBtn,messageBtn,sendBtn};


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





//        //setting book details
//        book_rent = 10;
//        book_deposit = 300;
//        BookName.setText("Game of Thrones");
//        BookAuthor.setText("George R R Martin");
//        BookRent.setText("Tk "+book_rent+" per week");
//        BookDeposit.setText("Tk "+book_deposit);
//
//
//        BookImg.setImage(new Image(new File(path+"pj.png").toURI().toString()));
//
//        print.setText("Original");
//        condition.setText("Optimal");
//        yearBought.setText("2017");
//        timesRented.setText("3");
//        reviewByRenter.setText("The book is undoubtedly a masterpiece created by George R R Martin. Best thing" +
//                "that happened to the world after J.K.Rowling.");
//
//        genre.setText(bookGenre);

        for(int i = 0; i<5; i++)
        {
            if(i>=allsimilarBooks.length)
            {
                lb[i].setText("");
            }
            else{lb[i].setText(allsimilarBooks[i]);}
        }

        Loading l = new Loading();
        new Thread(l).start();


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

    public void rentBtnPressed(MouseEvent e) throws IOException, ClassNotFoundException, JSONException {

        if(weeks.getText().isEmpty()){
            toast.set("PLEASE FILL THE NO OF WEEKS YOU WANT TO RENT","#f0ad4e");
        }
        else {
            JSONObject response = new JSONObject(Main.connection.rentBook(
                    id,
                    Integer.valueOf(weeks.getText()),
                    renter_id,
                    Integer.parseInt(dashboard.userId)
            ));

            if (Boolean.valueOf(response.getString("success"))) {
                toast.set("SUCCESSFULLY RENTED", "#5CB85C");
                Preferences userCon = Main.userCon;
                dashboard.rentedBooks = String.valueOf(Integer.valueOf(dashboard.rentedBooks) + 1);
                userCon.put("books_rented", dashboard.rentedBooks);

                Integer payment = (Integer.valueOf(weeks.getText()) * book_rent) + book_deposit;
                dashboard.wallet = String.valueOf(Double.valueOf(dashboard.wallet) - payment);
                userCon.put("wallet",dashboard.wallet);

                new Windows(rentBtn, "../fxml/dashboard.fxml");
            } else {
                toast.set("UNABLE TO RENT", "#D9534F");
            }
        }

    }

    public void messageBtnPressed(MouseEvent e){
        Windows w = new Windows(messageBtn, "../fxml/messenger.fxml");
    }

    public void sendBtnPressed(MouseEvent e) throws IOException, ClassNotFoundException, JSONException {
        if(bookReview.getText().isEmpty())
        {
            toast.set("PLEASE ENTER YOUR REVIEW","#f0ad4e");
        }
        else
        {
            JSONObject response = new JSONObject(Main.connection.reviewAdd(
                    Integer.parseInt(dashboard.userId),
                    1,//static
                    bookReview.getText()
                    ));


            reviewers.add(0,dashboard.userName);
            reviewArr.add(0,bookReview.getText());

            stckLArrow.setVisible(false);
            //populating reviews
            for(index = 0; index<3 && index<reviewers.size(); index++)
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

    public void onLabelHover(MouseEvent event) {
        ((Label) event.getSource()).setUnderline(true);
    }


    public void endLabelHover(MouseEvent event) {
        ((Label) event.getSource()).setUnderline(false);
    }

    public void labelClicked(MouseEvent event) {
        Windows w = new Windows((Label) event.getSource(), "../fxml/bookDetailsPage.fxml", 1);
    }

    class Loading extends Task {
        @Override
        public Void call() throws Exception {
            try {
                //populate info
                //JSONObject response = new JSONObject(Main.connection.getBookDetails(Integer.parseInt(id)));
                //System.out.println(response.toString());





                // Populating reviews
                JSONArray response_arr1 = new JSONArray(Main.connection.reviewGet(id));
                for (int i=0; i<response_arr1.length(); i++) {
                    JSONObject review = response_arr1.getJSONObject(i);
                    // username
                    reviewers.add(review.getString("username"));
                    // body
                    reviewArr.add(review.getString("body"));
                }

                for(index = 0; index<3 && index<reviewers.size(); index++)
                {
                    vbx[index].setVisible(true);
                    revName[index].setText(reviewers.get(index));
                    rev[index].setText(reviewArr.get(index));
                }


                // Making right arrow visible
                if(reviewers.size()>3)
                {
                    stckRArrow.setVisible(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Couldn't load reviews");

            }


            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    JSONObject response = null;
                    try {
                        response = new JSONObject(Main.connection.getBookDetails(id));
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    if (Boolean.valueOf(response.getString("success"))){
                        BookName.setText(response.getString("book"));
                        // author name
                        BookAuthor.setText(response.getString("author"));
                        // book rent
                        book_rent = (int)response.getDouble("rent");
                        // book deposit
                        book_deposit = (int)response.getDouble("deposit");
                        // book print
                        print.setText(response.getString("print"));
                        // book condition
                        condition.setText(response.getString("condition"));
                        // year bought
                        yearBought.setText(response.getString("year_bought"));
                        // times Rented
                        timesRented.setText(String.valueOf(response.getInt("times_rented")));
                        // review
                        reviewByRenter.setText(response.getString("review"));
                        // genre
                        genre.setText(response.getString("genre"));
                        //get renter id
                        renter_id = response.getInt("owner_id");
                        owner.setText(response.getString("owner_name"));
                        address.setText(response.getString("owner_location"));
                        contact.setText(response.getString("owner_contact"));
                    }

                    BookRent.setText(Integer.toString(book_rent));
                    BookDeposit.setText(Integer.toString(book_deposit));
                    BookImg.setImage(SwingFXUtils.toFXImage(book_image, null));
                    Image imgperson1 = SwingFXUtils.toFXImage(renter_image, null);
                    imgCircleCenter.setFill(new ImagePattern(imgperson1));
                }
            });

            return null;
        }
    }





}
