package com.saasovation.collaboration.domain.model.calendar;

import com.saasovation.collaboration.DomainEventSubscriber;
import com.saasovation.collaboration.domain.model.*;
import org.junit.Test;


import java.util.TreeSet;

import static org.junit.Assert.*;

public class CalenderTest {

    private CalendarEntry calendarEntry;
    private CalendarEntryId calendarEntryId;


    @Test
    public void sheduleCalenarEntry() {
    }

    public void testCreateCalendarEntry() throws Exception {
        Calendar calendar = this.calendarFixture();
        DomainRegistry.calendarRepository().add(calendar);
        DomainEventPublisher
                .instance()
                .subscribe(
                        new DomainEventSubscriber<CalendarEntryScheduled>() {
                            public void handleEvent( CalendarEntryScheduled aDomainEvent) {
                                calendarEntryId = aDomainEvent.calendarEntryId();
                            }
                            public Class<CalendarEntryScheduled> subscribedToEventType() {
                                return CalendarEntryScheduled.class;
                            }
                        });
        calendarEntry =
                calendar.scheduleCalendarEntry(
                        DomainRegistry
                                .calendarEntryRepository()
                                .nextIdentity(),
                        new Owner(
                                "jdoe",
                                "John Doe",
                                "jdoe@lastnamedoe.org"),
                        "Sprint Planning",
                        "Plan sprint for first half of April 2012.",
                        this.tomorrowOneHourTimeSpanFixture(),
                        this.oneHourBeforeAlarmFixture(),
                        this.weeklyRepetitionFixture(),
                        "Team Room",
                        new TreeSet<Invitee>());

        DomainRegistry.calendarEntryRepository().add(calendarEntry);
        assertNotNull(calendarEntryId);
        assertNotNull(calendarEntry);
        //...
    }

    private Repetition weeklyRepetitionFixture() {
        return null;
    }

    private Alarm oneHourBeforeAlarmFixture() {
        return null;
    }

    private TimeSpan tomorrowOneHourTimeSpanFixture() {
        return null;
    }

    private Calendar calendarFixture() {
        return null;
    }
}