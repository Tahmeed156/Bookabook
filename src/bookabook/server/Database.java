package bookabook.server;

import bookabook.objects.Bookser;
import bookabook.server.models.*;

// Hibernate components
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@SuppressWarnings("Duplicates")
public class Database {

    private Session session;

    Database () {
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

    public JSONObject edit_profile ( int uid, String name, String loc, String work, String gender, String email, String contact_no,
                                     Boolean changePic)
            throws JSONException {
        startSession();
        JSONObject response = new JSONObject();

        Query query = session.createQuery("from User where id = :i");
        query.setParameter("i", uid);
        try {
            User u = (User) query.uniqueResult();
            u.edit_profile(name, loc, work, gender, email, contact_no);
            response.put("success", "true");
            if(changePic)
            {
                response.put("change_pic","true");
            }
            else {response.put("change_pic","false");}
        }
        catch (Exception e) {
            response.put("success", "false");
        }

        endSession();
        return response;
    }

    public JSONObject profile_info (int uid) throws JSONException {
        startSession();
        JSONObject response = new JSONObject();

        // Generating an input query
        Query query = session.createQuery("from User where id = :i");
        query.setParameter("i", uid);
        // Executing the generated input query and handling exception
        try {
            User u = (User) query.uniqueResult();
            // Creating response object
            response.put("success", "true");
            response.put("work",u.getWork());
            response.put("date_of_birth", u.getDate_of_birth().toString());
            response.put("location", u.getLocation());
            response.put("email", u.getEmail());
            response.put("contact_no", u.getContact_no());
            return response;
        }
        catch (Exception e) {
            // In case user is not found
            System.out.println("Wrong credentials | " + e.getMessage());
            response.put("success", "false");
            return response;
        }
        finally {
            endSession();
        }
    }

    public JSONObject rent_book (int b, int w, int rtr, int rte) throws JSONException {
        startSession();

        // Changing the number of times rented
        Query query_1 = session.createQuery("from Book where id = :b");
        query_1.setParameter("b", b);
        Book book = (Book) query_1.uniqueResult();
        book.increase_times_rented();
        session.save(book);

        // Changing wallet for renter
        Query query_2 = session.createQuery("from User where id = :rtr");
        query_2.setParameter("rtr", rtr);
        User renter = (User) query_2.uniqueResult();
        renter.change_shared(1);
        double renter_wallet = renter.increment_wallet(w*book.getRent() + book.getDeposit());

        // Changing wallet for rentee
        Query query_3 = session.createQuery("from User where id = :rte");
        query_3.setParameter("rte", rte);
        User rentee = (User) query_3.uniqueResult();
        rentee.change_rented(1);
        double rentee_wallet = rentee.decrement_wallet(w*book.getRent() + book.getDeposit());

        JSONObject response = new JSONObject();
        try {
            Rent r = new Rent(b, w, rtr, rte);
            session.save(r);
            response.put("success", "true");
            response.put("renter_wallet", renter_wallet);
            response.put("rentee_wallet", rentee_wallet);
        }
        catch (Exception e) {
            response.put("success", "false");
        }
        endSession();
        return response;
    }

    public JSONObject rent_out_book (
            int uid, String n, String a, double rent, double d,
            String g, String p, String c, String review, String y
    ) throws JSONException {
        startSession();
        Book b = new Book();
        JSONObject bookInfo = new JSONObject();
        try {
            bookInfo.put("print", p);
            bookInfo.put("condition", c);
            bookInfo.put("review", review);
            bookInfo.put("year_bought", y);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        b.rent(
                uid,
                n,
                a,
                rent,
                d,
                bookInfo.toString(),
                g
        );
        session.save(b);
        JSONObject response = new JSONObject();
        response.put("success","true");
        endSession();
        return response;

    }

    // =========================================  DASHBOARD BOOKS

    public ArrayList<Bookser> latest_books () {
        startSession();

        Query q = session.createQuery("from Book order by timestamp desc").setFirstResult(0).setMaxResults(10);
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            Bookser bser = new Bookser(b.getId(), b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    public ArrayList<Bookser> trending_books () {
        startSession();

        Query q = session.createQuery("from Book order by times_rented desc").setFirstResult(0).setMaxResults(10);
        // bug TMD: Paginate all these
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            Bookser bser = new Bookser(b.getId(), b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }
        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    public ArrayList<Bookser> similar_books (String genre, int book_id) {
        startSession();

        Query q = session.createQuery("from Book where genre = :gen order by timestamp desc").setFirstResult(0).setMaxResults(6);
        q.setParameter("gen", genre);
        List books = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();
        for (int i=0; i<books.size(); i++) {
            Book b = (Book) books.get(i);
            System.out.println("book id is "+b.getId());
            if(b.getId() == book_id) {continue;} //skip its own name
            Bookser bser = new Bookser(b.getId(), b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
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
        for (Object book : books) {
            Book b = (Book) book;
            Bookser bser = new Bookser(b.getId(), b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    public JSONArray upcoming_books(int user_id) throws JSONException {
        startSession();

        Query q = session.createQuery("from Rent where rentee = :r");
        q.setParameter("r", new User(user_id));

        List results = q.getResultList();
        List<JSONObject> jsonValues = new ArrayList<>();

        for (Object obj: results) {
            Rent r = (Rent) obj;
            // Determining number of days till submission
            Date n = new Date();
            int date = (int) (r.getRenturn_date().getTime() - n.getTime())/(1000*3600*24);
            // Preparing JSONObject for each entry
            JSONObject rj = new JSONObject();
            rj.put("book_name", r.getBook().getName());
            rj.put("renter_name", r.getRenter().getFull_name());
            rj.put("date", date);
            jsonValues.add(rj);
        }

        // Sorting the list
        jsonValues.sort((a, b) -> {
            Integer valB;
            Integer valA;
            try {
                valB = b.getInt("date");
                valA = a.getInt("date");
                return -valA.compareTo(valB);
            } catch (JSONException e) {
                // Can't sort list
                e.printStackTrace();
                return 0;
            }
        });

        // Take elements from the sorted list and put it in JSONArray
        JSONArray response = new JSONArray();
        for (JSONObject j: jsonValues) {
            response.put(j);
        }

        endSession();
        return response;
    }

    public JSONArray shared_books(int user_id) throws JSONException {
        startSession();

        Query q = session.createQuery("from Rent where renter = :r");
        q.setParameter("r", new User(user_id));

        List results = q.getResultList();
        List<JSONObject> jsonValues = new ArrayList<>();

        for (Object obj: results) {
            Rent r = (Rent) obj;
            // Determining number of days till submission
            Date n = new Date();
            int date = (int) (r.getRenturn_date().getTime() - n.getTime())/(1000*3600*24);
            // Preparing JSONObject for each entry
            JSONObject rj = new JSONObject();
            rj.put("book_name", r.getBook().getName());
            rj.put("rentee_name", r.getRentee().getFull_name());
            rj.put("date", date);
            // Adding JSONObject to the list
            jsonValues.add(rj);
        }

        // Sorting the list
        jsonValues.sort((a, b) -> {
            Integer valB;
            Integer valA;
            try {
                valB = b.getInt("date");
                valA = a.getInt("date");
                return -valA.compareTo(valB);
            }
            catch (JSONException e) {
                // Can't sort list
                e.printStackTrace();
                return 0;
            }
        });

        // Take elements from the sorted list and put it in JSONArray
        JSONArray response = new JSONArray();
        for (JSONObject j: jsonValues) {
            response.put(j);
        }

        endSession();
        return response;
    }

    // ========================================   PROFILE PAGE BOOKS

    public ArrayList<Bookser> rented_books(int user_id) {
        startSession();

        Query q = session.createQuery("from Rent where rentee = :r").setFirstResult(0).setMaxResults(8);
        q.setParameter("r", new User (user_id));

        List rents = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();

        for (Object rent: rents) {
            Rent r = (Rent) rent;
            Book b = r.getBook();
            Bookser bser = new Bookser(b.getId(), b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    public ArrayList<Bookser> rented_out_books (int user_id) {
        startSession();

        Query q = session.createQuery("from Rent where renter = :r").setFirstResult(0).setMaxResults(8);
        q.setParameter("r", new User (user_id));

        List rents = q.getResultList();
        ArrayList<Bookser> book_objects = new ArrayList<>();

        for (Object rent: rents) {
            Rent r = (Rent) rent;
            Book b = r.getBook();
            Bookser bser = new Bookser(b.getId(), b.getName(), b.getAuthor(), b.getRent(), b.getDeposit());
            book_objects.add(bser);
        }

        System.out.println("Successful queries!");
        endSession();
        return book_objects;
    }

    // ========================================   BOOK DETAILS PAGE

    public JSONObject book_details(int book_id) throws JSONException {
        startSession();

        Query query = session.createQuery("from Book where id = :i");
        query.setParameter("i", book_id);

        Book book = (Book) query.uniqueResult();
        User owner = (User) book.getOwner();
        JSONObject book_info = new JSONObject(book.getBook_info());
        JSONObject response = new JSONObject();

        response.put("success","true");
        response.put("book", book.getName());
        response.put("author", book.getAuthor());
        response.put("rent", book.getRent());
        response.put("deposit", book.getDeposit());
        response.put("genre", book.getGenre());
        response.put("times_rented", book.getTimes_rented());
        response.put("available",book.isAvailable());

        response.put("print", book_info.getString("print"));
        response.put("condition", book_info.getString("condition"));
        response.put("year_bought", book_info.getString("year_bought"));
        response.put("review", book_info.getString("review"));

        response.put("owner_id", owner.getId());
        response.put("owner_name", owner.getFull_name());
        response.put("owner_location", owner.getLocation());
        response.put("owner_contact", owner.getContact_no());

        endSession();
        return response;
    }

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
        // todo TMD: remove limit or paginate
        try {

            //NHS
//            Query q = session.createQuery("from Review where book_id = :b order by timestamp desc").setFirstResult(0).setMaxResults(8);
//            q.setParameter("b", book_id);

            //TT
            Query q = session.createQuery("from Review where book = :b order by timestamp desc").setFirstResult(0).setMaxResults(8);
            q.setParameter("b", new Book(book_id));

            List reviews = q.getResultList();
            for (Object review1 : reviews) {
                Review r = (Review) review1;
                JSONObject review = new JSONObject();
                review.put("username", r.getReviewer().getFull_name());
                System.out.println(r.getReviewer().getFull_name());
                review.put("body", r.getBody());
                response.put(review);
            }
        }
        catch (Exception e) {
            System.out.println("No results | " + e.getMessage());
        }
        finally {
            endSession();
        }
        return response;
    }

    // ========================================   MESSAGES

    public JSONArray online(String username) {
        JSONArray response = new JSONArray();
        for (Connection c: Server.clients) {
            if (c.isAlive() && !c.getName().equals(username))
                response.put(c.getName());
        }
        return response;
    }

    public JSONArray get_messages() throws JSONException {
        startSession();

        JSONArray response = new JSONArray();
        // Gets 25 messages from the database
        Query q = session.createQuery("from Message order by timestamp").setFirstResult(0).setMaxResults(100);
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

    public void send_message (int id, String name, String type, String body) throws JSONException {
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
        //return response;
    }

    // ========================================   UTILITY

    private void startSession() {
        // Opening session
        session = Server.sessionFactory.openSession();
        session.beginTransaction();
    }

    private void endSession() {
        // Closing session
        session.getTransaction().commit();
        session.close();
    }

}
