package sample;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private int id;
    private String username;
    public static int count = 1;

    public User () {
        username = "";
        id = count++;
    }

    public User (String u) {
        username = u;
        id = count++;
    }

}