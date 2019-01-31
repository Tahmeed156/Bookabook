package bookabook.server.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message", schema = "bookabook")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "sender_id", nullable = false)
    private int sender_id;

    @Column(name = "sender_name", nullable = false)
    private String sender_name;

    @Column(name = "type", length = 32, nullable = false)
    private String type;

    @Column(name = "body", length = 256)
    private String body;

    @Column(name = "timestamp")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date timestamp;

    public Message (int s_id, String s_name, String b) {
        sender_id = s_id;
        sender_name = s_name;
        type = "text";
        body = b;
        timestamp = new Date();
    }

    public Message (int s_id, String s_name, String t, String b) {
        sender_id = s_id;
        sender_name = s_name;
        type = t;
        body = b;
        timestamp = new Date();
    }



}
