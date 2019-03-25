package com.saasovation.collaboration.domain.model.calendar;


import name.katlog.dddimpl.chapter07_domainservice.Tenant;

import java.util.Collection;

public interface CalendarEntryRepository {

    public void add(CalendarEntry aCalendarEntry);

    public void addAll(Collection<CalendarEntry> aCalendarEntryCollection);

    public void remove(CalendarEntry aCalendarEntry);

    public void removeAll(Collection<CalendarEntry> aCalendarEntryCollection);
    //...

    public CalendarEntry calendarEntryOfId(Tenant aTenant,CalendarEntryId aCalendarEntryId);

    public Collection<CalendarEntry> calendarEntriesOfCalendar(Tenant aTenant,CalendarId aCalendarId);

    public Collection<CalendarEntry> overlappingCalendarEntries(Tenant aTenant, CalendarId aCalendarId/*,
                                                                TimeSpan aTimeSpan*/);

    int size();
}
