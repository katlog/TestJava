package com.saasovation.agilepm.domain.model;

import java.io.Serializable;

/**
 * Created by fw on 2019/8/26
 */
public class IdentifiedDomainObject implements Serializable {
    private long id = -1;
    public IdentifiedDomainObject() {
        super();
    }
    protected long id() {
        return this.id;
    }
    protected void setId(long anId) {
        this.id = anId;
    }
}
