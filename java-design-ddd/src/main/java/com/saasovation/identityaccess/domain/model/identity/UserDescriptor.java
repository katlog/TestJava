package com.saasovation.identityaccess.domain.model.identity;


/**
 * Created by fw on 2019/3/21
 */
public class UserDescriptor {
    private String emailAddress;
    private TenantId tenantId;
    private String username;

    public UserDescriptor(TenantId aTenantId, String aUsername, String anEmailAddress) {
        this.tenantId = aTenantId;
        this.username = aUsername;
        this.emailAddress = anEmailAddress;
    }

    public EmailAddress emailAddress() {
        return null;
    }

    public String username() {
        return username;
    }

    public TenantId tenantId() {
        return tenantId;
    }
    //...

}
