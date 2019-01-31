package bookabook.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

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

    public boolean send (String str) {
        try {
            output.writeUTF(str);
            System.out.println("Json sent: " + str);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to send request");
            return false;
        }
    }

    public boolean login (String username, String password) {
        request = new JSONObject();

        try {
            request.put("type", "login");
            request.put("username", username);
            request.put("password", password);
        } catch (JSONException e) {
            System.out.println("Error creating/sending json");
        }
        return send(request.toString());
    }

    public boolean signup (String full_name, String username, String password,
                           String dob, String email) {
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
        return send(request.toString());
    }

}
