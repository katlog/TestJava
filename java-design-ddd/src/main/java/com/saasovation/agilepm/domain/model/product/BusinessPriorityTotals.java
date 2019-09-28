package com.saasovation.agilepm.domain.model.product;

/**
 * Created by fw on 2019/3/21
 */
public class BusinessPriorityTotals {

     private float totalBenefit;
     private float totalPenalty;
     private float totalValue;
     private float totalCost;
     private float totalRisk;


    public BusinessPriorityTotals(float totalBenefit, float totalPenalty, float totalValue, float totalCost, float totalRisk) {
        this.totalBenefit = totalBenefit;
        this.totalPenalty = totalPenalty;
        this.totalValue = totalValue;
        this.totalCost = totalCost;
        this.totalRisk = totalRisk;
    }

    public float totalBenefit() {
        return totalBenefit;
    }

    public float totalPenalty() {
        return totalPenalty;
    }

    public float totalValue() {
        return totalValue;
    }

    public float totalCost() {
        return totalCost;
    }

    public float totalRisk() {
        return totalRisk;
    }
}
