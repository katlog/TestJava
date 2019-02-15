package name.katlog.dddimpl.chapter04;

import java.util.Collection;

/**
 * Created by fw on 2019/2/15
 */
public class Sample1 {

    //package com.saasovation.agilepm.infrastructure.persistence;
    //import com.saasovation.agilepm.domain.model.product.*;
    public class HibernateBacklogItemRepository
            implements BacklogItemRepository {
         //...

        @Override
        @SuppressWarnings("unchecked")
        public Collection<BacklogItem> allBacklogItemsComittedTo(
                Tenant aTenant, SprintId aSprintId) {
            Query query =
                    this.session().createQuery(
                            "from -BacklogItem as _obj_ "
                                    + "where _obj_.tenant = ? and _obj_.sprintId = ?");
            query.setParameter(0, aTenant);
            query.setParameter(1, aSprintId);
            return (Collection<BacklogItem>) query.list();
        }

        public Session session(){
            return new Session();
        }

            //...
    }

    class Tenant{

    }

    interface BacklogItemRepository{
        public Collection<BacklogItem> allBacklogItemsComittedTo(Tenant aTenant, SprintId aSprintId);

    }

    class Query{
        public void setParameter(int i, Object params) {
        }

        public Collection list() {
            return null;
        }
    }

    class Session{

        public Query createQuery(String s) {
            return null;
        }
    }
}
