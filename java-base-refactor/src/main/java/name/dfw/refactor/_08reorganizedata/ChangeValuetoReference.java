package name.dfw.refactor._08reorganizedata;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by fw on 2018/4/24
 * 将实值对象改为引用对象
 */
public class ChangeValuetoReference {

    static class Before {
        class Customer {
            public Customer(String name) {
                _name = name;
            }

            public String getName() {
                return _name;
            }

            private final String _name;
        }

        class Order {
            public Order(String customerName) {
                _customer = new Customer(customerName);
            }

            public void setCustomer(String customerName) {
                _customer = new Customer(customerName);
            }

            public String getCustomerName() {
                return _customer.getName();
            }

            private Customer _customer;
        }

        static class Client {
            private static int numberOfOrdersFor(Collection orders, String customer) {
                int result = 0;
                Iterator iter = orders.iterator();
                while (iter.hasNext()) {
                    Order each = (Order) iter.next();
                    if (each.getCustomerName().equals(customer)) result++;
                }
                return result;
            }
        }
    }

    static class Refactor {
       static class Customer {

            public static Customer create (String name) {
                return new Customer(name);
            }

            private Customer(String name) {
                _name = name;
            }

            public String getName() {
                return _name;
            }

            private final String _name;

           private static Dictionary _instances = new Hashtable();

           static void loadCustomers() {
               new Customer("Lemon Car Hire").store();
               new Customer("Associated Coffee Machines").store();
               new Customer("Bilston Gasworks").store();
           }

           private void store() {
               _instances.put(this.getName(), this);
           }

       }

        class Order {
            public Order(String customerName) {
                _customer =  Customer.create(customerName);
            }

            public void setCustomer(String customerName) {
                _customer = Customer.create(customerName);
            }

            public String getCustomerName() {
                return _customer.getName();
            }

            private Customer _customer;
        }

        static class Client {
            private static int numberOfOrdersFor(Collection orders, String customer) {
                int result = 0;
                Iterator iter = orders.iterator();
                while (iter.hasNext()) {
                    Order each = (Order) iter.next();
                    if (each.getCustomerName().equals(customer)) result++;
                }
                return result;
            }
        }
    }

    static class Refactor1 {
       static class Customer {

            public static Customer create (String name) {
                return (Customer) _instances.get(name);
            }

            private Customer(String name) {
                _name = name;
            }

            public String getName() {
                return _name;
            }

            private final String _name;

           private static Dictionary _instances = new Hashtable();

           static void loadCustomers() {
               new Customer("Lemon Car Hire").store();
               new Customer("Associated Coffee Machines").store();
               new Customer("Bilston Gasworks").store();
           }

           private void store() {
               _instances.put(this.getName(), this);
           }

       }

        class Order {
            public Order(String customerName) {
                _customer =  Customer.create(customerName);
            }

            public void setCustomer(String customerName) {
                _customer = Customer.create(customerName);
            }

            public String getCustomerName() {
                return _customer.getName();
            }

            private Customer _customer;
        }

        static class Client {
            private static int numberOfOrdersFor(Collection orders, String customer) {
                int result = 0;
                Iterator iter = orders.iterator();
                while (iter.hasNext()) {
                    Order each = (Order) iter.next();
                    if (each.getCustomerName().equals(customer)) result++;
                }
                return result;
            }
        }
    }

}
