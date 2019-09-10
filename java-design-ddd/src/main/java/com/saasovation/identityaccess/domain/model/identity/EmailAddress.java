package com.saasovation.identityaccess.domain.model.identity;

import com.saasovation.supply.ValueObject;

/**
 * Created by fw on 2019/3/21
 */
@ValueObject
public final class EmailAddress {

    private String address;
    public EmailAddress(String anAddress) {
        super();
        this.setAddress(anAddress);
    }
    //...
    /**  chapter 5 实体 验证属性  setAddress4个前置条件*/
    private void setAddress(String anAddress) {
        if (anAddress == null) {
            throw new IllegalArgumentException("The address may not be set to null.");
        }
        if (anAddress.length() == 0) {
            throw new IllegalArgumentException("The email address is required.");
        }
        if (anAddress.length() > 100) {
            throw new IllegalArgumentException("Email address must be 100 characters or less.");
        }
        if (!java.util.regex.Pattern.matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", anAddress)) {
            throw new IllegalArgumentException("Email address and/or its format is invalid.");
        }
        this.address = anAddress;
    }
}
