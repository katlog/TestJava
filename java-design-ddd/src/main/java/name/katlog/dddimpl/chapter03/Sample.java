package name.katlog.dddimpl.chapter03;

import name.katlog.dddimpl.Entity;

import java.io.Serializable;

/**
 * Created by fw on 2019/2/14
 */
public class Sample {

    public enum DiscussionAvailability {
        ADD_ON_NOT_ENABLED, NOT_REQUESTED, REQUESTED, READY;
    }
    public final class Discussion implements Serializable {
        private DiscussionAvailability availability;
        private DiscussionDescriptor descriptor;
        //...
    }
    public class Product extends Entity {
        //...
        private Discussion discussion;
        //...
    }
}
