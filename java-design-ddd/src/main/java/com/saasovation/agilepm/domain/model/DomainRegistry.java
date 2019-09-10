package com.saasovation.agilepm.domain.model;


import com.saasovation.agilepm.domain.model.product.BusinessPriorityCalculator;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItemRepository;

/**
 * Created by fw on 2019/3/21
 */
public class DomainRegistry {
    public static BacklogItemRepository backlogItemRepository() {
        return null;
    }

    public static BusinessPriorityCalculator businessPriorityCalculator() {
        return null;
    }
}
