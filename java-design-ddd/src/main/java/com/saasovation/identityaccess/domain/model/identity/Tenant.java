package com.saasovation.identityaccess.domain.model.identity;

import com.saasovation.supply.FactoryMethod;

/**
 * Created by fw on 2019/3/21
 */
public class Tenant {

    private boolean active;

    private TenantId tenantId;


    private TenantId tenantId() {
        return this.tenantId;
    }


    public void activate() {
        active = true;
    }
    public void deactivate() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }


    @FactoryMethod
    /** 注册用户 */
    public User registerUser(String aUsername,String aPassword,Person aPerson){
        aPerson.setTenantId(this.tenantId());
        User user = new User(this.tenantId(),aUsername,aPassword,aPerson);
        return user;
    }

    @FactoryMethod
    /** 提供注册邀请 */
    public void offerRegistrationInvitation(){

    }
    @FactoryMethod
    public void provisionGroup() {
    }

    @FactoryMethod
    public void provisionRole() {
    }

    public String id() {
        return null;
    }
}
