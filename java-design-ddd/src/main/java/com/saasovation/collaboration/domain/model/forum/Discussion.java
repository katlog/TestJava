package com.saasovation.collaboration.domain.model.forum;

import com.saasovation.collaboration.domain.model.Author;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.supply.FactoryMethod;

/**
 * Created by fw on 2019/3/22
 */
public class Discussion {

    public Discussion(Tenant tenant,
                      ForumId forumId,
                      DiscussionId aDiscussionId,
                      Author anAuthor,
                      String aSubject) {

    }

    @FactoryMethod
    public void post(){
    }
}
