package bookabook.server;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

    public static ArrayList<Connection> clients = new ArrayList<>();
    private static ThreadGroup clientGroup = new ThreadGroup("clients");
    public static SessionFactory sessionFactory;

    public static void main (String [] args) throws IOException {

        // Creating the server
        ServerSocket server = new ServerSocket(9899);
        Socket socket;
        System.out.println("Server running!");

        // Configuring hibernate
        sessionFactory = new Configuration().configure("/bookabook/server/hibernate.cfg.xml").buildSessionFactory();

        // Accepting new clients
        while (true) {
            socket = server.accept();
            System.out.println("Got you");
            clients.add(new Connection(socket, clientGroup));
        }

    }

    private static void log (String str) {
        System.out.printf("%s [%s]\n", str, "Main thread");
    }

}