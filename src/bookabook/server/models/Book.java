package bookabook.server.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "book", schema = "bookabook")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "author", length = 32, nullable = false)

    private String author;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "rent", scale = 4, precision = 2) // Rent per week
    private double rent;

    @Column(name = "deposit", scale = 4, precision = 2) // Deposit (initial)
    private double deposit;

    @Column(name = "timestamp")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "genre")
    private String genre;

    @Column(name = "book_info", columnDefinition = "TEXT")
    private String book_info;

    @Column(name = "available")
    private boolean available = true;


    public Book () {

    }

    public Book (String n, String a) {
        name = n;
        author = a;
    }

    public void rent (String n, String a, double r, double d, String i, String g) {
        name = n;
        author = a;
        rent = r;
        deposit = d;
        book_info = i;
        genre = g;
        timestamp = new Date();
    }

    public User getOwner() {
        return owner;
    }

    public Book(String name, String author, User owner, double rent, double deposit, Date timestamp, String genre, String book_info, boolean available) {
        this.name = name;
        this.author = author;
        this.owner = owner;
        this.rent = rent;
        this.deposit = deposit;
        this.timestamp = timestamp;
        this.genre = genre;
        this.book_info = book_info;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public double getRent() {
        return rent;
    }

    public double getDeposit() {
        return deposit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getGenre() {
        return genre;
    }

    public String getBook_info() {
        return book_info;
    }

    public boolean isAvailable() {
        return available;
    }
}
