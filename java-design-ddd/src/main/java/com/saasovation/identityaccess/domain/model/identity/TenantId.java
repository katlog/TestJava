package com.saasovation.identityaccess.domain.model.identity;

/**
 * Created by fw on 2019/3/21
 */
public class TenantId {
    private String tenanId;

    public TenantId(String aTenantId) {
        tenanId = aTenantId;
    }

    public String id() {
        return tenanId;
    }
}
