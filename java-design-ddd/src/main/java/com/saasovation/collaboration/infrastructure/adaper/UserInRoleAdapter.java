package com.saasovation.collaboration.infrastructure.adaper;

import com.saasovation.collaboration.domain.model.Author;
import com.saasovation.collaboration.domain.model.collaborator.Collaborator;
import com.saasovation.collaboration.infrastructure.services.CollaboratorTranslator;
import com.saasovation.identityaccess.domain.model.identity.Tenant;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

/**
 * Created by fw on 2019/8/31
 */
public class UserInRoleAdapter {
    private UserInRoleAdapter() {
    }


    public static UserInRoleAdapter newInstance() {
        return new UserInRoleAdapter();
    }

    public <T extends Collaborator> T toCollaborator(
            Tenant aTenant,
            String anIdentity,
            String aRoleName,
            Class<T> aCollaboratorClass) {

        T collaborator = null;
        try {
            ClientRequest request =
                    this.buildRequest(aTenant, anIdentity, aRoleName);
            ClientResponse<String> response =
                    request.get(String.class);
            if (response.getStatus() == 200) {
                collaborator =
                        new CollaboratorTranslator()
                                .toCollaboratorFromRepresentation(
                                        response.getEntity(),
                                        aCollaboratorClass);
            } else if (response.getStatus() != 204) {
                throw new IllegalStateException(
                        "There was a problem requesting the user: "
                                + anIdentity
                                + " in role: "
                                + aRoleName
                                + " with resulting status: "
                                + response.getStatus());
            }
        } catch (Throwable t) {
            throw new IllegalStateException(
                    "Failed because: " + t.getMessage(), t);
        }
        return collaborator;
    }

    private ClientRequest buildRequest(Tenant aTenant, String anIdentity, String aRoleName) {
        return new ClientRequest("");
    }
}
