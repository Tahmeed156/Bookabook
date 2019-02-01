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

    public Bookser(String n, String a, double r, double d) {
        name = n;
        author = a;
        rent = r;
        deposit = d;
    }

    public void sendImage (ObjectOutputStream out) {
        try {

            image = ImageIO.read(new File("D:\\Bookabook\\src\\bookabook\\client\\Pictures\\" + name + ".png"));
            System.out.println("Sending image for book: " + name);
            ImageIO.write(image, "png", out);

        } catch (IOException e) {
            System.out.println("Couldn't send image - " +
            name + " | " + author);
        }
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

    // nice: image receiving, storing code
//    // Receiving, logging, saving images
//    BufferedImage image = ImageIO.read(inputStream);
//    System.out.printf("Successfully saved image: %sx%s\n", image.getWidth(), image.getHeight());
//    ImageIO.write(image, "png", new File("E:\\Coding\\Code\\Gava\\src\\server\\upload\\" + String.valueOf(std.getId()) + ".png"));
//
//    // Clearing out leftover bytes
//    System.out.println("Lefover bytes: " + inputStream.available());
//    inputStream.skipBytes(20);


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
