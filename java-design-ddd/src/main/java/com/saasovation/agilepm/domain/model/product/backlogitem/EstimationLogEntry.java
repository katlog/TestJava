package com.saasovation.agilepm.domain.model.product.backlogitem;

import com.saasovation.supply.ValueObject;

/**
 * Created by fw on 2019/8/26
 */
@ValueObject
public class EstimationLogEntry {

    private String description;
    private int hoursRemaining;
    private String name;
    private String volunteer;

}
