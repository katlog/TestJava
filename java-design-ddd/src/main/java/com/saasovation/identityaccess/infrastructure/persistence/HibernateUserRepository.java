package com.saasovation.identityaccess.infrastructure.persistence;

import com.saasovation.collaboration.infrastructure.persistence.SpringHibernateSessionProvider;
import com.saasovation.identityaccess.domain.model.identity.User;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Created by fw on 2019/3/21
 * 管理repository的事务
 */
public class HibernateUserRepository {

    public void add(User aUser) {
        try {
            this.session().saveOrUpdate(aUser);
        } catch (ConstraintViolationException e) {
            throw new IllegalStateException("User is not unique.", e);
        }
    }
    //...
    private SpringHibernateSessionProvider sessionProvider;

    public void setSessionProvider(
            SpringHibernateSessionProvider aSessionProvider) {
        this.sessionProvider = aSessionProvider;
    }
    private org.hibernate.Session session() {
        return this.sessionProvider.session();
    }
}
