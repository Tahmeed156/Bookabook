package bookabook.client.controllers;

import bookabook.client.Main;

import bookabook.objects.Bookser;
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

    static int daysLeft = 2;
    static int rentedBooks = 3;
    static int deposit = 2000;

    @FXML private Pane parent;
    @FXML private VBox left;
    //left side labels;
    @FXML private Label dashBLbl;
    @FXML private Label searchLbl;
    @FXML private Label messagesLbl;
    @FXML private Label helpLbl;
    @FXML private Label profileLbl;
    @FXML private Label logoutLbl;
    @FXML private Label[] lbl;// = {searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl}; //cant do this
    //because of how fxml loader acts

    //left side stacks
    @FXML private StackPane dashBStk;
    @FXML private StackPane searchStk;
    @FXML private StackPane messagesStk;
    @FXML private StackPane helpStk;
    @FXML private StackPane profileStk;
    @FXML private StackPane logoutStk;
    StackPane[] stck;// = {searchStk,messagesStk,helpStk,profileStk,logoutStk};

    //borderpane top stuff
    @FXML private Button rentOutBtn;
    @FXML private Circle imgCircle;
    @FXML private VBox upperRightVbox;

    //center HBoxs
    @FXML private HBox trendHbx;
    @FXML private HBox recHbx;

    public Integer tIndex = 0;
    public Integer rIndex = 0;

    @FXML private VBox tVbox1;
    @FXML private VBox tVbox2;
    @FXML private VBox tVbox3;
    @FXML private VBox rVbox1;
    @FXML private VBox rVbox2;
    @FXML private VBox rVbox3;
    VBox[] tVbox;
    VBox[] rVbox;


    @FXML private Label tBookName1;
    @FXML private Label tBookName2;
    @FXML private Label tBookName3;
    @FXML private Label rBookName1;
    @FXML private Label rBookName2;
    @FXML private Label rBookName3;
    Label[] tlabel;
    Label[] rlabel;


    @FXML private Label tBookAuthor1;
    @FXML private Label tBookAuthor2;
    @FXML private Label tBookAuthor3;
    @FXML private Label rBookAuthor1;
    @FXML private Label rBookAuthor2;
    @FXML private Label rBookAuthor3;
    Label[] tAuthorLabel;
    Label[] rAuthorLabel;




    @FXML private ImageView tBookImage1;
    @FXML private ImageView tBookImage2;
    @FXML private ImageView tBookImage3;
    @FXML private ImageView rBookImage1;
    @FXML private ImageView rBookImage2;
    @FXML private ImageView rBookImage3;
    ImageView[] timgv;
    ImageView[] rimgv;


    @FXML private StackPane tstckLArrow;
    @FXML private StackPane tstckRArrow;
    @FXML private StackPane rstckLArrow;
    @FXML private StackPane rstckRArrow;


    @FXML private ImageView tLArrow;
    @FXML private ImageView tRArrow;
    @FXML private ImageView rLArrow;
    @FXML private ImageView rRArrow;
    ImageView[] arrows;



//    //list for trending
    List<String> tname = new ArrayList<>();
    List<String> tauthor = new ArrayList<>();
//    Image[] timgs = new Image[50];
    List<Image> timgs = new ArrayList<>();

    List<Bookser> trendingBooks;

    //list for recommended
    List<String> rname = new ArrayList<>();
    List<String> rauthor = new ArrayList<>();
    List<Image> rimgs = new ArrayList<>();

    List<Bookser> latestBooks;

    // private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";


    public void initialize() throws IOException, JSONException, ClassNotFoundException, InterruptedException {
        parent.getChildren().add(toast.get());


        Preferences userCon = Main.userCon;

        // Saving information in the registry
        // TODO: DO THIS ON LOG_IN
        userCon.put("days_left", String.valueOf(daysLeft));
        userCon.put("rented_books", String.valueOf(rentedBooks));
        userCon.put("deposit", String.valueOf(deposit));



        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk,searchStk,messagesStk,helpStk,profileStk,logoutStk};

        tlabel = new Label[]{tBookName1,tBookName2,tBookName3};
        rlabel = new Label[]{rBookName1,rBookName2,rBookName3};
        tAuthorLabel = new Label[]{tBookAuthor1,tBookAuthor2,tBookAuthor3};
        rAuthorLabel = new Label[]{rBookAuthor1,rBookAuthor2,rBookAuthor3};
        timgv = new ImageView[]{tBookImage1,tBookImage2,tBookImage3};
        rimgv = new ImageView[]{rBookImage1,rBookImage2,rBookImage3};
        tVbox = new VBox[]{tVbox1,tVbox2,tVbox3};
        rVbox = new VBox[]{rVbox1,rVbox2,rVbox3};
        arrows = new ImageView[]{tLArrow,tRArrow,rLArrow,rRArrow};



        trendingBooks = Main.connection.latest_books("books/trending","");
        //Thread.sleep(5000);
        for (Bookser b: trendingBooks) {
            tname.add(b.getName());
            tauthor.add(b.getAuthor());
            timgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
        }

        latestBooks = Main.connection.latest_books("books/latest","");
        for (Bookser b: latestBooks) {
            rname.add(b.getName());
            rauthor.add(b.getAuthor());
            rimgs.add(SwingFXUtils.toFXImage(b.getImage(), null));
        }

