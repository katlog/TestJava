package com.saasovation.collaboration.infrastructure;

import com.saasovation.agilepm.domain.model.Entity;
import com.saasovation.collaboration.Warble;

public class WarbleValidator extends Validator {
    private Warble warble;
    public WarbleValidator(Warble aWarble, ValidationNotificationHandler aHandler) {
        super(aHandler);
        this.setWarble(aWarble);
    }

    private void setWarble(Warble aWarble) {
        this.warble = aWarble;
    }

    //	...
    /**  chapter 5 实体  验证整体对象*/
    @Override
    public void validate() {
        if (this.hasWarpedWarbleCondition(this.warble())) {
            this.notificationHandler().handleError("The warble is warped.");
        }
        if (this.hasWackyWarbleState(this.warble())) {
            this.notificationHandler().handleError("The warble has a wacky state.");
        }
        //...
    }

    private boolean hasWackyWarbleState(Warble warble) {
        return false;
    }

    private boolean hasWarpedWarbleCondition(Warble warble) {
        return false;
    }

    private Warble warble() {
        return warble;
    }


}
