package bookabook.server;


import bookabook.server.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        while (true) {

            // Getting requests (Json objects)
            JSONObject request;
            try {
                request = new JSONObject(input.readUTF());

                String type = request.getString("type");
                System.out.println("The message type is: " + type);

                switch (type) {

                    case "login": {
                        success = db.login(
                                request.getString("username"),
                                request.getString("password")
                        );
                        send(success);
                        break;
                    }

                    case "signup": {
                        success = db.signup(
                                request.getString("full_name"),
                                request.getString("username"),
                                request.getString("password"),
                                request.getString("dob"),
                                request.getString("email")
                        );
                        send(success);
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