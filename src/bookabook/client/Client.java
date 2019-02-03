package bookabook.client;

import bookabook.objects.Bookser;
import javafx.application.Platform;
import org.json.JSONException;
import org.json.JSONObject;
import bookabook.client.controllers.toast;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private ObjectInputStream input;
    private DataOutputStream output;
    private Socket socket;
    private JSONObject request;
    static String message;

    public Client () throws IOException, InterruptedException {
        while (true) {
            try {
                // Tries again and again until connecting
                socket = new Socket("127.0.0.1", 9899);
                System.out.println("Successfully connected!");
                // todo NHS: Reference to toasts in both cases
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        toast.set("SUCCESSFULLY CONNECTED TO SERVER", "#5cb85c");
                    }
                });
                // todo:message disappears immediately after connecting


                break;
            } catch (ConnectException e) {
                // Restarting message
                System.out.println("Start your server and connect again.");
                System.out.println("Retrying in ");
                message = "Start your server and connect again.\nRetrying in : ";
                for (int i = 5; i > 0; i--) {
                    System.out.print(i + "  ");
                    message += i+" ";

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            toast.set(message, "#D9534F");
                        }
                    });
                    Thread.sleep(1000);
                }
                System.out.println();
            }
        }

        // Setting up I/O
        input = new ObjectInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        input.close();
        output.close();
        socket.close();
    }

    private boolean send (String str) {
        try {
            output.writeUTF(str);
            System.out.println("Json sent: " + str);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to send request");
            return false;
        }
    }

    public String login (String username, String password) throws IOException, ClassNotFoundException {
        // Preparing request and handling failures
        request = new JSONObject();
        try {
            request.put("type", "login");
            request.put("username", username);
            request.put("password", password);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        // Sending prepared request and handling response
        send(request.toString());
        return (String) input.readObject();
    }

    public String signup (String full_name, String username, String password,
                           String dob, String email) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "signup");
            request.put("full_name", full_name);
            request.put("username", username);
            request.put("password", password);
            request.put("dob", dob);
            request.put("email", email);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }


    public boolean rentOutBook (int user_id, String book, String author, Double rent,
                                Double deposit, String genre, String print, String condition,
                                String review, String year_bought) {
        request = new JSONObject();

        try {
            request.put("type", "rent_out_book");
            request.put("user_id", user_id);
            request.put("book", book);
            request.put("author", author);
            request.put("rent", rent);
            request.put("deposit", deposit);
            request.put("genre", genre);
            request.put("print", print);
            request.put("condition", condition);
            request.put("review", review);
            request.put("year_bought", year_bought);
            //todo TMD: input image from user ??

        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        return send(request.toString());
    }


    public boolean editProfile (int user_id, String name, String dob, String work,
                                String gender, String email, String contact_no,
                                String about) {
        request = new JSONObject();

        try {
            request.put("type", "edit_profile");
            request.put("user_id", user_id);
            request.put("name",name);
            request.put("dob",dob);
            request.put("work",work);
            request.put("gender",gender);
            request.put("email",email);
            request.put("contact_no",contact_no);
            request.put("about",about);
            //todo TMD: input image from user ?? Also DEFAULT IMAGE

        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        return send(request.toString());
    }

    public Boolean rentBook(int user_id, String book, int renter_id) {
        request = new JSONObject();
        try {
            request.put("type", "rent_book");
            request.put("user_id", user_id);
            request.put("book", book);
            request.put("rented_id", renter_id);

        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        return send(request.toString());
    }

    public ArrayList<Bookser> latest_books(String type, String query) throws JSONException, IOException, ClassNotFoundException {

        JSONObject request = new JSONObject();
        request.put("type", type);
        request.put("query", query);
        send(request.toString());
       // System.out.println(request.toString());

        System.out.println("Started reading array list");
        ArrayList books_objects = (ArrayList) input.readObject();
        System.out.println("Done reading array list");
        ArrayList<Bookser> books = new ArrayList<>();

        for (int i = 0; i < books_objects.size(); i++) {
            System.out.println("Saving image " + i);
            Bookser book = (Bookser) books_objects.get(i);
            BufferedImage image = ImageIO.read(input);
            book.saveImage(image);
            // To omit end of file
            input.skipBytes(16);
            books.add(book);
        }

        return books;

    }

}
