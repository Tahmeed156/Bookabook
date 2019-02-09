package bookabook.server.models;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "rent", schema = "bookabook")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "rent_date")
    @Temporal(value = TemporalType.DATE)
    private Date rent_date;

    @Column(name = "return_date")
    @Temporal(value = TemporalType.DATE)
    private Date renturn_date;

    @ManyToOne
    @JoinColumn(name = "renter_id", nullable = false)
    private User renter;

    @ManyToOne
    @JoinColumn(name = "rentee_id", nullable = false)
    private User rentee;

    public Rent (int b, int w, int rtr, int rte) {
        rent_date = new Date();
        renter = new User(rtr);
        rentee = new User(rte);
        book = new Book(b);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(rent_date);
        calendar.add(Calendar.DAY_OF_YEAR, w*7);
        renturn_date = calendar.getTime();
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Date getRent_date() {
        return rent_date;
    }

    public Date getRenturn_date() {
        return renturn_date;
    }

    public User getRenter() {
        return renter;
    }

    public User getRentee() {
        return rentee;
    }
}