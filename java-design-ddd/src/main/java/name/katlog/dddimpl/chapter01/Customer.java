package name.katlog.dddimpl.chapter01;

import lombok.Data;

/**
 * Created by fw on 2019/2/11
 */
@Data
public class Customer {

    private String  customerId;
    private String customerFirstName;
    private String customerLastName;
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String stateOrProvince;
    private String postalCode;
    private String country;
    private String homePhone;
    private String mobilePhone;
    private String primaryEmailAddress;
    private String secondaryEmailAddress;

}
