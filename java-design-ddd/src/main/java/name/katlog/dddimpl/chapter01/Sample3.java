package name.katlog.dddimpl.chapter01;

import name.katlog.dddimpl.assist.Transactional;

/**
 * Created by fw on 2019/2/11
 */
public class Sample3 {

    private CustomerRepository customerRepository;

    @Transactional(rollbackFor = Exception.class)
    public void changeCustomerPersonalName(String customerId, String customerFirstName, String customerLastName) {
        Customer1 customer = customerRepository.customerOfId(customerId);
        if (customer == null) {
            throw new IllegalStateException("Customer does not exist.");
        }
        customer.changePersonalName(customerFirstName, customerLastName);
    }


}
