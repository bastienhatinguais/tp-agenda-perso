package agenda;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {

    private ChronoUnit frequency;
    private ArrayList<LocalDate> lesExceptions = new ArrayList<>();

    /**
     * Constructs a repetitive event
     *
     * @param title     the title of this event
     * @param start     the start of this event
     * @param duration  myDuration in seconds
     * @param frequency one of :
     *                  <UL>
     *                  <LI>ChronoUnit.DAYS for daily repetitions</LI>
     *                  <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     *                  <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     *                  </UL>
     */
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        lesExceptions.add(date);
    }

    public boolean isInDay(LocalDate aDay) {
        LocalDate theEnd = this.getStart().plus(myDuration).toLocalDate();
        if (aDay.isBefore(myStart.toLocalDate()))
            return false;

        if (lesExceptions.contains(aDay))
            return false;

        boolean isInDay = false;
        switch (frequency) {
            case DAYS:
                isInDay = true;
                break;
            case WEEKS:
                int startDayOfWeek = myStart.getDayOfWeek().getValue();
                int theEndDayOfWeek = theEnd.getDayOfWeek().getValue();
                if (theEndDayOfWeek < startDayOfWeek)
                    theEndDayOfWeek += 7;
                int aDayOfWeek = aDay.getDayOfWeek().getValue();
                if (startDayOfWeek <= aDayOfWeek && theEndDayOfWeek >= aDayOfWeek)
                    isInDay = true;
                else
                    isInDay = false;
                break;
            case MONTHS:
                int startDayOfMonth = myStart.getDayOfMonth();
                int theEndDayOfMonth = theEnd.getDayOfMonth();
                if (theEndDayOfMonth < startDayOfMonth)
                    theEndDayOfMonth += 31;
                int aDayOfMonth = aDay.getDayOfMonth();
                if (startDayOfMonth <= aDayOfMonth && theEndDayOfMonth >= aDayOfMonth)
                    isInDay = true;
                else
                    isInDay = false;
                break;
            default:
                isInDay = false;

        }
        return isInDay;
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return frequency;
    }

}
