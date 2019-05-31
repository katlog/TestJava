package com.saasovation.collaboration.infrastructure.persistence;

import com.saasovation.collaboration.domain.model.calendar.CalendarEntry;
import com.saasovation.collaboration.domain.model.calendar.CalendarEntryId;
import com.saasovation.collaboration.domain.model.calendar.CalendarEntryRepository;
import com.saasovation.collaboration.domain.model.calendar.CalendarId;
import name.katlog.dddimpl.chapter07_domainservice.Tenant;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by fw on 2019/3/20
 */
public class HibernateCalendarEntryRepository implements CalendarEntryRepository {

    public HibernateCalendarEntryRepository() {
        super();
    }

    public CalendarEntryId nextIdentity() {
        return new CalendarEntryId(UUID.randomUUID().toString().toUpperCase());
    }

    @Override
    public void add(CalendarEntry aCalendarEntry) {
        try {
            this.session().saveOrUpdate(aCalendarEntry);
        } catch (ConstraintViolationException e) {
            throw new IllegalStateException("CalendarEntry is not unique.", e);
        }
    }

    // TODO: 2019/3/20 安放在哪
    private Session session() {
        return null;
    }

    @Override
    public void addAll(Collection<CalendarEntry> aCalendarEntryCollection) {
        try {
            for (CalendarEntry instance : aCalendarEntryCollection) {
                this.session().saveOrUpdate(instance);
            }
        } catch (ConstraintViolationException e) {
            throw new IllegalStateException("CalendarEntry is not unique.", e);
        }
    }

    @Override
    public void remove(CalendarEntry aCalendarEntry) {
        this.session().delete(aCalendarEntry);
    }

    @Override
    public void removeAll(Collection<CalendarEntry> aCalendarEntryCollection) {
        for (CalendarEntry instance : aCalendarEntryCollection) {
            this.session().delete(instance);
        }
    }

    @Override
    public CalendarEntry calendarEntryOfId(Tenant aTenant, CalendarEntryId aCalendarEntryId) {
        Query query =
                this.session().createQuery(
                        "from CalendarEntry as _obj_ " +
                                "where _obj_.tenant = ? and _obj_.calendarEntryId = ?");
        query.setParameter(0, aTenant);
        query.setParameter(1, aCalendarEntryId);
        return (CalendarEntry) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")

    public Collection<CalendarEntry> calendarEntriesOfCalendar(Tenant aTenant, CalendarId aCalendarId) {

        Query query =
                this.session().createQuery(
                        "from CalendarEntry as _obj_ " +
                                "where _obj_.tenant = ? and _obj_.calendarId = ?");
        query.setParameter(0, aTenant);
        query.setParameter(1, aCalendarId);
        return (Collection<CalendarEntry>) query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<CalendarEntry> overlappingCalendarEntries(Tenant aTenant, CalendarId aCalendarId) {

        Query query =
                this.session().createQuery(
                        "from CalendarEntry as _obj_ " +
                                "where _obj_.tenant = :tenant and " +
                                "_obj_.calendarId = :calendarId and " +
                                "((_obj_.repetition.timeSpan.begins between " +
                                ":tsb and :tse) or " +
                                " (_obj_.repetition.timeSpan.ends between " +
                                ":tsb and :tse))");
        query.setParameter("tenant", aTenant);
        query.setParameter("calendarId", aCalendarId);
        // query.setParameter("tsb", aTimeSpan.begins(), Hibernate.DATE);
        // query.setParameter("tse", aTimeSpan.ends(), Hibernate.DATE);
        return (Collection<CalendarEntry>) query.list();
    }


    @Override
    public int size() {
        Query query =
                this.session().createQuery(
                        "select count(*) from CalendarEntry");
        int size = ((Integer) query.uniqueResult()).intValue();
        return size;
    }
}
