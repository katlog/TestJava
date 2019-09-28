package com.saasovation.agilepm.domain.model.product;

import com.saasovation.supply.ValueObject;

/**
 * Created by fw on 2019/9/9
 */
@ValueObject
public class BusinessPriorityRatings {

    private float benefit;
    private float penalty;
    private float cost;
    private float risk;

    public BusinessPriorityRatings(float benefit, float penalty, float cost, float risk) {
        this.benefit = benefit;
        this.penalty = penalty;
        this.cost = cost;
        this.risk = risk;
    }
    public float benefit() {
        return benefit;
    }

    public float penalty() {
        return penalty;
    }

    public float cost() {
        return cost;
    }

    public float risk() {
        return risk;
    }


}
