package bookabook.client.controllers;
import bookabook.client.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class messenger {
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


    @FXML private TextField chat;
    @FXML private VBox message;
    @FXML private ImageView upArrow;
    @FXML private ImageView downArrow;
    @FXML private Button send;
    @FXML private VBox onlineUsers;
    @FXML private ImageView upArrow1;
    @FXML private ImageView downArrow1;


    // private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\client\\Pictures\\";


    List<String> user = new ArrayList<>();
//    Arrays.asList("Hello","How are you?","I am fine thank you",
//            "For future use lets do this dont you think we should\n lets do aaaaaaaaaaaaaa" +
//                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
//                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaathis.","Hello","How are you?","I am fine thank you",
//            "For future use lets do this dont you think we should lets do this.","Hello","How are you?","I am fine thank you",
//            "For future use lets do this dont you think we should lets do this.","Hello","How are you?","I am fine thank you",
//            "For future use lets do this dont you think we should lets do this.","a"));

    List<String> who = new ArrayList<>();
    // Arrays.asList(0,0,1,1,0,1,1,1,0,0,0,1,0,0,1,1,0));

    List<String> time = new ArrayList<>();
//        Arrays.asList("1:30","2:15","2:16","2:17","2:18","2:30",
//            "2:45","3:30","5:50","5:00","6:25","6:45","6:55","6:55","7:00","7:01",
//            "7:02"));

    List<String> online = new ArrayList<>();
//    Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
//           "p","q","r","s","t","u","v","w","x","y","z"));


    int index;
    int index1;
    String lblStyle;

    public void initialize() throws IOException, ClassNotFoundException, JSONException {
        lbl = new Label[]{dashBLbl, searchLbl, messagesLbl, helpLbl, profileLbl, logoutLbl};
        stck = new StackPane[]{dashBStk,searchStk,messagesStk,helpStk,profileStk,logoutStk};
        parent.getChildren().add(toast.get());

        // load message from server
        JSONArray response_arr = new JSONArray(Main.connection.getMessage());
        for (int i=0; i<response_arr.length(); i++) {
            JSONObject messages = response_arr.getJSONObject(i);
            //add  message
            user.add(messages.getString("body"));
            who.add(messages.getString("username"));
            time.add(messages.getString("timestamp"));
        }

        int start = 0;
        if(user.size()>7) { start = user.size() - 7; }

        messageDisplay(start,user.size());
        if(start>0){ upArrow.setVisible(true);}
        index = start;

        JSONArray response_arr1 = new JSONArray(Main.connection.getOnline(dashboard.userName));
        for (int i=0; i<response_arr1.length(); i++) {
            online.add(response_arr1.get(i).toString());
        }


        index1 = online.size()-1;
        lblStyle = "-fx-background-color:#ffffff; -fx-pref-width:100";
        index1 = helper.initiate(online, onlineUsers, 10,100, lblStyle,
                downArrow1, index1, 10, true, 1);


    }

    public void onHoverBox(MouseEvent event)
    {


        for(int i=0; i<stck.length; i++)
        {
            if(stck[i].isHover() && i!=2)
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
            if(!stck[i].isHover() && i!=2)
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
            if(stck[i].isPressed() && i!=2)
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
        else if (event.getSource()==upArrow1)
        {
            Image img = new Image(new File(path+"upAClicked.png").toURI().toString());
            upArrow1.setImage(img);
        }
        else if (event.getSource()==downArrow1)
        {
            Image img = new Image(new File(path+"downAClicked.png").toURI().toString());
            downArrow1.setImage(img);
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
        else if (event.getSource()==upArrow1)
        {
            Image img = new Image(new File(path+"upArrow.png").toURI().toString());
            upArrow1.setImage(img);
        }
        else if (event.getSource()==downArrow1)
        {
            Image img = new Image(new File(path+"downArrow.png").toURI().toString());
            downArrow1.setImage(img);
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
        int i = postindex - 7;
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

    public void upArrow1Clicked(MouseEvent event)
    {
        index1 = helper.up_arrow_clicked(online, onlineUsers, 10,100, lblStyle,
                downArrow1, upArrow1, index1, 10, true, 1);
    }

    public void downArrow1Clicked(MouseEvent event)
    {
        index1 = helper.down_arrow_clicked(online, onlineUsers, 10,100, lblStyle,
                downArrow1, upArrow1, index1, 10, true,1);

    }

    public void downArrowClicked(MouseEvent event)
    {


        if (index==0)
        {
            index = user.size()%7;
            if(user.size()%7==0)
            {
                index = 7;
            }
        }
        else
        {
            index+=7;
        }

        messageDisplay(index, index+7);


        //if leftmost box contain last book then left arrow will be invisible
        if((index+7)==user.size())
        {
            downArrow.setVisible(false);
        }
        upArrow.setVisible(true);
        //System.out.println(index);
    }

    public void onHoverBtn(MouseEvent e)
    {
        send.setStyle("-fx-border-color: #ffffff; -fx-border-radius:5; -fx-border-width:2;" +
                " -fx-background-color: #0275d8");
    }

    public void endHoverBtn(MouseEvent e)
    {
        send.setStyle("-fx-border-color: #ffffff; -fx-border-radius:5; -fx-border-width:2;" +
                " -fx-background-color: #3b3838");
    }
    public void sendBtn(ActionEvent event) throws IOException, ClassNotFoundException {
        // todo NHS: send to database too
        sendMessage();
    }

    public void sendKey(KeyEvent e) throws IOException, ClassNotFoundException {
        if(e.getCode().equals(KeyCode.ENTER))
        {
            // todo NHS: send to database too
            sendMessage();
        }
    }

    public void sendMessage() throws IOException, ClassNotFoundException {
        user.add(chat.getText());
        who.add(dashboard.user);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        time.add(dateFormat.format(now));

        index+=1;
        addMessage();
        chat.clear();
    }

    public void addOnlineUser(){
        // TODO: add online users from server

        index1 = helper.initiate(online, onlineUsers, 10,100, lblStyle,
                downArrow1, index1, 10, true,1 );

    }

    public void addMessage() throws IOException, ClassNotFoundException {
        // todo NHS: add messages from server
        int start = 0;
        if(user.size()>7) { start = user.size() - 7; }

        messageDisplay(start,user.size());
        if(start>0){ upArrow.setVisible(true);}
        index = start;


        // adding messages to server
        String s = Main.connection.addMessage(
                Integer.parseInt(dashboard.userId),
                dashboard.user,
                "text",
                chat.getText()
        );
        //System.out.println("MESSAGE SENT");
        toast.set("MESSAGE SENT","#5cb85c");
    }



    public void messageDisplay(int initial, int limit)
    {
        message.getChildren().clear();
        for(int i=initial; i<limit ; i++)
        {
            HBox hb = new HBox();
            VBox vb = new VBox();
            Label timeStamp = new Label(time.get(i));
            timeStamp.setStyle("-fx-text-fill:#ffffff; -fx-font-size:10");
            Label lb = new Label(user.get(i));
            lb.setPadding(new Insets(5,5,5,5));
            //todo NHS && TMD text doesn't get wrapped if bottom padding is set
            lb.setWrapText(true);
            lb.setMaxWidth(400);
            vb.setPadding(new Insets(10,0,0,0));
            if(who.get(i).equals(dashboard.user)) {
                vb.setAlignment(Pos.CENTER_RIGHT);
                hb.setAlignment(Pos.CENTER_RIGHT);
                lb.setAlignment(Pos.CENTER_RIGHT);
                lb.setStyle("-fx-background-color:#767171; -fx-background-radius:8; -fx-text-fill:#ffffff");
                vb.getChildren().addAll(lb,timeStamp);}
            else{
                Label username = new Label(who.get(i));
                username.setStyle("-fx-text-fill:#ffffff");
                vb.setAlignment(Pos.CENTER_LEFT);
                hb.setAlignment(Pos.CENTER_LEFT);
                lb.setAlignment(Pos.CENTER_LEFT);
                lb.setStyle("-fx-background-color:#ffffff; -fx-background-radius:8");
                vb.getChildren().addAll(username, lb, timeStamp);
            }
            hb.getChildren().add(vb);
            Region rg = new Region();
            rg.setPrefHeight(5);
            rg.setPrefWidth(680);
            message.getChildren().addAll(hb,rg);
        }


    }

}