//
//        //tbook1
//        timgs[0] = new Image(new File(path+"hp.png").toURI().toString());
//
//        //tbook2
//        timgs[1] = new Image(new File(path+"pj.png").toURI().toString());
//
//        //tbook3
//
//        timgs[2] = new Image( new File(path+"animalFarm.png").toURI().toString());
//
//        //tbook4
//        timgs[3] = new Image( new File(path+"foundation.png").toURI().toString());
//
//        //tbook5
//        timgs[4] = new Image(new File(path+"animalFarm.png").toURI().toString());
//
//        //tbook6
//        timgs[5] = new Image(new File(path+"1984.png").toURI().toString());
//
//        //tbook7
//        timgs[6] = new Image(new File(path+"hp.png").toURI().toString());

        //rbook1
//        rimgs[0] = new Image(new File(path+"book.png").toURI().toString());
//
//
//        //rbook2
//        rimgs[1] = new Image(new File(path+"1984.png").toURI().toString());
//
//        //rbook3
//        rimgs[2] = new Image(new File(path+"animalFarm.png").toURI().toString());
//
//        //tbook4
//        rimgs[3] = new Image(new File(path+"foundation.png").toURI().toString());


        //profilePicture
        File file4 = new File(path+"woman.png");
        Image imgperson = new Image(file4.toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));




        //upperRightLabels
        Label nameUser = new Label("Ayan Antik Khan ");
        nameUser.setStyle("-fx-font-weight:bold");


        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Next return: " + daysLeft+" days"),
                new Label("Rented: " + rentedBooks+" Books"),
                new Label("Money deposited:"),
                new Label("Tk " + deposit));


        //populating trending
        for(tIndex = 0 ; tIndex<tname.size() && tIndex<=2; tIndex++)
        {
            tVbox[tIndex].setVisible(true);
            tlabel[tIndex].setText(tname.get(tIndex));
            tAuthorLabel[tIndex].setText(tauthor.get(tIndex));
            timgv[tIndex].setImage(timgs.get(tIndex));

        }
        //Here tIndex value increments +1 at the end. So value is 3 not 2. Can be used as a normal value

        //make right arrow visible if more books available
        if(tname.size()>3)
        {
            tstckRArrow.setVisible(true);
        }

        //populating recommended
        for(rIndex = 0 ; rIndex<rname.size() && rIndex<3; rIndex++)
        {
            rVbox[rIndex].setVisible(true);
            rlabel[rIndex].setText(rname.get(rIndex));
            rAuthorLabel[rIndex].setText(rauthor.get(rIndex));
            rimgv[rIndex].setImage(rimgs.get(rIndex));

        }

        //make right arrow visible if more books available
        if(rname.size()>3)
        {
            rstckRArrow.setVisible(true);
        }


    }

    public void onHoverBox(MouseEvent event)
    {


        for(int i=1; i<stck.length; i++)
        {
            if(stck[i].isHover())
            {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59,56,56));
            }
        }

    }

    public void endHoverBox(MouseEvent event)
    {
        for(int i=1; i<stck.length; i++)
        {
            if(!stck[i].isHover())
            {
                stck[i].setStyle("-fx-background-color:#3b3838;");
                lbl[i].setTextFill(Color.rgb(217,217,217));
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

    public void rArrowClicked(MouseEvent event)
    {
        if(event.getSource()==tRArrow)
        {
            //if right arrow clicked and more books available
           if(tIndex<tname.size())
           {
               //set left arrow visible
               tstckLArrow.setVisible(true);
               //making boxes and images invisible
               for(int boxIn = 2; boxIn>=0; boxIn--)
               {
                   tVbox[boxIn].setVisible(false);
                   timgv[boxIn].setImage(null);
               }

               //populating remaining boxes
               int i = 0;
               while(tIndex<tname.size() && i<3)
               {
                   tVbox[i].setVisible(true);
                   tlabel[i].setText(tname.get(tIndex));
                   tAuthorLabel[i].setText(tauthor.get(tIndex));
                   timgv[i].setImage(timgs.get(tIndex));
                   tIndex++;
                   i++;
               }

               //making arrow disabled if no more books
               //System.out.println(tIndex);
               if(tIndex>=tname.size())
               {
                   tstckRArrow.setVisible(false);
               }
           }
        }


        if(event.getSource()==rRArrow)
        {
            if(rIndex<rname.size())
            {
                //set left arrow visible
                rstckLArrow.setVisible(true);
                //making invisible boxes and images
                for(int boxIn = 2; boxIn>=0; boxIn--)
                {
                    rVbox[boxIn].setVisible(false);
                    rimgv[boxIn].setImage(null);
                }
                //populate remaining boxes
                int i = 0;
                while(rIndex<rname.size() && i<3)
                {
                    rVbox[i].setVisible(true);
                    rlabel[i].setText(rname.get(rIndex));
                    rAuthorLabel[i].setText(rauthor.get(rIndex));
                    rimgv[i].setImage(rimgs.get(rIndex));
                    rIndex++;
                    i++;
                }
                //System.out.println(rIndex);
                //making arrow disabled if no more books
                if(rIndex>=rname.size())
                {
                    rstckRArrow.setVisible(false);
                }
            }
        }
    }

    public void lArrowClicked(MouseEvent event)
    {
        if(event.getSource()==tLArrow){
            //finding out the (index+1) value of the rightmost box
            if(tIndex%3==0)
            {
                tIndex-=3;
            }
            else{
                tIndex -= tIndex%3;
            }



            //populate these boxes
            int boxIn = 0;
            while(boxIn<=2)
            {
                tlabel[boxIn].setText(tname.get(tIndex-(3-boxIn)));
                tAuthorLabel[boxIn].setText(tauthor.get(tIndex-(3-boxIn)));
                timgv[boxIn].setImage(timgs.get(tIndex-(3-boxIn)));
                tVbox[boxIn].setVisible(true);
                boxIn++;
            }
            //System.out.println(tIndex);

            //if leftmost box contain last book then left arrow will be invisible
            if((tIndex-3)<=0)
            {
                tstckLArrow.setVisible(false);
            }
            tstckRArrow.setVisible(true);
        }

        if(event.getSource()==rLArrow){
            //finding out the (index+1) value of the rightmost box
            if(rIndex%3==0)
            {
                rIndex-=3;
            }
            else{
                rIndex -= rIndex%3;
            }

            //populate these boxes
            int boxIn = 0;
            while(boxIn<=2)
            {
                rlabel[boxIn].setText(rname.get(rIndex-(3-boxIn)));
                rAuthorLabel[boxIn].setText(rauthor.get(rIndex-(3-boxIn)));
                rimgv[boxIn].setImage(rimgs.get(rIndex-(3-boxIn)));
                rVbox[boxIn].setVisible(true);
                boxIn++;
            }

            //System.out.println(rIndex);
            //if leftmost box contain last book then left arrow will be invisible
            if((rIndex-3)<=0)
            {
                rstckLArrow.setVisible(false);
            }
            rstckRArrow.setVisible(true);
        }
    }



    public void onHoverArrow(MouseEvent event)
    {
        for(int i=0; i<arrows.length; i++)
        {
            if(arrows[i].isHover())
            {
                if(i==1||i==3)
                {
                    arrows[i].setImage(new Image(new File(path+"rightAClicked.png").toURI().toString()));
                }
                else
                {
                    arrows[i].setImage(new Image(new File(path+"leftAClicked.png").toURI().toString()));
                }
            }

        }
    }

    public void endHoverArrow(MouseEvent event)
    {
        for(int i=0; i<arrows.length; i++)
        {
            if(!arrows[i].isHover())
            {
                if(i==1||i==3)
                {
                    arrows[i].setImage(new Image(new File(path+"rightArrow.png").toURI().toString()));
                }
                else
                {
                    arrows[i].setImage(new Image(new File(path+"leftArrow.png").toURI().toString()));
                }
            }

        }
    }

    public void btnPressed(ActionEvent e)
    {
        Windows w = new Windows(rentOutBtn, 6);
    }


    public void onHoverButton(MouseEvent event)
    {

        rentOutBtn.setStyle("-fx-background-color:#92a2b9");

    }


    public void endHoverButton(MouseEvent event)
    {

        rentOutBtn.setStyle("-fx-background-color:#44546a");

    }

    public void bookPageClicked(MouseEvent event)
    {
        for(int i=0; i<timgv.length; i++) {
            if (event.getSource() == timgv[i]) {
                Windows w = new Windows(timgv[i], "../fxml/bookDetailsPage.fxml", tlabel[i].getText());
            }
        }

        for(int i=0; i<rimgv.length; i++) {
            if (event.getSource() == rimgv[i]) {
                Windows w = new Windows(rimgv[i], "../fxml/bookDetailsPage.fxml",rlabel[i].getText());
            }
        }
    }


}
