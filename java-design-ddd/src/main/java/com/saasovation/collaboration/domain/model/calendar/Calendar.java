package com.saasovation.collaboration.domain.model.calendar;

import com.saasovation.collaboration.domain.model.*;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.supply.FactoryMethod;

import java.util.Set;
import java.util.TreeSet;


/**
 * Created by fw on 2019/3/22
 */
public class Calendar {

    private Tenant tenant;

    private CalendarId calendarId;

    public CalendarId calendarId(){
        return this.calendarId;
    }
    private Tenant tenant() {
        return this.tenant;
    }


    @FactoryMethod
    protected  CalendarEntry scheduleCalendarEntry(
            CalendarEntryId aCalendarEntryId,
            Owner anOwner,
            String aSubject,
            String aDescription,
            TimeSpan aTimeSpan,
            Alarm anAlarm,
            Repetition aRepetition,
            String aLocation,
            Set<Invitee> anInvitees) {

        CalendarEntry calendarEntry =
                new CalendarEntry(
                        this.tenant(),
                        this.calendarId(),
                        aCalendarEntryId,
                        anOwner,
                        aSubject,
                        aDescription,
                        aTimeSpan,
                        anAlarm,
                        aRepetition,
                        aLocation,
                        anInvitees);
        DomainEventPublisher
                .instance()
                .publish(new CalendarEntryScheduled());

        return calendarEntry;
    }


}
