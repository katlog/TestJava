package name.katlog.dddimpl.chapter02;

import name.katlog.dddimpl.Entity;

/**
 * Created by fw on 2019/2/13
 */
public class Forum extends Entity {

    private UserRepository userRepository;

    // ...
    public Discussion startDiscussion(
            String aUsername, String aSubject) {
        if (this.isClosed()) {
            throw new IllegalStateException("Forum is closed.");
        }
        User user = userRepository.userFor(this.tenantId(), aUsername);
        if (!user.hasPermissionTo(Permission.Forum.StartDiscussion)) {
            throw new IllegalStateException(
                    "User may not start forum discussion.");
        }

        String authorUser = user.username();
        String authorName = user.person().name().asFormattedName();
        String authorEmailAddress = user.person().emailAddress();
        Discussion discussion = new Discussion(
                this.tenant(), this.forumId(),
                DomainRegistry.discussionRepository().nextIdentity(),authorUser, authorName, authorEmailAddress,
                aSubject);
        return discussion;
    }

    private long tenantId() {
        return 0;
    }

    private boolean isClosed() {
        return false;
    }
}
