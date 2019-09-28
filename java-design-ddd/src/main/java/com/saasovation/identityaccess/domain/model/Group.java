package com.saasovation.identityaccess.domain.model;

import com.saasovation.agilepm.domain.model.Entity;
import com.saasovation.agilepm.domain.model.tenant.TenantId;
import lombok.AccessLevel;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by fw on 2019/9/28
 */
public class Group extends Entity {
    @Setter(AccessLevel.PRIVATE)
    private String description;
    @Setter(AccessLevel.PRIVATE)
    private Set<GroupMember> groupMembers;
    @Setter(AccessLevel.PRIVATE)
    private String name;
    @Setter(AccessLevel.PRIVATE)
    private TenantId tenantId;

    public Group(TenantId aTenantId,String aName,String aDescription) {
        this();
        this.setDescription(aDescription);
        this.setName(aName);
        this.setTenantId(aTenantId);
        this.initialize();
    }

    private void initialize() {

    }

    //...
    protected Group() {
        super();
        this.setGroupMembers(new HashSet<GroupMember>(0));
    }
    public void replaceMembers(Set<GroupMember> aReplacementMembers) {
        this.groupMembers().clear();
        this.setGroupMembers(aReplacementMembers);
    }

    //...


    public Set<GroupMember> groupMembers() {
        return groupMembers;
    }
}
