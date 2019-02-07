package bookabook.server;


import bookabook.objects.Bookser;
import bookabook.server.models.User;
import com.mysql.cj.xdevapi.JsonArray;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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
        boolean success = false;

        // Loggin in user
        String response = null;
        JSONObject user;
        try {
            response = input.readUTF();
            user = new JSONObject(response);
            login(user.getString("username"));
            BufferedImage image = ImageIO.read(new File("D:\\Bookabook\\src\\bookabook\\client\\Pictures\\users\\" +
                    user.getString("id") + ".png"));
            ImageIO.write(image, "png", output);
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        while (true) {

            // Getting requests (Json objects)
            JSONObject request, response;
            try {
                request = new JSONObject(input.readUTF());
                System.out.println(request.toString());

                String type = request.getString("type");
                System.out.println("The message type is: " + type);

                switch (type) {

                    case "login": {
                        response = db.login(
                                request.getString("username"),
                                request.getString("password")
                        );
                        login(response.getString("full_name"));
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
                        login(response.getString("full_name"));
                        send(response.toString());
                        break;
                    }

                    case "messages/add": {
                        success = db.send_message(
                                request.getInt("id"),
                                request.getString("username"),
                                request.getString("message_type"),
                                request.getString("body")
                        );
                        send(success);
                        break;
                    }

                    case "messages/online": {
                        JSONArray response_arr = db.online(request.getString("username"));
                        send(response_arr);
                        break;
                    }

                    case "messages/get": {
                        JSONArray response_arr = db.get_messages();
                        send(response_arr);
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

                    //todo TMD loction field add

                    case "profile/edit": {
                        response = db.edit_profile(
                                request.getInt("user_id"),
                                request.getString("name"),
                                request.getString("work"),
                                request.getString("gender"),
                                request.getString("email"),
                                request.getString("contact_no")
                        );
                        send(response);
                        break;
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

                    case "books/rent_out": {
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
    private void login(String name) {
        setName(name);
        System.out.println(getName() + " connected to the network");
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

    private void log(String str) {
        System.out.printf("%s [%s]\n", str, getName());
    }

}