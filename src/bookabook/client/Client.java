package bookabook.client;

import bookabook.objects.Bookser;
import org.json.JSONException;
import org.json.JSONObject;

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

    public Client () throws IOException, InterruptedException {
        while (true) {
            try {
                // Tries again and again until connecting
                socket = new Socket("127.0.0.1", 9899);
                System.out.println("Successfully connected!");
                // todo NHS: Reference to toasts in both cases
                break;
            } catch (ConnectException e) {
                // Restarting message
                System.out.println("Start your server and connect again.");
                System.out.println("Retrying in ");
                for (int i = 5; i > 0; i--) {
                    System.out.print(i + "  ");
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

    public JSONObject login (String username, String password) throws IOException, ClassNotFoundException {
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
        return (JSONObject) input.readObject();
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



    public ArrayList<Bookser> latest_books (String type, String query) throws JSONException, IOException, ClassNotFoundException {

        JSONObject request  = new JSONObject();
        request.put("type", type);
        request.put("query", query);
        send(request.toString());
        System.out.println(request.toString());

        System.out.println("Started reading array list");
        ArrayList books_objects = (ArrayList) input.readObject();
        System.out.println("Done reading array list");
        ArrayList<Bookser> books = new ArrayList<>();

        for (int i=0; i<books_objects.size(); i++) {
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
