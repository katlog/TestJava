package name.katlog.ddd.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by fw on 2019/4/17
 */
public class BaseEvent<D> {

    @Getter
    private Long id;
    @Setter(AccessLevel.PROTECTED)
    @Getter
    private Date occurredTime;

    @Getter
    private D source;

    private BaseEvent() {
    }

    public BaseEvent(D source) {
        this.source = source;
    }
}
