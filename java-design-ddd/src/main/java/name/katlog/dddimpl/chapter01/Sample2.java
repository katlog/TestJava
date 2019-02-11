package name.katlog.dddimpl.chapter01;

import name.katlog.dddimpl.assist.Transactional;

/**
 * Created by fw on 2019/2/11
 */
public class Sample2 {

    private CustomerDao customerDao;

    @Transactional(rollbackFor = Exception.class)
    public void saveCustomer( String customerId, String customerFirstName, String customerLastName,
                              String streetAddress1, String streetAddress2, String city, String stateOrProvince,
                              String postalCode, String country, String homePhone, String mobilePhone,
                              String primaryEmailAddress, String secondaryEmailAddress) {
        Customer customer = customerDao.readCustomer(customerId);
        if (customer == null) {
            customer = new Customer();
            customer.setCustomerId(customerId);
        }
        if (customerFirstName != null) {
            customer.setCustomerFirstName(customerFirstName);
        }
        if (customerLastName != null) {
            customer.setCustomerLastName(customerLastName);
        }
        if (streetAddress1 != null) {
            customer.setStreetAddress1(streetAddress1);
        }
        if (streetAddress2 != null) {
            customer.setStreetAddress2(streetAddress2);
        }
        if (city != null) {
            customer.setCity(city);
        }
        if (stateOrProvince != null) {
            customer.setStateOrProvince(stateOrProvince);
        }
        if (postalCode != null) {
            customer.setPostalCode(postalCode);
        }
        if (country != null) {
            customer.setCountry(country);
        }
        if (homePhone != null) {
            customer.setHomePhone(homePhone);
        }
        if (mobilePhone != null) {
            customer.setMobilePhone(mobilePhone);
        }
        if (primaryEmailAddress != null) {
            customer.setPrimaryEmailAddress(primaryEmailAddress);
        }
        if (secondaryEmailAddress != null) {
            customer.setSecondaryEmailAddress (secondaryEmailAddress);
        }
        customerDao.saveCustomer(customer);
    }

}
