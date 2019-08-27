package com.saasovation.agilepm.domain.model.product.backlogitem;

import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.supply.AggregateRoot;

/**
 * Created by fw on 2019/8/26
 */
@AggregateRoot
public class Task {
    private String description;
    private int hoursRemaining;
    private String name;
    private String volunteer;

    private BacklogItemId backlogItemId;
    private ProductId productId;
}
