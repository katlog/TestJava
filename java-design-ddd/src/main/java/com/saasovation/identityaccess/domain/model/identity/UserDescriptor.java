package com.saasovation.identityaccess.domain.model.identity;

import com.saasovation.agilepm.domain.model.tenant.TenantId;

/**
 * Created by fw on 2019/3/21
 */
public class UserDescriptor {
    private String emailAddress;
    private TenantId tenantId;
    private String username;

    public UserDescriptor(TenantId aTenantId, String aUsername, String anEmailAddress) {
        //	...
    }
    //...

}
