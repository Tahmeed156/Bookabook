package bookabook.server.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review", schema = "bookabook")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "reviewer_id", nullable = false)
    private User reviewer;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(name = "body", length = 256)
    private String body;

    @Column(name = "timestamp")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;

    public Review (int reviewer_id, int book_id, String b) {
        reviewer = new User(reviewer_id);
        book = new Book(book_id);
        body = b;
        timestamp = new Date();
    }

    public int getId() {
        return id;
    }

    public User getReviewer() {
        return reviewer;
    }

    public Book getBook() {
        return book;
    }

    public String getBody() {
        return body;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
