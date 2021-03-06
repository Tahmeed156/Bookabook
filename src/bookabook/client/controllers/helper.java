package bookabook.client.controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class helper {

    //functions for already available invisible boxes
    //shows from index 0 to final
    public static int initiate(List<String> l1, List<String> l2, List<Image> img,
                                StackPane rArrow, VBox[] vb1, Label[] lb1,
                                Label[] lb2, ImageView[] imgv, int index, int limit)
    {

        int start = sortBox(l1, l2, img, vb1, lb1, lb2, imgv, index, limit);
        //make right arrow visible if more books available
        //System.out.println("start plus index is "+(start+index));
        if ((start+index) < l1.size()) {
            rArrow.setVisible(true);
        }
        return index;
    }


    public static int initiate(List<String> l1, List<String> l2, List<Integer> l3, List<Integer> l4,
                               List<Image> img, StackPane rArrow, VBox[] vb1, VBox[] vb2, Label[] lb1,
                               Label[] lb2, Label[] lb3, Label[] lb4, ImageView[] imgv, int index, int limit)
    {

        int start = sortBox(l1, l2, l3, l4, img, vb1, vb2, lb1, lb2, lb3, lb4, imgv, index, limit);
        //make right arrow visible if more books available
        //System.out.println("start plus index is "+(start+index));
        if ((start+index) < l1.size()) {
            rArrow.setVisible(true);
        }
        return index;
    }


    public static int right_arrow_clicked(List<String> l1, List<String> l2, List<Image> img,
                                           StackPane rArrow, StackPane lArrow, VBox[] vb1, Label[] lb1,
                                           Label[] lb2, ImageView[] imgv, int index, int limit)
    {


        index += limit;
        lArrow.setVisible(true);
        //making boxes and images invisible
        for(int boxIn = 0; boxIn<limit; boxIn++)
        {
            vb1[boxIn].setVisible(false);
            imgv[boxIn].setImage(null);
        }

        //populating remaining boxes
        int start = sortBox(l1, l2, img, vb1, lb1, lb2, imgv, index, limit);

        //making arrow disabled if no more books
        //System.out.println(tIndex);
        if((index+start)>=l1.size())
        {
            rArrow.setVisible(false);
        }

        return index;
    }

    public static int right_arrow_clicked(List<String> l1, List<String> l2, List<Integer> l3, List<Integer> l4,
                                          List<Image> img, StackPane rArrow, StackPane lArrow, VBox[] vb1,
                                          VBox[] vb2, Label[] lb1, Label[] lb2, Label[] lb3, Label[] lb4,
                                          ImageView[] imgv, int index, int limit)
    {


        index += limit;
        lArrow.setVisible(true);
        //making boxes and images invisible
        for(int boxIn = 0; boxIn<limit; boxIn++)
        {
            vb1[boxIn].setVisible(false);
            vb2[boxIn].setVisible(false);
            imgv[boxIn].setImage(null);
        }

        //populating remaining boxes
        int start = sortBox(l1, l2, l3, l4, img, vb1, vb2, lb1, lb2, lb3, lb4, imgv, index, limit);

        //making arrow disabled if no more books
        //System.out.println(tIndex);
        if((index+start)>=l1.size())
        {
            rArrow.setVisible(false);
        }

        return index;
    }

    public static int left_arrow_clicked(List<String> l1, List<String> l2, List<Image> img,
                                          StackPane rArrow, StackPane lArrow, VBox[] vb1, Label[] lb1,
                                          Label[] lb2, ImageView[] imgv, int index, int limit)
    {
        index -= limit;


        //populate these boxes
        int start = sortBox(l1, l2, img, vb1, lb1, lb2, imgv, index, limit);

        //if leftmost box contain last book then left arrow will be invisible
        if(index==0)
        {
            lArrow.setVisible(false);
        }
        rArrow.setVisible(true);

        return index;
    }

    public static int left_arrow_clicked(List<String> l1, List<String> l2, List<Integer> l3, List<Integer> l4,
                                         List<Image> img, StackPane rArrow, StackPane lArrow, VBox[] vb1,
                                         VBox[] vb2, Label[] lb1, Label[] lb2, Label[] lb3, Label[] lb4,
                                         ImageView[] imgv, int index, int limit)
    {
        index -= limit;


        //populate these boxes
        int start = sortBox(l1, l2, l3, l4, img, vb1, vb2, lb1, lb2, lb3, lb4, imgv, index, limit);

        //if leftmost box contain last book then left arrow will be invisible
        if(index==0)
        {
            lArrow.setVisible(false);
        }
        rArrow.setVisible(true);

        return index;
    }


    public static int sortBox(List<String> l1, List<String> l2, List<Image> img, VBox[] vb1, Label[] lb1,
                               Label[] lb2, ImageView[] imgv, int index, int limit)
    {
        int start;
        for(start = 0 ; (start+index)<l1.size() && start<limit; start++)
        {
            vb1[start].setVisible(true);
            lb1[start].setText(l1.get(start+index));
            lb2[start].setText(l2.get(start+index));
            imgv[start].setImage(img.get(start+index));
        }
        return start;
    }


    public static int sortBox(List<String> l1, List<String> l2, List<Integer> l3, List<Integer> l4,
                              List<Image> img, VBox[] vb1, VBox[] vb2, Label[] lb1, Label[] lb2, Label[] lb3,
                              Label[] lb4, ImageView[] imgv, int index, int limit)
    {
        sortBox(l1, l2, img, vb1, lb1, lb2, imgv, index, limit);
        int start;
        for(start = 0 ; (start+index)<l1.size() && start<limit; start++)
        {
            vb2[start].setVisible(true);
            lb3[start].setText(Integer.toString(l3.get(start+index)));
            lb4[start].setText(Integer.toString(l4.get(start+index)));
        }
        return start;
    }


    //functions for creating manually
    //shows for index end to 0

    public static int initiate(List<String> list, VBox vb, int lblPadding, int lblMaxWidth, String lblStyle,
                               ImageView downArrow, int index, int limit, boolean region,int pos)
    {
        // INITIAL MUST BE LIST SIZE - 1
        index = sortVBox(list,vb,lblPadding,lblMaxWidth,lblStyle,list.size()-1,limit,region, pos);
        if(index-limit>=0)
        {
            downArrow.setVisible(true);
        }
        return index;
    }

    public static int down_arrow_clicked(List<String> list, VBox vb, int lblPadding, int lblMaxWidth,
                                         String lblStyle, ImageView downArrow, ImageView upArrow, int index,
                                         int limit, boolean region, int pos)
    {
        index -= limit;
        index = sortVBox(list,vb,lblPadding,lblMaxWidth,lblStyle,index,limit,region, pos);
        upArrow.setVisible(true);
        if(index-limit>=0)
        {
            downArrow.setVisible(true);
        }
        else {downArrow.setVisible(false);}
        return index;
    }


    public static int up_arrow_clicked(List<String> list, VBox vb, int lblPadding, int lblMaxWidth,
                                       String lblStyle, ImageView downArrow, ImageView upArrow, int index,
                                       int limit, boolean region, int pos)
    {
        index += limit;
        index = sortVBox(list,vb,lblPadding,lblMaxWidth,lblStyle,index,limit,region, pos);
        if(index==(list.size()-1))
        {
            upArrow.setVisible(false);
        }
        downArrow.setVisible(true);
        return index;
    }


    public static int sortVBox(List<String> list, VBox vb, int lblPadding, int lblMaxWidth, String lblStyle,
                               int index, int limit, boolean region, int pos)
    {
        System.out.println("the start index is "+index);
        vb.getChildren().clear();
        if (pos == 0 || pos == 3) { btnInvisible(dashboard.btn, pos);}
        int start;
        for(start = 0 ; (index-start)>=0 && start<limit; start++)
        {
            HBox hb = new HBox();
            Label lb = new Label(list.get(index-start));
            lb.setPadding(new Insets(lblPadding));
            //lb.setWrapText(true);
            lb.setMaxWidth(lblMaxWidth);
            lb.setStyle(lblStyle);
            lb.setAlignment(Pos.CENTER);
            if(pos == 0 || pos == 3) {
                dashboard.btn[pos + start].setVisible(true);
                if(pos==3) {
                    System.out.println("should be returning");
                    if(dashboard.sStatus.get(index - start).equals("returning"))
                    {
                        System.out.println("is returning");
                        dashboard.btn[pos+start].setText("Confirm");
                    }
                    else {dashboard.btn[pos+start].setText("Request");}
                }
                if ((index-start) == list.size()-1) {
                    hb.setStyle("-fx-background-color:#3b3838");
                    lb.setStyle("-fx-text-fill: #d9d9d9; -fx-font-weight: bold; -fx-font-size: 15");
                    // hb.setAlignment(Pos.CENTER_RIGHT);
                } else {
                    hb.setAlignment(Pos.CENTER_LEFT);
                }
            }
            else{
                    hb.setAlignment(Pos.TOP_CENTER);
                }
            hb.setPadding(new Insets(0,10,0,10));
            hb.getChildren().add(lb);
            if(region) {
                Region rg = new Region();
                rg.setPrefHeight(10);
                rg.setPrefWidth(350);
                vb.getChildren().addAll(hb, rg);
            }
            else
            {
                vb.getChildren().add(hb);
            }
        }
        System.out.println("the end index is "+index);
        return index;
    }

    public static void btnInvisible(Button[] b, int start)
    {
        for(int i = start; i<start+3; i++)
        {
            b[i].setVisible(false);
        }
    }

}
