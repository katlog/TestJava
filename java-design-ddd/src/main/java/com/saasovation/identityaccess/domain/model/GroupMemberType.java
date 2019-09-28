package com.saasovation.identityaccess.domain.model;

/**
 * Created by fw on 2019/9/28
 */
public enum GroupMemberType {
    /**  */
    GROUP {
        @Override
        public boolean isGroup() {
            return true;
        }
    },
    USER {
        @Override
        public boolean isUser() {
            return true;
        }
    };
    public boolean isGroup() {
        return false;
    }
    public boolean isUser() {
        return false;
    }
}
