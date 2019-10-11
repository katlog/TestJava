package com.saasovation.identityaccess.application;

import com.saasovation.common.media.OvationsMediaType;
import com.saasovation.identityaccess.domain.model.DomainRegistry;
import com.saasovation.identityaccess.domain.model.identity.User;
import com.saasovation.supply.Adapter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 * UserResource类是RESTful HTTP端口的适配器
 * @author difengwei
 */
@Adapter
@Path("/tenants/{tenantId}/users")
public class UserResource {

    //...

    /** chapter 13 集成限界上下文  通过REST资源集成限界上下文*/
    @GET
    @Path("{username}/inRole/{role}")
    @Produces({ OvationsMediaType.ID_OVATION_TYPE })
    public Response getUserInRole(
            @PathParam("tenantId") String aTenantId,
            @PathParam("username") String aUsername,
            @PathParam("role") String aRoleName) {

        // 消费方可通过以下方式发出请求 : GET/tenants/{tenantId}/users/{username}/inRole/{role}

        //			HTTP/1.1 200 OK
        // 			Content-Type: application/vnd.saasovation.idovation+json
        // 			...
        // 			{
        // 				"role":"Author","username":"zoe",
        // 				"tenantId":"A94A8298-43B8-4DA0-9917-13FFF9E116ED",
        // 				"firstName":"Zoe","lastName":"Doe",
        // 				"emailAddress":"zoe@saasovation.com"
        // }


        Response response = null;
        User user = null;
        try {
            user = this.accessService().userInRole(aTenantId, aUsername, aRoleName);
        } catch (Exception e) {
            // fall through
        }
        if (user != null) {
            response = this.userInRoleResponse(user, aRoleName);
        } else {
            response = Response.noContent().build();
        }
        return response;
    }

    private Response userInRoleResponse(User user, String aRoleName) {
        return null;
    }

    private AccessService accessService() {
        return DomainRegistry.accessService();
    }
    //...
}
