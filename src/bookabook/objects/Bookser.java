package bookabook.objects;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Bookser implements Serializable {

    private static final long serialVersionUID = 12345L;
    private String name;
    private String author;
    private double rent;
    private double deposit;
    private transient BufferedImage image;

    // nice: image sending code
//    // Sends object
//    out.writeObject(std);
//    out.flush();
//
//    // Reading, sending images
//    BufferedImage image = ImageIO.read(new File("E:\\Coding\\Code\\Gava\\src\\server\\" + String.valueOf(std.getId()) + ".jpg"));
//    ImageIO.write(image, "png", out);
//
//    // Breaking out of loop
//    if (std.getId() == -1)
//        break;

    // nice: image receiving, storing code
//    // Receiving, logging, saving images
//    BufferedImage image = ImageIO.read(inputStream);
//    System.out.printf("Successfully saved image: %sx%s\n", image.getWidth(), image.getHeight());
//    ImageIO.write(image, "png", new File("E:\\Coding\\Code\\Gava\\src\\server\\upload\\" + String.valueOf(std.getId()) + ".png"));
//
//    // Clearing out leftover bytes
//    System.out.println("Lefover bytes: " + inputStream.available());
//    inputStream.skipBytes(20);

}
