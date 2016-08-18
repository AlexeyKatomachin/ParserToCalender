import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.IOException;

public class ToCalender {
    public void ToCalender(JSONWork jsonWork) throws IOException {
        ListEvents listEvents = new ListEvents();
        Event event = new Event()
                .setSummary(jsonWork.jsonReadEvent("players"))
                .setLocation(jsonWork.jsonReadEvent("place"))
                .setDescription(jsonWork.jsonReadEvent("description"));

        DateTime startDateTime = new DateTime(jsonWork.jsonReadEvent("dataOfGameForCalender"));
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Minsk");
        event.setStart(start);

        DateTime endDateTime = new DateTime(jsonWork.jsonReadEvent("dataOfGameForCalender"));
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Minsk");
        event.setEnd(end);

        listEvents.addEvent(Auth.getCalendarService(), jsonWork.jsonReadSettings("calendarId"), event);
    }
}
