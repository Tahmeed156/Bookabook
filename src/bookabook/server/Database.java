package bookabook.server;

import antlr.debug.MessageAdapter;
import bookabook.objects.Bookser;
import bookabook.server.models.*;

// Hibernate components
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("Duplicates")
public class Database {

    private SessionFactory sessionFactory;
    private Session session;

    Database () {
        // Configuring hibernate
        sessionFactory = new Configuration().configure("/bookabook/server/hibernate.cfg.xml").buildSessionFactory();
    }

    // =====================================   USER AUTHENTICATION

    public JSONObject login (String username, String password) throws JSONException {
        startSession();

        // Generating an input query
        Query query = session.createQuery("from User where username = :u and password = :p");
        query.setParameter("u", username);
        query.setParameter("p", password);

        // Executing the generated input query and handling exception
        try {
            User u = (User) query.uniqueResult();
            // Creating response object
            JSONObject response = new JSONObject();
            response.put("success", "true");
            response.put("id", String.valueOf(u.getId()));
            response.put("full_name", u.getFull_name());
            response.put("wallet", String.valueOf(u.getWallet()));
            response.put("books_shared", String.valueOf(u.getBooks_shared()));
            response.put("books_rented", String.valueOf(u.getBooks_rented()));
            return response;
        }
        catch (Exception e) {
            // In case user is not found
            System.out.println("Wrong login credentials | " + e.getMessage());
            JSONObject response = new JSONObject();
            response.put("success", "false");
            return response;
        }
        finally {
            endSession();
        }
    }

    public JSONObject signup ( String full_name, String username,
            String password, String date, String email)
            throws JSONException {
        startSession();

        // Formatter to convert a string into a Date object
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        User u = new User();
        try {
            // Creating and saving user
            u.signup(
                    full_name,
                    username,
                    password,
                    // returning a date object
                    formatter.parse(date),
                    email
            );
            session.save(u);

            // Creating response object
            JSONObject response = new JSONObject();
            response.put("success", "true");
            response.put("id", String.valueOf(u.getId()));
            response.put("full_name", u.getFull_name());
            response.put("wallet", String.valueOf(u.getWallet()));
            response.put("books_shared", String.valueOf(u.getBooks_shared()));
            response.put("books_rented", String.valueOf(u.getBooks_rented()));
            return response;
        }
        catch (ParseException e) {
            // returning error JSON
            System.out.println(e.getMessage());
            JSONObject response = new JSONObject();
            response.put("success", "false");
            return response;
        }
        finally {
            endSession();
        }
    }

    // =========================================  DATA INPUT

    public JSONObject edit_profile ( int uid, String name, String work, String gender, String email, String contact_no)
            throws JSONException {
        startSession();
        JSONObject response = new JSONObject();

        Query query = session.createQuery("from User where id = :i");
        query.setParameter("i", uid);
        try {
            User u = (User) query.uniqueResult();
            u.edit_profile(name, work, gender, email, contact_no);
            response.put("success", "true");
        }
        catch (Exception e) {
            response.put("success", "false");
        }

        endSession();
        return response;
    }

    public JSONObject rent_book (int b, int w, int rtr, int rte) throws JSONException {
        startSession();

        JSONObject response = new JSONObject();
        try {
            Rent r = new Rent(b, w, rtr, rte);
            session.save(r);
            response.put("success", "true");
        }
        catch (Exception e) {
            response.put("success", "false");
        }
        endSession();
        return response;
    }

    // =========================================  DASHBOARD BOOKS

    public ArrayList<Bookser> latest_books () {
        startSession();

        Query q = session.createQuery("from Book order by timestamp desc").setFirstResult(0).setMaxResults(8);
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            Bookser bser = new Bookser(b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    public ArrayList<Bookser> trending_books () {
        startSession();

        // bug TMD: Number of rents to determine trending books
        Query q = session.createQuery("from Book order by timestamp desc").setFirstResult(0).setMaxResults(8);
        // bug TMD: Paginate all these
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            Bookser bser = new Bookser(b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    public ArrayList<Bookser> similar_books (String genre) {
        startSession();

        Query q = session.createQuery("from Book where genre = :gen order by timestamp desc").setFirstResult(0).setMaxResults(8);
        q.setParameter("gen", genre);
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            Bookser bser = new Bookser(b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    public ArrayList<Bookser> searching_books (String str) {
        startSession();

        Query q = session.createQuery("from Book where name LIKE CONCAT('%', :str,'%')").setFirstResult(0).setMaxResults(8);
        q.setParameter("str", str);
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            Bookser bser = new Bookser(b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    // ========================================   PROFILE PAGE BOOKS

    public ArrayList<Bookser> rented_books() {
        startSession();

        Query q = session.createQuery("from Book order by timestamp desc").setFirstResult(0).setMaxResults(8);
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            Bookser bser = new Bookser(b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    public ArrayList<Bookser> rented_out_books () {
        startSession();

        Query q = session.createQuery("from Book order by timestamp desc").setFirstResult(0).setMaxResults(8);
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            Bookser bser = new Bookser(b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    // ========================================   BOOK DETAILS PAGE

    public JSONObject add_review(int reviewer_id, int book_id, String body) throws JSONException {
        startSession();
        JSONObject response = new JSONObject();

        try {
            Review r = new Review(reviewer_id, book_id, body);
            session.save(r);
            response.put("success", "true");
        }
        catch (Exception e) {
            response.put("success", "false");
        }
        endSession();
        return response;
    }

    public JSONArray get_reviews(int book_id) throws JSONException {
        startSession();

        JSONArray response = new JSONArray();
        Query q = session.createQuery("from Review order by timestamp desc").setFirstResult(0).setMaxResults(8);
        List reviews = q.getResultList();
        for (Object review1 : reviews) {
            Review r = (Review) review1;
            JSONObject review = new JSONObject();
            review.put("username", r.getReviewer().getFull_name());
            System.out.println(r.getReviewer().getFull_name());
            review.put("body", r.getBody());
            response.put(review);
        }
        endSession();
        return response;
    }

    // ========================================   MESSAGES

    public JSONArray online(String username) {
        JSONArray response = new JSONArray();
        for (Connection c: Server.clients) {
            if (c.isAlive() || !c.getName().equals(username))
                response.put(c.getName());
        }
        return response;
    }

    public JSONArray get_messages() throws JSONException {
        startSession();

        JSONArray response = new JSONArray();
        // Gets 25 messages from the database
        Query q = session.createQuery("from Message order by timestamp desc").setFirstResult(0).setMaxResults(25);
        List messages = q.getResultList();

        for (Object mess : messages) {
            Message m = (Message) mess;
            // Create a JSONObject for each message
            JSONObject message = new JSONObject();
            message.put("username", m.getSender_name());
            message.put("user_id", m.getSender_id());
            message.put("body", m.getBody());
            message.put("timestamp", m.getTimestamp().toString());
            // Adds the created JSONObject to JSONArray
            response.put(message);
        }
        endSession();
        return response;
    }

    public JSONObject send_message (int id, String name, String type, String body) throws JSONException {
        startSession();
        JSONObject response = new JSONObject();
        try {
            Message m = new Message(id, name, type, body);
            session.save(m);
            response.put("success", "true");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            response.put("success", "false");
        }
        endSession();
        return response;
    }

    // ========================================   UTILITY

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        sessionFactory.close();
    }

    private void startSession () {
        // Opening session
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    private void endSession () {
        // Closing session
        session.getTransaction().commit();
        session.close();
    }

}
