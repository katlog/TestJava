package com.saasovation.identityaccess.domain.model.identity;

/**
 * Created by fw on 2019/3/21
 */
public class Person {
    private TenantId tenantId;
    private ContractInfomation contractInfomation;

    public void setTenantId(TenantId tenantId) {
        this.tenantId = tenantId;
    }

    public EmailAddress emailAddress() {
        return null;
    }
}
