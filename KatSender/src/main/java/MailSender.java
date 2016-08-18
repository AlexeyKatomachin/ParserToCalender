/**
 * Created by ${Alexey} on ${09.08.2016}.
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class MailSender {
    private MimeMessage message;

    public void MailSender(JSONWork jsonWork) throws MessagingException, IOException {

        final String username = jsonWork.jsonReadSettings("username");
        final String password = jsonWork.jsonReadSettings("password");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", jsonWork.jsonReadSettings("port"));
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.host", jsonWork.jsonReadSettings("host"));
        props.put("mail.smtp.port", jsonWork.jsonReadSettings("port"));
        props.put("mail.smtp.user", jsonWork.jsonReadSettings("mailFromSend"));

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        //session.setDebug(true); to show session


        message = new MimeMessage(session);
        try {

            message.setFrom(new InternetAddress(jsonWork.jsonReadSettings("mailFromSend")));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(jsonWork.jsonReadSettings("mailToSend")));
        } catch (MessagingException e) {

            e.printStackTrace();

        }
    }

    public void MessageError(String data) {
        try {
            message.setText(data);
            message.setSubject("ERROR");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void MessageIsSend(String data, String head) {
        try {
            message.setText("Next Game:  " + data);
            message.setSubject(head);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
