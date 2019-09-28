package com.saasovation.agilepm.domain.model.product;

import com.saasovation.supply.ValueObject;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * Created by fw on 2019/3/26
 */
@ValueObject
@EqualsAndHashCode
public class BusinessPriority {

    private BusinessPriorityRatings ratings;

    protected BusinessPriority() {
        super();
    }

    public BusinessPriority(BusinessPriorityRatings businessPriorityRatings) {
        setRatings(businessPriorityRatings);
    }

    private void setRatings(BusinessPriorityRatings aRatings) {
        if (aRatings == null) {
            throw new IllegalArgumentException("The ratings are required.");
        }
        this.ratings = aRatings;
    }

    public BusinessPriority(BusinessPriority businessPriority) {
            this(businessPriority.ratings());
    }

    public BusinessPriorityRatings ratings() {
        return ratings;
    }


    public float costPercentage(BusinessPriorityTotals aTotals) {
        return (float) 100 * this.ratings().cost() /
                aTotals.totalCost();
    }
    public float priority(BusinessPriorityTotals aTotals) {
        return
                this.valuePercentage(aTotals) /
                        (this.costPercentage(aTotals) +
                                this.riskPercentage(aTotals));
    }
    public float riskPercentage(BusinessPriorityTotals aTotals) {
        return (float) 100 * this.ratings().risk() /
                aTotals.totalRisk();
    }
    public float totalValue() {
        return this.ratings().benefit() + this.ratings().penalty();
    }
    public float valuePercentage(BusinessPriorityTotals aTotals) {
        return (float) 100 * this.totalValue() / aTotals.totalValue();
    }


}
