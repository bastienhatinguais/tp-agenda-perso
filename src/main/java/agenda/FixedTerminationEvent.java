package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {
    private LocalDate terminationDate;
    private long numberOfOccurrences;

    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title                the title of this event
     * @param start                the start time of this event
     * @param duration             the duration of this event
     * @param frequency            one of :
     *                             <UL>
     *                             <LI>ChronoUnit.DAYS for daily repetitions</LI>
     *                             <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     *                             <LI>ChronoUnit.MONTHS for monthly
     *                             repetitions</LI>
     *                             </UL>
     * @param terminationInclusive the date when this event ends
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency,
            LocalDate terminationInclusive) {
        super(title, start, duration, frequency);
        this.numberOfOccurrences = frequency.between(start.toLocalDate(), terminationInclusive) + 1;
        this.terminationDate = terminationInclusive;
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title               the title of this event
     * @param start               the start time of this event
     * @param duration            the duration of this event
     * @param frequency           one of :
     *                            <UL>
     *                            <LI>ChronoUnit.DAYS for daily repetitions</LI>
     *                            <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     *                            <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     *                            </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency,
            long numberOfOccurrences) {
        super(title, start, duration, frequency);
        this.terminationDate = start.plus(numberOfOccurrences - 1, frequency).toLocalDate();
        this.numberOfOccurrences = numberOfOccurrences;
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
        if (aDay.isAfter(terminationDate))
            return false;
        else {
            return super.isInDay(aDay);
        }
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        return this.terminationDate;
    }

    public long getNumberOfOccurrences() {
        return this.numberOfOccurrences;
    }

}
