package bookabook.objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Bookser implements Serializable {

    private static final long serialVersionUID = 12345L;
    private String name;
    private String author;
    private double rent;
    private double deposit;
    private transient BufferedImage image;

    // private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    private String dir = "D:\\"; // Tahmeed config
    private String path = dir + "Bookabook\\src\\bookabook\\server\\images\\books\\";

    public Bookser(String n, String a, double r, double d) {
        name = n;
        author = a;
        rent = r;
        deposit = d;
    }

    public void sendImage (ObjectOutputStream out) {
        try {
            image = ImageIO.read(new File(path + name + ".png"));
            System.out.println("Read done!");
            System.out.println("Sending image for book: " + name);
            ImageIO.write(image, "png", out);
            System.out.println("SENT!");

        }
        catch (IOException e) {
            System.out.println("Couldn't send image - " +
            name + " | " + author);
        }
        System.out.println("Sent image for book: " + name);
    }

    public void saveImage (BufferedImage img) {
        image = img;
//        try {
//            ImageIO.write(image, "png", new File("D:\\Bookabook\\src\\bookabook\\client\\Pictures\\saved\\"
//                    + name + ".png"));
//        } catch (IOException e) {
//            System.out.println("Could not save image - " + name);
//        }
    }

    public String getName() {
        return name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getAuthor() {
        return author;
    }

    public double getRent() {
        return rent;
    }

    public double getDeposit() {
        return deposit;
    }

    public BufferedImage getImage() {
        return image;
    }
}
