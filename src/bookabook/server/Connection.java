package bookabook.server;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;

@SuppressWarnings("Duplicates")
class Connection extends Thread {

    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    Connection(Socket soc, ThreadGroup tg) {
        super(tg, "Anonymous");
        try {
            this.socket = soc;
            // this.input = new ObjectInputStream(soc.getInputStream());
            // this.output = new ObjectOutputStream(soc.getOutputStream());
            this.input = new DataInputStream(soc.getInputStream());
            this.output = new DataOutputStream(soc.getOutputStream());

        } catch (IOException e) {
            log("Error in i/o at startup");
        }

        this.start();
    }

    @Override
    public void run() {

        while (true) {

            // Getting requests (Json objects)
            JSONObject request;
            try {
                request = new JSONObject(input.readUTF());
            }
            catch (IOException e) {
                System.out.println("User disconnected");
                break;
            }
            catch (JSONException e) {
                System.out.println("Error reading json.");
                break;
            }

            try {
                System.out.println("The message type is: " + request.get("type"));
            } catch (JSONException e) {
                System.out.println("Error getting JSON");
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
//        try {
//            output.writeObject(obj);
//        }
//        catch (IOException e) {
//            log("Cannot output to bookabook.client");
//        }
    }

    private void log(String str) {
        System.out.printf("%s [%s]\n", str, getName());
    }

}