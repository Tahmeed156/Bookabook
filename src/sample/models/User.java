package sample.models;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "contact_no", length = 32)
    private String contact_no;

    @Column(name = "rating", precision = 1, scale = 2)
    private double rating;

    @Column(name = "location", length = 128)
    private String location;

    @Column(name = "work", length = 64)
    private String work;


    public User () {

    }

    public User (String u) {
        this.username = u;
    }

    public void signup(String f, String u, String p, Date d, String e) {
        this.full_name = f;
        this.username = u;
        this.password = p;
        this.date_of_birth = d;
        this.email = e;
    }

    public String showName() {
        return this.username + " | " + this.full_name;
    }

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this.id;
    }

    public String getFull_name() {
        return this.full_name;
    }
}