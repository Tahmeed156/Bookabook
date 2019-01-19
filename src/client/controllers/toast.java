package client.controllers;


import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Timer;

public class toast {
    private static Label lb = new Label();
    private static int i = 0;

    public static void set(String a)
    {
        lb.setAlignment(Pos.BOTTOM_CENTER);
        lb.setPadding(new Insets(10,10,10,10));
        lb.setStyle("-fx-background-color:#000000; -fx-background-radius:10; -fx-text-fill:#ffffff;" +
                "-fx-border-color:#ffffff; -fx-border-radius:10");
        lb.setLayoutX(500);
        lb.setLayoutY(700);
        text(a);

    }

    public static void setColor(String colorBG)
    {
        lb.setStyle("-fx-background-color:"+colorBG+";  -fx-background-radius:10; -fx-text-fill:#ffffff;" +
                "-fx-border-color:#ffffff; -fx-border-radius:10");
    }

    public static void text(String a)
    {
        lb.setText(a);
        i = 0;


        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event ->{
            //System.out.println(i);
            if (i<=3) {
                pause.play();
            } else {
                lb.setText("");
                lb.setStyle("");
            }
            i++;
        });
        pause.play();
    }

    public static Label get()
    {
        return lb;
    }
}
