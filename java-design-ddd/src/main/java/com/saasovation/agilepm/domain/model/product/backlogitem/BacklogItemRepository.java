package com.saasovation.agilepm.domain.model.product.backlogitem;


import com.saasovation.agilepm.domain.model.product.ProductId;
import com.saasovation.agilepm.domain.model.tenant.Tenant;
import com.saasovation.agilepm.domain.model.tenant.TenantId;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by fw on 2019/2/14
 */
public class BacklogItemRepository {
    public BacklogItem backlogItemOfId(TenantId tenantId, BacklogItemId backlogItemId) {
        return null;
    }

    public void add(BacklogItem plannedBacklogItem) {

    }

    public Collection<BacklogItem> allOutstandingProductBacklogItems(TenantId aTenant, ProductId aProductId) {
        return Collections.emptyList();
    }
}
