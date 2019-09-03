package com.saasovation.collaboration.domain.model.forum;

import com.saasovation.collaboration.domain.model.Author;
import com.saasovation.collaboration.domain.model.calendar.CalendarEntryRepository;
import org.junit.Test;

import static org.junit.Assert.*;

public class ForumTest {

    private DiscussionRepository discussionRepository;

    @Test
    public void startDiscussion() {

        Forum agilePmForum = new Forum();

        Discussion discussion = agilePmForum.startDiscussion(
                this.discussionRepository.nextIdentity(),
                new Author("jdoe", "John Doe", "jdoe@saasovation.com"),
                "Dealing with Aggregate Concurrency Issues");
        assertNotNull(discussion);
		//...
        this.discussionRepository.add(discussion);

    }
}