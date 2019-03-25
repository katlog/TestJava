package com.saasovation.collaboration.infrastructure.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by fw on 2019/3/20
 */
public class SpringHibernateSessionProvider {
    private static final ThreadLocal<Session> sessionHolder = new ThreadLocal<Session>();
    private SessionFactory sessionFactory;
    public SpringHibernateSessionProvider() {
        super();
    }
    public Session session() {
        Session threadBoundsession = sessionHolder.get();
        if (threadBoundsession == null) {
            threadBoundsession = sessionFactory.openSession();
            sessionHolder.set(threadBoundsession);
        }
        return threadBoundsession;
    }
    public void setSessionFactory(SessionFactory aSessionFactory) {
        this.sessionFactory = aSessionFactory;
    }

}
