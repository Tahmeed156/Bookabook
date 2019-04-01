package bookabook.client;

import bookabook.objects.Bookser;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.json.JSONException;
import org.json.JSONObject;
import bookabook.client.controllers.toast;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import bookabook.client.controllers.dashboard;
import bookabook.client.controllers.bookDetailsPage;

public class Client {


    // To communicate with server
    public ObjectInputStream input;
    private DataOutputStream output;
    private Socket socket;
    private JSONObject request;
    static String message;

    public Client () throws IOException, InterruptedException {
        while (true) {
            try {
                // Tries again and again until connecting
                socket = new Socket("127.0.0.1", 9899);
                // socket = new Socket("192.168.43.200", 9899);
                System.out.println("Successfully connected!");
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        toast.set("SUCCESSFULLY CONNECTED TO SERVER", "#5cb85c");
                    }
                });



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
                            toast.set(message, "#f0ad4e");
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

    public void getProPic() throws IOException, JSONException {
        JSONObject request = new JSONObject();
        Preferences userCon = Main.userCon;
        String user = userCon.get("username","Anonymous");
        request.put("username",user);
        request.put("type", "profile/login");
        request.put("id",userCon.get("id","1"));
        if(user.equals("")) {
            request.put("username", "Anon");
        }
        else{
            output.writeUTF(request.toString());
            dashboard.proPic = ImageIO.read(input);
            input.skipBytes(16);
        }

    }

    public String rentOutBook (int user_id, String book, String author, Double rent,
                                Double deposit, String genre, String print, String condition,
                                String review, String year_bought, File file) throws IOException, ClassNotFoundException {
        request = new JSONObject();

        try {
            request.put("type", "books/rent_out");
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

        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        // send Image
        BufferedImage image = ImageIO.read(file);
        ImageIO.write(image, "png", output);
        return (String) input.readObject();
    }

    public String editProfile (int user_id, String name, String loc, String work,
                                String gender, String email, String contact_no,
                               Boolean change_pic, File file) throws IOException, ClassNotFoundException {
        request = new JSONObject();

        try {
            request.put("type", "profile/edit");
            request.put("user_id", user_id);
            request.put("name", name);
            request.put("loc", loc);
            request.put("work", work);
            request.put("gender", gender);
            request.put("email", email);
            request.put("contact_no", contact_no);
            request.put("change_pic",change_pic);

        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        if(change_pic) {
            BufferedImage image = ImageIO.read(file);
            ImageIO.write(image, "png", output);
            dashboard.proPic = null;
        }
        return (String) input.readObject();
    }

    public String rentBook(int book_id,
                            int week, int renter_id, int rentee_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "books/rent");
            request.put("renter_id", renter_id);
            request.put("rentee_id", rentee_id);
            request.put("book_id", book_id);
            request.put("week", week);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }

    public String reviewAdd(int reviewer_id, int book_id, String body) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "review/add");
            request.put("reviewer_id", reviewer_id);
            request.put("book_id", book_id);
            request.put("body", body);

        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }

    public String reviewGet(int book_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "review/get");
            request.put("book_id",book_id);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }


    public ArrayList<Bookser> latest_books(int user_id, String type, String query) throws JSONException, IOException, ClassNotFoundException {

        JSONObject request = new JSONObject();
        request.put("type", type);
        request.put("query", query);
        request.put("user_id",user_id);
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

    public void addMessage(int id, String username, String message_type, String body) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "messages/add");
            request.put("id", id);
            request.put("username",username);
            request.put("message_type",message_type);
            request.put("body",body);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        //return (String) input.readObject();
    }

    public String getMessage() throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "messages/get");
        } catch (JSONException e) {
            System.err.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }

    public String getOnline(String username) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "messages/online");
            request.put("username",username);
        } catch (JSONException e) {
            System.err.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }

    public String getBooks(String type, int user_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", type);
            request.put("user_id",user_id);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }

    public ArrayList<Bookser> getSimilarBooks(String genre, int book_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "books/similar");
            request.put("genre",genre);
            request.put("book_id",book_id);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (ArrayList<Bookser>) input.readObject();
    }

    public String getBookDetails(int book_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "books/details");
            request.put("book_id", book_id);
        }
        catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        String reply = (String) input.readObject();

        bookDetailsPage.book_image = ImageIO.read(input);
        input.skipBytes(16);
        bookDetailsPage.renter_image = ImageIO.read(input);
        input.skipBytes(16);

        return reply;
    }

    public String getProfile(int user_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "profile/details");
            request.put("id",user_id);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }

    //to signal the CLIENT AND the server to stop sending messages
    public void endMessage(Boolean messageable){
        request = new JSONObject();
        try {
            request.put("type", "messages/status");
            request.put("messageable", messageable);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
    }

    public String requestBook(int book_id, int user_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "books/request");
            request.put("book_id",book_id);
            request.put("user_id",user_id);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }

    public String returnBook(int book_id, int user_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "books/return");
            request.put("book_id",book_id);
            request.put("user_id",user_id);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }


    public String confirmBook(int book_id, int user_id) throws IOException, ClassNotFoundException {
        request = new JSONObject();
        try {
            request.put("type", "books/return/confirm");
            request.put("book_id",book_id);
            request.put("user_id",user_id);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        send(request.toString());
        return (String) input.readObject();
    }

}
