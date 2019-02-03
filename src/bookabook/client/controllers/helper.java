package bookabook.client.controllers;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class helper {

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
}
