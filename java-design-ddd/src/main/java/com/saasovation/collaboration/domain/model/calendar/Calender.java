package com.saasovation.collaboration.domain.model.calendar;

import com.saasovation.collaboration.domain.model.Alarm;
import com.saasovation.collaboration.domain.model.Invitee;
import com.saasovation.collaboration.domain.model.Repetition;
import com.saasovation.collaboration.domain.model.TimeSpan;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.supply.FactoryMethod;

import java.security.acl.Owner;
import java.util.Set;


/**
 * Created by fw on 2019/3/22
 */
public class Calender {

    private Tenant tenant;

    private CalendarId calendarId;

    public CalendarId calendarId(){
        return this.calendarId;
    }
    private Tenant tenant() {
        return this.tenant;
    }


    @FactoryMethod
    public CalendarEntry sheduleCalenarEntry(
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


        return null;
    }



}
