import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.util.List;


class ListEvents {

    List<Event> listEvents(com.google.api.services.calendar.Calendar service, String calendarId) throws IOException {


        //TODO: AUTH!!!!
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list(calendarId)
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        List<Event> items = events.getItems();
        return items;

    }

    public String addEvent(Calendar service, String calendarId, Event event) throws IOException {


        String ret = "";

        DateTime now = new DateTime(System.currentTimeMillis());
        event = service.events().insert(calendarId, event).execute();
        return ret = "Event created: %s\n" + event.getHtmlLink();


    }
}
