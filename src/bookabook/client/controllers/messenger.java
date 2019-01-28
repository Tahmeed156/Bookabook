package bookabook.client.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class messenger {
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


    @FXML private TextField chat;
    @FXML private VBox message;
    @FXML private ImageView upArrow;
    @FXML private ImageView downArrow;
    @FXML private Button send;


    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    // private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";


    List<String> user = new ArrayList<>(Arrays.asList("Hello","How are you?","I am fine thank you",
            "For future use lets do this dont you think we should lets do aaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaathis.","Hello","How are you?","I am fine thank you",
            "For future use lets do this dont you think we should lets do this.","Hello","How are you?","I am fine thank you",
            "For future use lets do this dont you think we should lets do this.","Hello","How are you?","I am fine thank you",
            "For future use lets do this dont you think we should lets do this.","a"));

    List<Integer> who = new ArrayList<>(Arrays.asList(0,0,1,1,0,1,1,1,0,0,0,1,0,0,1,1,0));
    int index;

    public void initialize()
    {
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk,searchStk,messagesStk,helpStk,profileStk,logoutStk};




        int start = 0;
        if(user.size()>8)
        {
            start = user.size() - 8;
        }

        messageDisplay(start,user.size());
        if(start>0){ upArrow.setVisible(true);}
        index = start;



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

    public void onArrowHover(MouseEvent event)
    {
        if (event.getSource()==upArrow)
        {
            Image img = new Image(new File(path+"upAClicked.png").toURI().toString());
            upArrow.setImage(img);
        }
        else if (event.getSource()==downArrow)
        {
            Image img = new Image(new File(path+"downAClicked.png").toURI().toString());
            downArrow.setImage(img);
        }
    }

    public void endArrowHover(MouseEvent event)
    {
        if (event.getSource()==upArrow)
        {
            Image img = new Image(new File(path+"upArrow.png").toURI().toString());
            upArrow.setImage(img);
        }
        else if (event.getSource()==downArrow)
        {
            Image img = new Image(new File(path+"downArrow.png").toURI().toString());
            downArrow.setImage(img);
        }
    }


    public void upArrowClicked(MouseEvent event)
    {
        //if up arrow clicked and more books available

        //set left arrow visible
        downArrow.setVisible(true);

        //delete all children
        message.getChildren().clear();


        int postindex = index;
        int i = postindex - 8;
        if (i<=0)
        {
            i = 0;
        }

        index = i;
        messageDisplay(i,postindex);


        //making arrow disabled if no more books
        //System.out.println(tIndex);
        if (index == 0) {
            upArrow.setVisible(false);
        }

        //System.out.println(index);

    }


    public void downArrowClicked(MouseEvent event)
    {


        if (index==0)
        {
            index = user.size()%8;
            if(user.size()%8==0)
            {
                index = 8;
            }
        }
        else
        {
            index+=8;
        }

        messageDisplay(index, index+8);


        //if leftmost box contain last book then left arrow will be invisible
        if((index+8)==user.size())
        {
            downArrow.setVisible(false);
        }
        upArrow.setVisible(true);
        //System.out.println(index);
    }

    public void sendBtn(MouseEvent event){
        user.add(chat.getText());
        who.add(1);
        chat.clear();

        index+=1;
        messageDisplay(index, user.size());

    }



    public void messageDisplay(int initial, int limit)
    {
        message.getChildren().clear();
        for(int i=initial; i<limit ; i++)
        {
            HBox hb = new HBox();
            Label lb = new Label(user.get(i));
            lb.setPadding(new Insets(10,10,10,10));
            lb.setWrapText(true);
            lb.setMaxWidth(400);
            if(who.get(i) == 1) { hb.setAlignment(Pos.CENTER_RIGHT);
                lb.setStyle("-fx-background-color:#767171; -fx-background-radius:8; -fx-text-fill:#ffffff");}
            else{
                hb.setAlignment(Pos.CENTER_LEFT);
                lb.setStyle("-fx-background-color:#ffffff; -fx-background-radius:8");
            }
            hb.getChildren().add(lb);
            Region rg = new Region();
            rg.setPrefHeight(10);
            rg.setPrefWidth(680);
            message.getChildren().addAll(hb,rg);
        }


    }
}
