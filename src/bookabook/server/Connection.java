package bookabook.server;


import bookabook.objects.Bookser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")
class Connection extends Thread {

    private DataInputStream input;
    private ObjectOutputStream output;
    private Socket socket;

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
                        send(response.toString());
                        break;
                    }

                    case "profile/login": {
                        login(request.getString("username"), request.getString("id"));
                        break;
                    }

                    case "messages/add": {
                        response = db.send_message(
                                request.getInt("id"),
                                request.getString("username"),
                                request.getString("message_type"),
                                request.getString("body")
                        );
                        broadcast(request.getString("body"), request.getString("username"));
                        send(response.toString());
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
                        // output.writeObject(books);
                        System.out.println("Sending images: " + books.size());
                        for (Bookser book : books) {
                            book.sendImage(output);
                        }
                        System.out.println("Successfully sent all objects and images!");

                        break;
                    }

                    case "books/similar": {
                        ArrayList<Bookser> books = db.similar_books(request.getString("genre"));
                        output.writeObject(books);
                        System.out.println("Sending images: " + books.size());
                        for (Bookser book : books) {
                            book.sendImage(output);
                        }
                        System.out.println("Successfully sent all objects and images!");

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
                                request.getInt("renter_id"),
                                request.getInt("rentee_id"),
                                request.getInt("book_id"),
                                request.getInt("week")
                        );
                        send(response.toString());
                        break;
                    }

                    case "books/details": {
//                        response = db.rent_book(
//
//                        )
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
                                request.getString("contact_no")
                        );

                        // receiving and saving profile picture
                        // BufferedImage image = ImageIO.read(input);
                        // ImageIO.write(image, "png", new File("D:\\Bookabook\\src\\bookabook\\server\\images\\users\\" + String.valueOf(request.getInt("user_id")) + ".png" ));
                        // input.skipBytes(16);

                        // return response
                        send(response);
                        break;
                    }

                    //todo TMD Profile Page info
                    case "profile/details": {
                        // give work, birthdate, location, email, contact as jsonarray
                    }

                    // todo TMD for dashboard from getBooks function from Client
                    case "books/upcoming": {
                        // JsonArray containing book_name, renter_name, days_left. Shortest time left should be
                        // at the end of the array
                    }

                    case "books/shared": {
                        // JsonArray containing book_name, rentee_name, days_left. Shortest time left should be
                        // at the end of the array
                    }


                    // todo TMD to rent out a book
                    case "books/rent_out":{
                        // see rentOutBook func from Client.
                        // add mechanism to input book image
                    }


                    // todo TMD for book details page
                    case "books/detail" : {
                        //
                    }

                    // todo TMD do these two for profile page books request
                    case "books/rented": {
                        ArrayList<Bookser> books = db.rented_books();
                        output.writeObject(books);
                        System.out.println("Sending images: " + books.size());
                        for (Bookser book : books) {
                            book.sendImage(output);
                        }
                        System.out.println("Successfully sent all objects and images!");

                        break;
                    }

                    case "books/rented_out": {
                        ArrayList<Bookser> books = db.rented_out_books();
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
            image = ImageIO.read(new File("D:\\Bookabook\\src\\bookabook\\server\\images\\users\\" +
                    id + ".png"));
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
        for (Connection c: Server.clients) {
            // Skip broadcast if user is same
            if (c.getName().equals(username))
                continue;
            send(body);
        }
    }

    private void log(String str) {
        System.out.printf("%s [%s]\n", str, getName());
    }

}