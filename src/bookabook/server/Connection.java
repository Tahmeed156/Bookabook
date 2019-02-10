package bookabook.server;


import bookabook.objects.Bookser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")
class Connection extends Thread {

    private String dir = "E:\\Projects\\CSE\\BookABook\\Code\\"; // Najib config
    // private String dir = "A:\\"; // Tahmeed config
    // private String dir = "D:\\"; // Tahmeed config
    private String path_user = dir + "Bookabook\\src\\bookabook\\server\\images\\users\\";
    private String path_book = dir + "Bookabook\\src\\bookabook\\server\\images\\books\\";

    private DataInputStream input;
    private ObjectOutputStream output;
    private Socket socket;
    private boolean messageable = false;

    Connection(Socket soc, ThreadGroup tg) {
        super(tg, "Anonymous");
        try {
            this.socket = soc;
            // this.input = new ObjectInputStream(soc.getInputStream());
            // this.output = new ObjectOutputStream(soc.getOutputStream());
            this.input = new DataInputStream(soc.getInputStream());
            this.output = new ObjectOutputStream(soc.getOutputStream());

        } catch (IOException e) {
            log("Error in i/o at startup");
        }

        this.start();
    }

    @Override
    public void run() {

        Database db = new Database();

        while (true) {

            // Getting requests (Json objects)
            JSONObject request, response;
            try {
                request = new JSONObject(input.readUTF());
                System.out.println(getName() + " sent request: " + request.toString());
                String type = request.getString("type");

                switch (type) {

                    case "login": {
                        System.out.println("1");
                        response = db.login(
                                request.getString("username"),
                                request.getString("password")
                        );
                        send(response.toString());
                        break;
                    }

                    case "signup": {
                        response = db.signup(
                                request.getString("full_name"),
                                request.getString("username"),
                                request.getString("password"),
                                request.getString("dob"),
                                request.getString("email")
                        );
                        if(Boolean.valueOf(response.getString("success")))
                        {
                            File file = new File(path_user+"default.png");
                            BufferedImage bi = ImageIO.read(file);
                            File outputfile = new File(path_user + response.getInt("id")+".png");
                            ImageIO.write(bi, "png", outputfile);
                        }
                        send(response.toString());
                        break;
                    }

                    case "profile/login": {
                        login(request.getString("username"), request.getString("id"));
                        break;
                    }

                    case "messages/add": {
                        db.send_message(
                                request.getInt("id"),
                                request.getString("username"),
                                request.getString("message_type"),
                                request.getString("body")
                        );
                        broadcast(request.getString("body"), request.getString("username"));
                        //send(response.toString());
                        break;
                    }

                    case "messages/online": {
                        JSONArray response_arr = db.online(request.getString("username"));
                        send(response_arr.toString());
                        break;
                    }

                    case "messages/get": {
                        JSONArray response_arr = db.get_messages();
                        send(response_arr.toString());
                        break;
                    }

                    case "messages/stop": {
                        messageable = request.getBoolean("messageable");
                        if(!messageable) {
                            response = new JSONObject();
                            response.put("stop", "true");
                            send(response.toString());
                        }
                        break;
                    }


                    case "books/latest": {
                        ArrayList<Bookser> books = db.latest_books();
                        output.writeObject(books);
                        System.out.println("Sending images: " + books.size());
                        for (Bookser book : books) {
                            book.sendImage(output);
                        }
                        System.out.println("Successfully sent all objects and images!");

                        break;
                    }

                    case "books/trending": {

                        ArrayList<Bookser> books = db.trending_books();
                        output.writeObject(books);
                        System.out.println("Sending images: " + books.size());
                        for (Bookser book : books) {
                            book.sendImage(output);
                        }
                        System.out.println("Successfully sent all objects and images!");

                        break;
                    }

                    case "books/similar": {
                        ArrayList<Bookser> books = db.similar_books(
                                request.getString("genre"),
                                request.getInt("book_id")
                                );
                        output.writeObject(books);
//                        System.out.println("Sending images: " + books.size());
//                        for (Bookser book : books) {
//                            book.sendImage(output);
//                        }
//                        System.out.println("Successfully sent all objects and images!");

                        break;
                    }

                    case "books/search": {
                        String str = request.getString("query");
                        ArrayList<Bookser> books = db.searching_books(str);
                        output.writeObject(books);
                        System.out.println("Sending images: " + books.size());
                        for (Bookser book : books) {
                            book.sendImage(output);
                        }
                        System.out.println("Successfully sent all objects and images!");

                        break;
                    }

                    case "books/rent": {
                        response = db.rent_book(
                                request.getInt("book_id"),
                                request.getInt("week"),
                                request.getInt("renter_id"),
                                request.getInt("rentee_id")
                                );
                        send(response.toString());
                        break;
                    }

                    case "books/details": {
                        response = db.book_details(
                                request.getInt("book_id")
                        );
                        System.out.println(response.toString());
                        send(response.toString());
                        // Getting and sending image for book
                        BufferedImage image  = ImageIO.read(new File(path_book + response.getString("book") + ".png"));
                        ImageIO.write(image, "png", output);
                        // Getting and sending image for user
                        image  = ImageIO.read(new File(path_user + String.valueOf(response.getInt("owner_id")) + ".png"));
                        ImageIO.write(image, "png", output);
                        break;
                    }

                    case "review/add": {
                        response = db.add_review(
                                request.getInt("reviewer_id"),
                                request.getInt("book_id"),
                                request.getString("body")
                        );
                        send(response.toString());
                        break;
                    }

                    case "review/get": {
                        JSONArray response_arr;
                        response_arr = db.get_reviews(request.getInt("book_id"));
                        send(response_arr.toString());
                        break;
                    }

                    case "profile/edit": {
                        response = db.edit_profile(
                                request.getInt("user_id"),
                                request.getString("name"),
                                request.getString("loc"),
                                request.getString("work"),
                                request.getString("gender"),
                                request.getString("email"),
                                request.getString("contact_no"),
                                request.getBoolean("change_pic")
                        );

                        // receiving and saving profile picture
                        if(response.getBoolean("change_pic")) {
                            BufferedImage image = ImageIO.read(input);
                            ImageIO.write(image, "png", new File(path_user + request.getInt("user_id") + ".png"));
                            input.skipBytes(16);
                        }

                        // return response
                        send(response.toString());
                        break;
                    }

                    case "profile/details": {
                        // give work, birthdate, location, email, contact
                        response = db.profile_info(request.getInt("id"));
                        send(response.toString());
                        break;
                    }

                    case "books/upcoming": {
                        JSONArray response_arr = db.upcoming_books(
                                request.getInt("user_id")
                        );
                        send(response_arr.toString());
                        break;
                    }

                    case "books/shared": {
                        JSONArray response_arr = db.shared_books(
                                request.getInt("user_id")
                        );
                        send(response_arr.toString());
                        break;
                    }


                    // todo TMD to rent out a book
                    case "books/rent_out":{
                        response = db.rent_out_book(
                                request.getInt("user_id"),
                                request.getString("book"),
                                request.getString("author"),
                                request.getDouble("rent"),
                                request.getDouble("deposit"),
                                request.getString("genre"),
                                request.getString("print"),
                                request.getString("condition"),
                                request.getString("review"),
                                request.getString("year_bought")
                        );
                        if(Boolean.valueOf(response.getString("success")))
                        {
                            BufferedImage image = ImageIO.read(input);
                            ImageIO.write(image, "png", new File(path_book + request.getString("book") + ".png"));
                            input.skipBytes(16);
                        }
                        send(response.toString());
                        break;
                    }

                    case "books/detail" : {

                    }

                    // todo TMD do these two for profile page books request
                    case "books/rented": {
                        ArrayList<Bookser> books = db.rented_books(request.getInt("user_id"));
                        output.writeObject(books);
                        System.out.println("Sending images: " + books.size());
                        for (Bookser book : books) {
                            book.sendImage(output);
                        }
                        System.out.println("Successfully sent all objects and images!");

                        break;
                    }

                    case "books/rented_out": {
                        ArrayList<Bookser> books = db.rented_out_books(request.getInt("user_id"));
                        output.writeObject(books);
                        System.out.println("Sending images: " + books.size());
                        for (Bookser book : books) {
                            book.sendImage(output);
                        }
                        System.out.println("Successfully sent all objects and images!");
                        break;
                    }

                    default:
                        break;
                }

            }
            catch (IOException e) {
                System.out.println("User disconnected");
                break;
            }
            catch (JSONException e) {
                e.printStackTrace();
                System.out.println("Error reading json.");
                break;
            }

        }

        try {
            this.input.close();
            this.output.close();
            this.socket.close();
        } catch (IOException e) {
            log(e.getMessage());
        }

    }

    // Logging in users (showing status, setting thread name)
    private void login(String username, String id) {
        System.out.println("3.5");
        setName(username);
        System.err.println(username + " connected to the server");
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path_user + id + ".png"));
            ImageIO.write(image, "png", output);
        }
        catch (IOException e) {
            System.out.println("Error sending profile picture to client");
        }
    }

    // Sending objects from server to bookabook.client
    private void send(Object obj) {
        try {
            output.writeObject(obj);
        }
        catch (IOException e) {
            log("Cannot output to client");
        }
    }

    // Sending the message to all users
    private void broadcast(String body, String username) {
        JSONObject response = new JSONObject();
        response.put("body", body);
        response.put("username", username);
        for (Connection c: Server.clients) {
            // Skip broadcast if user is same
            if (c.isAlive() && c.isMessageable() && c.getName().equals(username))
                continue;
            c.send(response.toString());
        }
    }

    private boolean isMessageable() {
        return messageable;
    }

    private void log(String str) {
        System.out.printf("%s [%s]\n", str, getName());
    }

}