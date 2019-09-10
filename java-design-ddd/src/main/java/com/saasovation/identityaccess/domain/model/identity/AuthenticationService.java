package com.saasovation.identityaccess.domain.model.identity;

import com.saasovation.supply.DomianService;

/** 该接口和那些与身份相关的聚合（比如Tenant，User和Group)定义在相同的模块（9）中，
 * 因AuthenticationService也是一个与身份相关的概念 */
@DomianService
public interface AuthenticationService {

    public UserDescriptor authenticate(TenantId aTenantId, String aUsername, String aPassword);

}
