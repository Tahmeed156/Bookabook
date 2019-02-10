package bookabook.client.controllers;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.Timer;

public class toast {
    private static Label lb = new Label();
    private static int i = 0;

    public static void set(String a, String colorBG)
    {
        lb.setAlignment(Pos.BOTTOM_CENTER);
        lb.setPadding(new Insets(10,10,10,10));
        lb.setStyle("-fx-background-color:"+colorBG+"; -fx-background-radius:10; -fx-text-fill:#ffffff;" +
                "-fx-border-color:#ffffff; -fx-border-radius:10");
        lb.setLayoutX(500);
        lb.setLayoutY(700);
        text(a);
    }


    public static void text(String a)
    {
        lb.setText(a);
        i = 0;


        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event ->{
            //System.out.println("IN TOAST" + i);
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
