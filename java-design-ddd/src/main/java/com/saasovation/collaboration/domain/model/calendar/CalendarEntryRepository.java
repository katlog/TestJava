package com.saasovation.collaboration.domain.model.calendar;


import com.saasovation.collaboration.domain.model.TimeSpan;
import com.saasovation.identityaccess.domain.model.identity.Tenant;

import java.util.Collection;

public interface CalendarEntryRepository {

    public void add(CalendarEntry aCalendarEntry);

    public void addAll(Collection<CalendarEntry> aCalendarEntryCollection);

    public void remove(CalendarEntry aCalendarEntry);

    public void removeAll(Collection<CalendarEntry> aCalendarEntryCollection);
    //...

    /** 资源库 查找方法 */
    public CalendarEntry calendarEntryOfId(Tenant aTenant, CalendarEntryId aCalendarEntryId);

    public Collection<CalendarEntry> calendarEntriesOfCalendar(Tenant aTenant,CalendarId aCalendarId);

    public Collection<CalendarEntry> overlappingCalendarEntries(Tenant aTenant, CalendarId aCalendarId,
                                                                TimeSpan aTimeSpan);

    /** 资源库额外行为 */
    int size();

    CalendarEntryId nextIdentity();

}
