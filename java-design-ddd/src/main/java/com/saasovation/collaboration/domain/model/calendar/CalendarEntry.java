package com.saasovation.collaboration.domain.model.calendar;

import com.saasovation.collaboration.domain.model.*;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.identityaccess.domain.model.identity.TenantId;

import java.util.Set;

/**
 * Created by fw on 2019/3/20
 */
public class CalendarEntry {

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

    }
}
