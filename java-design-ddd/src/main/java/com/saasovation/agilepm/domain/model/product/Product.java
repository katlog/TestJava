package com.saasovation.agilepm.domain.model.product;

import com.saasovation.agilepm.domain.model.product.backlogitem.BacklogItem;
import com.saasovation.agilepm.domain.model.product.release.Release;
import com.saasovation.agilepm.domain.model.product.sprint.Sprint;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import com.saasovation.supply.AggregateRoot;
import com.saasovation.supply.FactoryMethod;
import name.katlog.dddimpl.chapter07_domainservice.Tenant;

/**
 * Created by fw on 2019/3/20
 */
@AggregateRoot
public class Product {
    public TenantId tenantId() {
        return null;
    }

    public ProductId productId() {
        return null;
    }

    public Tenant tenant() {
        return null;
    }

    @FactoryMethod
    public BacklogItem planBacklogItem(){
        return null;
    }

    @FactoryMethod
    public Release sehduleRelease(){
        return null;
    }

    @FactoryMethod
    public Sprint sheduleSprint(){
        return null;
    }


}
