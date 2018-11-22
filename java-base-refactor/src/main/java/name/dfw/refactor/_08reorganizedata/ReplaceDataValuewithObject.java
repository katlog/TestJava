package name.dfw.refactor._08reorganizedata;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by fw on 2018/4/24
 * 以对象取代数据值
 */
public class ReplaceDataValuewithObject {

    static class BeforeRefactor {
        class Order {
            public Order(String customer) {
                _customer = customer;
            }


            public String getCustomer() {
                return _customer;
            }

            public void setCustomer(String arg) {
                _customer = arg;
            }

            private String _customer;
        }

        static class Client {
            private static int numberOfOrdersFor(Collection orders, String customer) {
                int result = 0;
                Iterator iter = orders.iterator();
                while (iter.hasNext()) {
                    Order each = (Order) iter.next();
                    if (each.getCustomer().equals(customer)) result++;
                }
                return result;
            }
        }
    }

    static class Refactor {

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
            public Order(String customer) {
                _customer = new Customer(customer);
            }

            public String getCustomer() {
                return _customer.getName();
            }

            private Customer _customer;

            public void setCustomer(String arg) {
                _customer = new Customer(arg);
            }

            public String getCustomerName() {
                return _customer.getName();
            }
        }

        static class Client {
            private static int numberOfOrdersFor(Collection orders, String customer) {
                int result = 0;
                Iterator iter = orders.iterator();
                while (iter.hasNext()) {
                    Order each = (Order) iter.next();
                    if (each.getCustomer().equals(customer)) result++;
                }
                return result;
            }
        }
    }

    static class Refactor1 {

        class Customer {
            public Customer(String name) {
                _name = name;
            }

            public String getName() {
                return _name;
            }

            private final String _name;
        }

        // 参数名更改
        class Order {

            public String getCustomer() {
                return _customer.getName();
            }

            public Order(String customerName) {
                _customer = new Customer(customerName);
            }

            public void setCustomer(String customerName) {
                _customer = new Customer(customerName);
            }

            private Customer _customer;


            public String getCustomerName() {
                return _customer.getName();
            }
        }

        static class Client {
            private static int numberOfOrdersFor(Collection orders, String customer) {
                int result = 0;
                Iterator iter = orders.iterator();
                while (iter.hasNext()) {
                    Order each = (Order) iter.next();
                    if (each.getCustomer().equals(customer)) result++;
                }
                return result;
            }
        }
    }


}
