package com.saasovation.identityaccess.domain.model.identity;

/**
 * Created by fw on 2019/3/21
 */
public class User {
    private String username;
    private String password;
    private TenantId tenantId;
    private Person person;

    private User() {
    }

    public void changePassword(){

    }

    public void changePersonalName(){

    }

    public void changePersonalContractInfomation(){}


    protected User(TenantId aTenantId, String aUsername,
                   String aPassword, Person aPerson) {
        this();
        this.setPassword(aPassword);
        this.setPerson(aPerson);
        this.setTenantId(aTenantId);
        this.setUsername(aUsername);
        this.initialize();
    }

    private void initialize() {
    }

    protected void setPassword(String aPassword) {
        if (aPassword == null) {
            throw new IllegalArgumentException( "The password may not be set to null.");
        }
        this.password = aPassword;
    }
    protected void setPerson(Person aPerson) {
        if (aPerson == null) {
            throw new IllegalArgumentException(
                    "The person may not be set to null.");
        }
        this.person = aPerson;
    }
    protected void setTenantId(TenantId aTenantId) {
        if (aTenantId == null) {
            throw new IllegalArgumentException("The tenantId may not be set to null.");
        }
        this.tenantId = aTenantId;
    }
    protected void setUsername(String aUsername) {
        if (this.username != null) {
            throw new IllegalStateException("The username may not be changed.");
        }
        if (aUsername == null) {
            throw new IllegalArgumentException("The username may not be set to null.");
        }
        this.username = aUsername;
    }
}
