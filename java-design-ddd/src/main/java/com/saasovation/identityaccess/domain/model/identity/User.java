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


    /**  chapter 5  实体  创建实体 */
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

    public boolean isEnabled() {
        return false;
    }

    public UserDescriptor userDescriptor() {
        return new UserDescriptor(tenantId,username, "");
    }

    public TenantId tenantId() {
        return tenantId;
    }

    public String username() {
        return username;
    }

    public Person person() {
        return person;
    }

    public String password() {
        return password;
    }
}
