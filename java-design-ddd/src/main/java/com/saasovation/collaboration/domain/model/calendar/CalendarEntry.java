package com.saasovation.collaboration.domain.model.calendar;

import com.saasovation.collaboration.domain.model.*;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.identityaccess.domain.model.identity.TenantId;

import java.util.Set;

/**
 * Created by fw on 2019/3/20
 */
public class CalendarEntry {
    private Tenant tenant;
    private CalendarId calendarId;
    private CalendarEntryId aCalendarEntryId;
    private Owner anOwner;
    private String aSubject;
    private String aDescription;
    private TimeSpan aTimeSpan;
    private Alarm anAlarm;
    private Repetition aRepetition;
    private String aLocation;
    private Set<Invitee> anInvitee;


    public CalendarEntry(Tenant tenant,
                         CalendarId calendarId,
                         CalendarEntryId aCalendarEntryId,
                         Owner anOwner,
                         String aSubject,
                         String aDescription,
                         TimeSpan aTimeSpan,
                         Alarm anAlarm,
                         Repetition aRepetition,
                         String aLocation,
                         Set<Invitee> anInvitees) {
        this.tenant = tenant;
        this.calendarId = calendarId;
        this.aCalendarEntryId = aCalendarEntryId;
        this.anOwner = anOwner;
        this.aSubject = aSubject;
        this.aDescription = aDescription;
        this.aTimeSpan = aTimeSpan;
        this.anAlarm = anAlarm;
        this.aRepetition = aRepetition;
        this.aLocation = aLocation;
        this.anInvitee = anInvitees;
    }
}
