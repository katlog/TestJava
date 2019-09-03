package com.saasovation.collaboration.domain.model;


import com.saasovation.agilepm.domain.model.product.ProductRepository;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemRepository;
import com.saasovation.collaboration.domain.model.calendar.CalendarEntryRepository;
import com.saasovation.collaboration.domain.model.calendar.CalendarRepository;

/**
 * Created by fw on 2019/3/21
 */
public class DomainRegistry {
    public static CalendarRepository calendarRepository() {
        return null;
    }

    public static CalendarEntryRepository calendarEntryRepository() {
        return null;
    }
}
