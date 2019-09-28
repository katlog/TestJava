package com.saasovation.identityaccess.infrastructure.persistence;

import com.saasovation.collaboration.infrastructure.persistence.SpringHibernateSessionProvider;
import com.saasovation.identityaccess.domain.model.UserRepository;
import com.saasovation.identityaccess.domain.model.identity.TenantId;
import com.saasovation.identityaccess.domain.model.identity.User;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Collection;

/**
 * chapter 12 repository: 管理repository的事务
 */
public class HibernateUserRepository implements UserRepository {

    @Override
    public User userFromAuthenticCredentials(TenantId aTenantId, String aUsername, String encryptedPassword) {
        return null;
    }

    @Override
    public void add(User aUser) {
        try {
            this.session().saveOrUpdate(aUser);
        } catch (ConstraintViolationException e) {
            throw new IllegalStateException("User is not unique.", e);
        }
    }

    @Override
    public User userWithUsername(TenantId tenantId, String aUsername) {
        return null;
    }

    @Override
    public void remove(User aUser) {
        this.session().delete(aUser.person());
        this.session().delete(aUser);
    }
    @Override
    public void removeAll(Collection<User> aUserCollection) {
        for (User instance : aUserCollection) {
            this.session().delete(instance.person());
            this.session().delete(instance);
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
