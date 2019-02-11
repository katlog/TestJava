package name.katlog.dddimpl.chapter01;


import name.katlog.dddimpl.assist.Transactional;

/**
 * Created by fw on 2019/2/11
 */
public class Sample1 {

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
        customer.setCustomerFirstName(customerFirstName);
        customer.setCustomerLastName(customerLastName);
        customer.setStreetAddress1(streetAddress1);
        customer.setStreetAddress2(streetAddress2);
        customer.setCity(city);
        customer.setStateOrProvince(stateOrProvince);
        customer.setPostalCode(postalCode);
        customer.setCountry(country);
        customer.setHomePhone(homePhone);
        customer.setMobilePhone(mobilePhone);
        customer.setPrimaryEmailAddress(primaryEmailAddress);
        customer.setSecondaryEmailAddress (secondaryEmailAddress);

        customerDao.saveCustomer(customer);
    }

}
