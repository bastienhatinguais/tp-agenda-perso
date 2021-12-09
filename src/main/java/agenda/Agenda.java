package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    private ArrayList<Event> events = new ArrayList<Event>();

    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        if (e == null)
            throw new IllegalArgumentException("L'évenement ne peut pas être nul.");
        events.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
        ArrayList<Event> eventsInDay = new ArrayList<>();
        for (Event event : events) {
            if (event.isInDay(day))
                eventsInDay.add(event);
        }
        return eventsInDay;
    }
}
