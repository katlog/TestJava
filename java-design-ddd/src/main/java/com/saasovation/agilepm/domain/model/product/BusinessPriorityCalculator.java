package com.saasovation.agilepm.domain.model.product;

import com.saasovation.agilepm.domain.model.DomainRegistry;
import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.tenant.Tenant;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.supply.DomianService;

/**
 * Created by fw on 2019/3/21
 */
@DomianService
public class BusinessPriorityCalculator {

    public BusinessPriorityCalculator() {
        super();
    }
    public BusinessPriorityTotals businessPriorityTotals(TenantId aTenant, ProductId aProductId) {
        int totalBenefit = 0;
        int totalPenalty = 0;
        int totalCost = 0;
        int totalRisk = 0;
        java.util.Collection<BacklogItem> outstandingBacklogItems =
                DomainRegistry
                        .backlogItemRepository()
                        .allOutstandingProductBacklogItems(aTenant,aProductId);
        for (BacklogItem backlogItem : outstandingBacklogItems) {
            if (backlogItem.hasBusinessPriority()) {
                BusinessPriorityRatings ratings =
                        backlogItem.businessPriority().ratings();
                totalBenefit += ratings.benefit();
                totalPenalty += ratings.penalty();
                totalCost += ratings.cost();
                totalRisk += ratings.risk();
            }
        }
        BusinessPriorityTotals businessPriorityTotals =
                new BusinessPriorityTotals(
                        totalBenefit,
                        totalPenalty,
                        totalBenefit + totalPenalty,
                        totalCost,
                        totalRisk);
        return businessPriorityTotals;
    }

}
