package com.saasovation.identityaccess.application.eventStore;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Created by fw on 2019/3/19
 */
@NoArgsConstructor
public class StoredEvent {

    @Setter(AccessLevel.PRIVATE)
    private String eventBody;
    /** 数据库自动产生 */
    private long eventId;
    @Setter(AccessLevel.PRIVATE)
    private Date occurredOn;
    @Setter(AccessLevel.PRIVATE)
    private String typeName;

    public StoredEvent(
            String aTypeName,
            Date anOccurredOn,
            String anEventBody) {

        this();
        this.setEventBody(anEventBody);
        this.setOccurredOn(anOccurredOn);
        this.setTypeName(aTypeName);
    }
    //...


}
