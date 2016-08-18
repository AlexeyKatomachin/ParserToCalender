import javax.mail.MessagingException;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

public class Updating {
    private String timeToDay;
    private MailSender mailSender = new MailSender();
    private ToCalender toCalender = new ToCalender();
    private HTMLParser htmlParser = new HTMLParser();

    void updating(JSONWork jsonWork) throws IOException, MessagingException, InterruptedException {

        while (true) {
            Date date = new Date();
            timeToDay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toString().substring(11, 16);
            System.out.println(timeToDay);
            if (timeToDay.equals(jsonWork.jsonReadSettings("timeOfRequest"))) {

                mailSender.MailSender(jsonWork);
                if (htmlParser.HTMLParser(mailSender, jsonWork)) {
                    try {
                        toCalender.ToCalender(jsonWork);
                    } catch (Exception e) {
                        mailSender.MessageError("Error! \n Something went wrong, Parser or/and CalenderSender are broken");
                    }
                }
            }
        }
    }
}
