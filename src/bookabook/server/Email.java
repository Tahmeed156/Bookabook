package bookabook.server;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

class Email {

    private static Message mailSender = null;

    public static void initializeMailSender() {
        try {
            final String mailID = "bookabook.devs@gmail.com";
            final String password = "tahmeednagib";

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.from.alias", "Bookabook");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(mailID, password);
                        }
                    }
            );

            mailSender = new MimeMessage(session);
            mailSender.setFrom(new InternetAddress(mailID, "Bookabook", "UTF8"));

        } catch (AddressException e) {
            System.out.println("Email account is not valid ");
            e.printStackTrace();
        } catch (MessagingException e) {
            System.out.println("technical problem");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    synchronized public static void sendReturnMail(String to, String username, String bookname, String sharer_name) {

        String subject = "Bookabook - book return";
        String text = "Dear " + username + ",\nYou were supposed to return the book - " + bookname + " to "
                + sharer_name + ".Please return the book as soon as possible";
        Thread t = new Thread(new MailThread(mailSender, to, subject, text));
        t.start();

    }
}


class MailThread implements Runnable {
    private Message message;
    private String to;

    MailThread(Message m, String to, String subject, String text) {
        message = m;
        try {
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            this.to = to;

            ((MimeMessage) message).setSubject(subject);
            ((MimeMessage) message).setText(text);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            Transport.send(message);
            System.out.println("Email is sent to " + to);
        } catch (MessagingException e) {
            System.out.println("Technical problem !");
            e.printStackTrace();

        }


    }


}