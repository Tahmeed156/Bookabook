package bookabook.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class profileRentedBooksPage{
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
    @FXML private Circle imgCircle;
    @FXML private VBox upperRightVbox;





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

    @FXML private Label labelBooks;
    @FXML private Button profileBtn;

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    //private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";

    //list for searched items
    List<String> name = new ArrayList<>(Arrays.asList("Harry Potter","Percy Jackson","Animal Farm","Foundation",
            "The Houdini","ABC","New Ton","ANI","Hey you","Maybe","Looking for mid","Hullaa","DragonSlayer"));
    List<String> author = new ArrayList<>(Arrays.asList("JK ROWLING","EE","George Orwell","Issac Assimov","LLALAL",
            "Mary Houdini","The Great Em","Heidi","Tom Cruise","Tom Shelby","Idea Partner","Lookie here","Mr. A"));
    List<Integer> rent = new ArrayList<>(Arrays.asList(10,20,30,10,50,10,10,20,30,40,10,20,30));
   //List<Integer> depositArr = new ArrayList<>(Arrays.asList(201,101,220,600,300,200,100,500,300,200,100,150,300));
    Image[] imgs = new Image[50];

    public void initialize()
    {
        parent.getChildren().add(toast.get());
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk, searchStk, messagesStk,helpStk,profileStk,logoutStk};

        bookLabel = new Label[]{BookName1,BookName2,BookName3,BookName4,BookName5,BookName6};
        authorLabel = new Label[]{BookAuthor1,BookAuthor2,BookAuthor3, BookAuthor4, BookAuthor5,BookAuthor6};
        rentLabel = new Label[]{rent1,rent2,rent3,rent4,rent5,rent6};
        imgv = new ImageView[]{img1,img2,img3,img4,img5,img6};
        Vbox1 = new VBox[]{Vbox11,Vbox21,Vbox31,Vbox41,Vbox51,Vbox61};
        Vbox2 = new VBox[]{Vbox12,Vbox22,Vbox32,Vbox42,Vbox52,Vbox62};


        for(int i=1; i<=13; i++)
        {
            imgs[i-1] = new Image(new File(path+i+".png").toURI().toString());
        }




        //search Button
        ImageView imgBtn = new ImageView(new Image(new File(path+"searchLogo.jpg").toURI().toString()));
        imgBtn.setFitHeight(25);
        imgBtn.setFitWidth(25);

        //profilePicture
        Image imgperson = new Image(new File(path+"woman.png").toURI().toString());
        imgCircle.setFill(new ImagePattern(imgperson));




        //upperRightLabels
        Label nameUser = new Label("Ayan Antik Khan ");
        nameUser.setStyle("-fx-font-weight:bold");

        Integer daysLeft = dashboard.daysLeft;
        Integer rentedBooks = dashboard.rentedBooks;
        Integer deposit = dashboard.deposit;

        upperRightVbox.getChildren().addAll(nameUser,
                new Label("Next return: "+daysLeft+" days"),
                new Label("Rented: "+rentedBooks+" Books"),
                new Label("Money deposited:"),
                new Label("Tk "+deposit));


        //populating the box
        for(index = 0 ; index<name.size() && index<6; index++)
        {

            Vbox1[index].setVisible(true);
            Vbox2[index].setVisible(true);
            bookLabel[index].setText(name.get(index));
            authorLabel[index].setText(author.get(index));
            imgv[index].setImage(imgs[index]);
            rentLabel[index].setText(Integer.toString(rent.get(index)));
        }
        //Here Index value increments +1 at the end. So value is 6 not 5. Can be used as a normal value

        //make right arrow visible if more books available
        if(name.size()>6)
        {
            stckRArrow.setVisible(true);
        }


    }

    public void onHoverBox(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (i == 4) {
                continue;
            }
            if (stck[i].isHover()) {
                stck[i].setStyle("-fx-background-color:#b9b9b9;");
                lbl[i].setTextFill(Color.rgb(59, 56, 56));
            }
        }
    }

    public void endHoverBox(MouseEvent event) {
        for (int i = 0; i < stck.length; i++) {
            if (i == 4) {
                continue;
            }
            if (!stck[i].isHover()) {
                stck[i].setStyle("-fx-background-color:#3b3838;");
                lbl[i].setTextFill(Color.rgb(217, 217, 217));
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



    public void onHoverButton(MouseEvent event)
    {

        if(event.getSource()==profileBtn) {
            profileBtn.setStyle("-fx-background-color:#92a2b9");
        }

    }


    public void endHoverButton(MouseEvent event)
    {

        if(event.getSource()==profileBtn) {
                profileBtn.setStyle("-fx-background-color:#44546a");
            }

    }




    public void rArrowClicked(MouseEvent event)
    {
        //if right arrow clicked and more books available
        if(index<name.size()) {
            //set left arrow visible
            stckLArrow.setVisible(true);
            //making boxes and images invisible
            for (int boxIn = 5; boxIn >= 0; boxIn--) {
                Vbox1[boxIn].setVisible(false);
                Vbox2[boxIn].setVisible(false);
                imgv[boxIn].setImage(null);
            }

            //populating remaining boxes
            int i = 0;
            while (index < name.size() && i < 6) {
                Vbox1[i].setVisible(true);
                Vbox2[i].setVisible(true);
                bookLabel[i].setText(name.get(index));
                authorLabel[i].setText(author.get(index));
                rentLabel[i].setText(Integer.toString(rent.get(index)));
                imgv[i].setImage(imgs[index]);
                index++;
                i++;
            }

            //making arrow disabled if no more books
            //System.out.println(tIndex);
            if (index >= name.size()) {
                stckRArrow.setVisible(false);
            }
        }
    }

    public void lArrowClicked(MouseEvent event)
    {

        //finding out the (index+1) value of the rightmost box
        if(index%6==0) {
            index-=6;
        }
        else{
            index -= index%6;
        }

        //populate these boxes
        int boxIn = 0;
        while(boxIn<=5)
        {
            bookLabel[boxIn].setText(name.get(index-(6-boxIn)));
            authorLabel[boxIn].setText(author.get(index-(6-boxIn)));
            rentLabel[boxIn].setText(Integer.toString(rent.get(index-(6-boxIn))));
            imgv[boxIn].setImage(imgs[index-(6-boxIn)]);
            Vbox1[boxIn].setVisible(true);
            Vbox2[boxIn].setVisible(true);
            boxIn++;
        }
        //System.out.println(tIndex);

        //if leftmost box contain last book then left arrow will be invisible
        if((index-6)<=0)
        {
            stckLArrow.setVisible(false);
        }
        stckRArrow.setVisible(true);
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

    public void profileBtnPressed(MouseEvent e){Windows w = new Windows(profileBtn, 4);}

}
