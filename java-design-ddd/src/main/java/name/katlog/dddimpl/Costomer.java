package name.katlog.dddimpl;

import lombok.Data;

/**
 * Created by fw on 2019/1/16
 */
@Data
public class Costomer {

    private String customerId;

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
    private String secondaryEmailAddress ;


}
