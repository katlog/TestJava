package com.saasovation.agilepm.domain.model.product;

import org.junit.Test;

import java.text.NumberFormat;

import static org.junit.Assert.*;

public class BusinessPriorityTest {
    public BusinessPriorityTest() {
        super();
    }
    //...
    private NumberFormat oneDecimal() {
        return this.decimal(1);
    }
    private NumberFormat twoDecimals() {
        return this.decimal(2);
    }
    private NumberFormat decimal(int aNumberOfDecimals) {
        NumberFormat fmt = NumberFormat.getInstance();
        fmt.setMinimumFractionDigits(aNumberOfDecimals);
        fmt.setMaximumFractionDigits(aNumberOfDecimals);
        return fmt;
    }


    /** 06 value object: 测试值对象 */
    @Test
    public void testCostPercentageCalculation() throws Exception{
        BusinessPriority businessPriority =
                new BusinessPriority(new BusinessPriorityRatings(2, 4, 1, 1));
        BusinessPriority businessPriorityCopy = new BusinessPriority(businessPriority);

        assertEquals(businessPriority, businessPriorityCopy);

        BusinessPriorityTotals totals =new BusinessPriorityTotals(53, 49, 53 + 49, 37, 33); //274
        float cost = businessPriority.costPercentage(totals);

        assertEquals(this.oneDecimal().format(cost), "2.7");
        assertEquals(businessPriority, businessPriorityCopy);
    }

    @Test
    public void testPriorityCalculation() throws Exception {
        BusinessPriority businessPriority =  new BusinessPriority(new BusinessPriorityRatings(2, 4, 1, 1));
        BusinessPriority businessPriorityCopy = new BusinessPriority(businessPriority);
        assertEquals(businessPriorityCopy, businessPriority);

        BusinessPriorityTotals totals = new BusinessPriorityTotals(53, 49, 53 + 49, 37, 33);
        float calculatedPriority = businessPriority.priority(totals);

        assertEquals("1.03",this.twoDecimals().format(calculatedPriority));
        assertEquals(businessPriority, businessPriorityCopy);
    }
    @Test
    public void testTotalValueCalculation() throws Exception {
        BusinessPriority businessPriority =new BusinessPriority(new BusinessPriorityRatings(2, 4, 1, 1));
        BusinessPriority businessPriorityCopy = new BusinessPriority(businessPriority);
        assertEquals(businessPriority, businessPriorityCopy);

        float totalValue = businessPriority.totalValue();
        assertEquals("6.0",this.oneDecimal().format(totalValue));
        assertEquals(businessPriority, businessPriorityCopy);
    }
    @Test
    public void testValuePercentageCalculation() throws Exception {
        BusinessPriority businessPriority = new BusinessPriority(new BusinessPriorityRatings(2, 4, 1, 1));
        BusinessPriority businessPriorityCopy = new BusinessPriority(businessPriority);
        assertEquals(businessPriority, businessPriorityCopy);

        BusinessPriorityTotals totals = new BusinessPriorityTotals(53, 49, 53 + 49, 37, 33);
        float valuePercentage = businessPriority.valuePercentage(totals);
        assertEquals("5.9", this.oneDecimal().format(valuePercentage));
        assertEquals(businessPriorityCopy, businessPriority);
    }
}