package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class AgendaTest {
    Agenda agenda;

    // November 1st, 2020
    LocalDate nov_1_2020 = LocalDate.of(2020, 11, 1);

    // January 5, 2021
    LocalDate jan_5_2021 = LocalDate.of(2021, 1, 5);

    // November 1st, 2020, 22:30
    LocalDateTime nov_1__2020_22_30 = LocalDateTime.of(2020, 11, 1, 22, 30);

    // November 2st, 2020, 00:30
    LocalDateTime nov_2__2020_00_30 = LocalDateTime.of(2020, 11, 2, 00, 30);

    // 120 minutes
    Duration min_120 = Duration.ofMinutes(120);

    // A simple event
    // November 1st, 2020, 22:30, 120 minutes
    Event simple = new Event("Simple event", nov_1__2020_22_30, min_120);

    // A Weekly Repetitive event ending at a given date
    RepetitiveEvent fixedTermination = new FixedTerminationEvent("Fixed termination weekly", nov_1__2020_22_30, min_120,
            ChronoUnit.WEEKS, jan_5_2021);

    // A Weekly Repetitive event ending after a give number of occurrrences
    RepetitiveEvent fixedRepetitions = new FixedTerminationEvent("Fixed termination weekly", nov_1__2020_22_30, min_120,
            ChronoUnit.WEEKS, 10);

    // A daily repetitive event, never ending
    // November 1st, 2020, 22:30, 120 minutes
    RepetitiveEvent neverEnding = new RepetitiveEvent("Never Ending", nov_1__2020_22_30, min_120, ChronoUnit.DAYS);

    @BeforeEach
    public void setUp() {
        agenda = new Agenda();
        agenda.addEvent(simple);
        agenda.addEvent(fixedTermination);
        agenda.addEvent(fixedRepetitions);
        agenda.addEvent(neverEnding);
    }

    @Test
    public void testMultipleEventsInDay() {
        assertEquals(4, agenda.eventsInDay(nov_1_2020).size(), "Il y a 4 événements ce jour là");
        assertTrue(agenda.eventsInDay(nov_1_2020).contains(neverEnding));
    }

    @Test
    public void testFindTitle() {
        assertEquals(2,
                agenda.findByTitle("Fixed termination weekly").size(),
                "Le nombre d'évènements trouvés n'est pas correct.");
        assertTrue(agenda.findByTitle("Fixed termination weekly").contains(fixedTermination));
        assertTrue(agenda.findByTitle("Fixed termination weekly").contains(fixedRepetitions));
    }

    @Test
    public void isFreeFor() {
        LocalDateTime nov_1__2020_22_00 = LocalDateTime.of(2020, 11, 1, 22, 00);
        LocalDateTime nov_1__2020_23_00 = LocalDateTime.of(2020, 11, 1, 23, 00);
        LocalDateTime nov_1__2020_17_00 = LocalDateTime.of(2020, 11, 1, 17, 00);
        Event simple1 = new Event("avant", nov_1__2020_22_00, min_120);
        Event simple2 = new Event("apres", nov_1__2020_23_00, min_120);
        Event simple3 = new Event("ok", nov_1__2020_17_00, min_120);
        assertFalse(agenda.isFreeFor(simple1));
        assertFalse(agenda.isFreeFor(simple2));
        assertTrue(agenda.isFreeFor(simple3));
    }

}
