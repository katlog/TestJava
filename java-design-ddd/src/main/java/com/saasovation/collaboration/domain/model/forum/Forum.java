package com.saasovation.collaboration.domain.model.forum;

import com.saasovation.collaboration.domain.model.Author;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import com.saasovation.supply.FactoryMethod;

/**
 * Created by fw on 2019/3/22
 */
public class Forum {

    private boolean isClosed;

    private Tenant tenant;

    private ForumId forumId;

    public Tenant tenant(){
        return this.tenant;
    }

    public ForumId forumId(){
        return this.forumId;
    }

    @FactoryMethod
    public Discussion startDiscussion(DiscussionId aDiscussionId,
                                        Author anAuthor,
                                        String aSubject){
        if (this.isClosed()) {
            throw new IllegalStateException("Forum is closed.");
        }
        Discussion discussion = new Discussion(
                this.tenant(),
                this.forumId(),
                aDiscussionId,
                anAuthor,
                aSubject);

        return discussion;
    }

    private boolean isClosed() {
        return this.isClosed;
    }
}
