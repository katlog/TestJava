package com.saasovation.identityaccess.domain.model;

import com.saasovation.agilepm.domain.model.IdentifiedValueObject;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import lombok.AccessLevel;
import lombok.Setter;

/**
 * Created by fw on 2019/9/28
 */
public class GroupMember extends IdentifiedValueObject {
    @Setter(AccessLevel.PRIVATE)
    private String name;
    @Setter(AccessLevel.PRIVATE)
    private TenantId tenantId;
    @Setter(AccessLevel.PRIVATE)
    private GroupMemberType type;

    public GroupMember(TenantId aTenantId, String aName, GroupMemberType aType) {
        this();
        this.setName(aName);
        this.setTenantId(aTenantId);
        this.setType(aType);
        this.initialize();
    }

    public GroupMember() {
    }

    private void initialize() {

    }

    //...
}
