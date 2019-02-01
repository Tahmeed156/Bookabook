package bookabook.client.controllers;

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
    @FXML private Pane parent;
    @FXML
    private VBox left;
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
    @FXML private Circle imgCircle;
    @FXML private VBox upperRightVbox;

    //middle part
    @FXML private Circle bigImgCircle;
    @FXML private Label bigName;
    @FXML private Label work;
    @FXML private Label birthDate;
    @FXML private Label email;
    @FXML private Label contact;

    @FXML private Button rentBookBtn;
    @FXML private Button editProfileBtn;
    @FXML private Button rentOutBtn;
    @FXML private Button rentedBtn;
    Button[] btn;



    public Integer tIndex = 0;
    public Integer bIndex = 0;

    @FXML private VBox tVbox1;
    @FXML private VBox tVbox2;
    @FXML private VBox tVbox3;
    @FXML private VBox bVbox1;
    @FXML private VBox bVbox2;
    @FXML private VBox bVbox3;
    VBox[] tVbox;
    VBox[] bVbox;


    @FXML private Label tBookName1;
    @FXML private Label tBookName2;
    @FXML private Label tBookName3;
    @FXML private Label bBookName1;
    @FXML private Label bBookName2;
    @FXML private Label bBookName3;
    Label[] tlabel;
    Label[] blabel;


    @FXML private Label tBookAuthor1;
    @FXML private Label tBookAuthor2;
    @FXML private Label tBookAuthor3;
    @FXML private Label bBookAuthor1;
    @FXML private Label bBookAuthor2;
    @FXML private Label bBookAuthor3;
    Label[] tAuthorLabel;
    Label[] bAuthorLabel;




    @FXML private ImageView tBookImage1;
    @FXML private ImageView tBookImage2;
    @FXML private ImageView tBookImage3;
    @FXML private ImageView bBookImage1;
    @FXML private ImageView bBookImage2;
    @FXML private ImageView bBookImage3;
    ImageView[] timgv;
    ImageView[] bimgv;


    @FXML private StackPane tstckLArrow;
    @FXML private StackPane tstckRArrow;
    @FXML private StackPane bstckLArrow;
    @FXML private StackPane bstckRArrow;


    @FXML private ImageView tLArrow;
    @FXML private ImageView tRArrow;
    @FXML private ImageView bLArrow;
    @FXML private ImageView bRArrow;
    ImageView[] arrows;



    //list for rented out books
    List<String> tname = new ArrayList<>(Arrays.asList("Harry Potter","Percy Jackson","Animal Farm","Foundation",
            "The Houdini","ABC","New Ton"));
    List<String> tauthor = new ArrayList<>(Arrays.asList("JK ROWLING","EE","George Orwell","Issac Assimov","LLALAL",
            "Mary Houdini","The Great Em"));
    Image[] timgs = new Image[50];

    //list for rented books
    List<String> bname = new ArrayList<>(Arrays.asList("Game of Thrones","1984","Animal Farm","Percy Jackson"));
    List<String> bauthor = new ArrayList<>(Arrays.asList("GRR Martin","George Orwell","George Orwell","Rick"));
    Image[] bimgs = new Image[50];

    // private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";


    public void initialize()
    {
        parent.getChildren().add(toast.get());
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk,searchStk,messagesStk,helpStk,profileStk,logoutStk};

        tlabel = new Label[]{tBookName1,tBookName2,tBookName3};
        blabel = new Label[]{bBookName1,bBookName2,bBookName3};
        tAuthorLabel = new Label[]{tBookAuthor1,tBookAuthor2,tBookAuthor3};
        bAuthorLabel = new Label[]{bBookAuthor1,bBookAuthor2,bBookAuthor3};
        timgv = new ImageView[]{tBookImage1,tBookImage2,tBookImage3};
        bimgv = new ImageView[]{bBookImage1,bBookImage2,bBookImage3};
        tVbox = new VBox[]{tVbox1,tVbox2,tVbox3};
        bVbox = new VBox[]{bVbox1,bVbox2,bVbox3};
        arrows = new ImageView[]{tLArrow,tRArrow,bLArrow,bRArrow};
        btn = new Button[]{rentBookBtn, editProfileBtn, rentOutBtn, rentedBtn};

        //tbook1
        timgs[0] = new Image(new File(path+"hp.png").toURI().toString());

        //tbook2
        timgs[1] = new Image(new File(path+"pj.png").toURI().toString());

        //tbook3

        timgs[2] = new Image( new File(path+"animalFarm.png").toURI().toString());

        //tbook4
        timgs[3] = new Image( new File(path+"foundation.png").toURI().toString());

        //tbook5
        timgs[4] = new Image(new File(path+"animalFarm.png").toURI().toString());

        //tbook6
        timgs[5] = new Image(new File(path+"1984.png").toURI().toString());

        //tbook7
        timgs[6] = new Image(new File(path+"hp.png").toURI().toString());

        //rbook1
        bimgs[0] = new Image(new File(path+"book.png").toURI().toString());


        //rbook2
        bimgs[1] = new Image(new File(path+"1984.png").toURI().toString());

        //rbook3
        bimgs[2] = new Image(new File(path+"animalFarm.png").toURI().toString());

        //tbook4
        bimgs[3] = new Image(new File(path+"foundation.png").toURI().toString());


        //profilePicture
        File file4 = new File(path+"woman.png");
        Image imgperson = new Image(file4.toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));

        Image bigimgperson = new Image(file4.toURI().toString());
        bigImgCircle.setFill(new ImagePattern(bigimgperson));




        //upperRightLabels
        Label nameUser = new Label("Ayan Antik Khan ");
        nameUser.setStyle("-fx-font-weight:bold");

        Integer daysLeft = 2;
        Integer rentedBooks = 3;
        Integer deposit = 2000;

        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Next return: "+daysLeft+" days"),
                new Label("Rented: "+rentedBooks+" Books"),
                new Label("Money deposited:"),
                new Label("Tk "+deposit));



        bigName.setText("Ayan Antik Khan");
        work.setText("Intern at the king's guard");
        birthDate.setText("11th March 2018");
        email.setText("jmike@gmail.com");
        contact.setText("54654685");


        //populating top (Rented out books)
        for(tIndex = 0 ; tIndex<tname.size() && tIndex<=2; tIndex++)
        {
            tVbox[tIndex].setVisible(true);
            tlabel[tIndex].setText(tname.get(tIndex));
            tAuthorLabel[tIndex].setText(tauthor.get(tIndex));
            timgv[tIndex].setImage(timgs[tIndex]);
        }
        //Here tIndex value increments +1 at the end. So value is 3 not 2. Can be used as a normal value

        //make right arrow visible if more books available
        if(tname.size()>3)
        {
            tstckRArrow.setVisible(true);
        }

        //populating bottom (rented books)
        for(bIndex = 0 ; bIndex<bname.size() && bIndex<3; bIndex++)
        {
            bVbox[bIndex].setVisible(true);
            blabel[bIndex].setText(bname.get(bIndex));
            bAuthorLabel[bIndex].setText(bauthor.get(bIndex));
            bimgv[bIndex].setImage(bimgs[bIndex]);

        }

        //make right arrow visible if more books available
        if(bname.size()>3)
        {
            bstckRArrow.setVisible(true);
        }


    }

    public void onHoverBox(MouseEvent event)
    {


        for(int i=0; i<stck.length; i++)
        {
            if(stck[i].isHover() && i!=4)
            {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59,56,56));
            }
        }

    }

    public void endHoverBox(MouseEvent event)
    {
        for(int i=0; i<stck.length; i++)
        {
            if(!stck[i].isHover() && i!=4)
            {
                stck[i].setStyle("-fx-background-color:#3b3838;");
                lbl[i].setTextFill(Color.rgb(217,217,217));
            }
        }

    }

    public void pressed(MouseEvent event)
    {
        for(int i=0; i<stck.length; i++)
        {
            if(stck[i].isPressed() && i!=4)
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
                    timgv[i].setImage(timgs[tIndex]);
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


        if(event.getSource()==bRArrow)
        {
            if(bIndex<bname.size())
            {
                //set left arrow visible
                bstckLArrow.setVisible(true);
                //making invisible boxes and images
                for(int boxIn = 2; boxIn>=0; boxIn--)
                {
                    bVbox[boxIn].setVisible(false);
                    bimgv[boxIn].setImage(null);
                }
                //populate remaining boxes
                int i = 0;
                while(bIndex<bname.size() && i<3)
                {
                    bVbox[i].setVisible(true);
                    blabel[i].setText(bname.get(bIndex));
                    bAuthorLabel[i].setText(bauthor.get(bIndex));
                    bimgv[i].setImage(bimgs[bIndex]);
                    bIndex++;
                    i++;
                }
                //System.out.println(rIndex);
                //making arrow disabled if no more books
                if(bIndex>=bname.size())
                {
                    bstckRArrow.setVisible(false);
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
                timgv[boxIn].setImage(timgs[tIndex-(3-boxIn)]);
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

        if(event.getSource()==bLArrow){
            //finding out the (index+1) value of the rightmost box
            if(bIndex%3==0)
            {
                bIndex-=3;
            }
            else{
                bIndex -= bIndex%3;
            }

            //populate these boxes
            int boxIn = 0;
            while(boxIn<=2)
            {
                blabel[boxIn].setText(bname.get(bIndex-(3-boxIn)));
                bimgv[boxIn].setImage(bimgs[bIndex-(3-boxIn)]);
                bimgv[boxIn].setImage(bimgs[bIndex-(3-boxIn)]);
                bVbox[boxIn].setVisible(true);
                boxIn++;
            }

            //System.out.println(rIndex);
            //if leftmost box contain last book then left arrow will be invisible
            if((bIndex-3)<=0)
            {
                bstckLArrow.setVisible(false);
            }
            bstckRArrow.setVisible(true);
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




    public void onHoverButton(MouseEvent event)
    {

        for(int i=0; i<4; i++)
        {
            if(event.getSource()==btn[i]) {
                if (i == 0 || i == 1) {
                    btn[i].setStyle("-fx-background-color:#92a2b9");
                } else {
                    btn[i].setStyle("-fx-background-color:#92e25d");
                }
            }
        }

    }


    public void endHoverButton(MouseEvent event)
    {

        for(int i=0; i<4; i++)
        {
            if(event.getSource()==btn[i]) {
                if (i == 0 || i == 1) {
                    btn[i].setStyle("-fx-background-color:#44546a");
                } else {
                    btn[i].setStyle("-fx-background-color:#70ad47");
                }
            }
        }

    }


    public void editProClicked(MouseEvent e)
    {
        Windows w = new Windows(editProfileBtn, 7);
    }
    public void rentABookClicked(MouseEvent e) {Windows w = new Windows(rentBookBtn, 6);}
    public void rentedBooksClicked(MouseEvent e) { Windows w = new Windows(rentedBtn, 8); }
    public void rentedOutBooksClicked(MouseEvent e) { Windows w = new Windows(rentOutBtn, 9); }


}
