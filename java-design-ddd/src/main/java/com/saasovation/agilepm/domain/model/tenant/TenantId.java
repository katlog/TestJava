package com.saasovation.agilepm.domain.model.tenant;

import com.saasovation.supply.ValueObject;

/**
 * value object
 * Created by fw on 2019/3/20
 */
@ValueObject
public class TenantId {
    private String tenanId;

    public TenantId(String aTenantId) {
        tenanId = aTenantId;
    }

    public String id() {
        return tenanId;
    }

}
