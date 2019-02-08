package bookabook.server.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user", schema = "bookabook")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "username", unique = true, length = 32, nullable = false)
    private String username;

    @Column(name = "full_name", length = 64)
    private String full_name;

    @Column(name = "password", length = 128)
    private String password;

    @Column(name = "date_of_birth")
    @Temporal(value = TemporalType.DATE)
    private Date date_of_birth;

    @Column(name = "gender", length = 8)
    private String gender;

    @Column(name = "wallet", precision = 10, scale = 2, nullable = false, columnDefinition = "int default 1000")
    private double wallet;

    @Column(name = "books_shared", nullable = false, columnDefinition = "int default 0")
    private int books_shared;

    @Column(name = "books_rented", nullable = false, columnDefinition = "int default 0")
    private int books_rented;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "contact_no", length = 32)
    private String contact_no;

    @Column(name = "location", length = 128)
    private String location;

    @Column(name = "work", length = 64)
    private String work;

    @OneToMany(mappedBy = "owner")
    private Set<Book> books;

    @OneToMany(mappedBy = "renter")
    private Set<Rent> rented_books;

    @OneToMany(mappedBy = "rentee")
    private Set<Rent> rented_out_books;

    @OneToMany(mappedBy = "reviewer")
    private Set<Review> reviews;

    public User () {

    }

    public User (int i) {
        id = i;
    }

    // ================= MAIN FUNCTIONS

    public User (String u) {
        this.username = u;
    }

    public void signup (String f, String u, String p, Date d, String e) {
        this.full_name = f;
        this.username = u;
        this.password = p;
        this.date_of_birth = d;
        this.email = e;
        this.books_rented = 0;
        this.books_shared = 0;
        this.wallet = 1000;
    }

    public void edit_profile (String n, String l, String w, String g,
                              String e, String c) {
        full_name = n;
        location = l;
        work = w;
        gender = g;
        email = e;
        contact_no = c;
    }

    // ================= GETTERS

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this.id;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getGender() {
        return gender;
    }

    public double getWallet() {
        return wallet;
    }

    public int getBooks_shared() {
        return books_shared;
    }

    public int getBooks_rented() {
        return books_rented;
    }

    public String getEmail() {
        return email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public String getLocation() {
        return location;
    }

    public String getWork() {
        System.out.println("WORK IS "+work);
        return work;
    }

    public Set<Book> getBooks() {
        return books;
    }
}