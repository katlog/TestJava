package com.saasovation.collaboration.domain.model.calendar;

import com.saasovation.collaboration.domain.model.Alarm;
import com.saasovation.collaboration.domain.model.Invitee;
import com.saasovation.collaboration.domain.model.Repetition;
import com.saasovation.collaboration.domain.model.TimeSpan;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.identityaccess.domain.model.identity.TenantId;

import java.security.acl.Owner;
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
