package com.saasovation.identityaccess.domain.model.identity;

import com.saasovation.supply.AggregateRoot;

/**
 * Created by fw on 2019/9/10
 */
@AggregateRoot
public class Role {
    public boolean isInRole(User user, GroupMemberService groupMemberService) {
        return false;
    }
}
