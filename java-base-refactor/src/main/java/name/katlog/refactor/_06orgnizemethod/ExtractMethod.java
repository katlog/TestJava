package name.katlog.refactor._06orgnizemethod;

import java.util.Enumeration;

/**
 * Created by fw on 2018/4/19
 * 提炼函数
 */

interface Elements{
    Enumeration elements();
}

class Order {
    private int amount;

    public int getAmount() {
        return amount;
    }
}

public class ExtractMethod {

    Elements _orders;

    String _name;

    private class BeforeRefactor {

        void printOwing() {
            Enumeration e = _orders.elements();
            double outstanding = 0.0;

            // print banner
            System.out.println("**************************");
            System.out.println("***** Customer Owes ******");
            System.out.println("**************************");

            // calculate outstanding
            while (e.hasMoreElements()) {
                Order each = (Order) e.nextElement();
                outstanding += each.getAmount();
            }
            //print detailsSystem.out.println ("name:" + _name);
            System.out.println("amount" + outstanding);
        }
    }

    /**  无局部变量替换 */
    private class NoLocalVariableRefator {

        void printOwing() {
            Enumeration e = _orders.elements();
            double outstanding = 0.0;
            printBanner();

            // calculate outstanding
            while (e.hasMoreElements()) {
                Order each = (Order) e.nextElement();
                outstanding += each.getAmount();
            }
            //print detailsSystem.out.println ("name:" + _name);
            System.out.println("amount" + outstanding);
        }

        /** 无局部变量（No Local Variables）*/
        void printBanner() {
            // print banner
            System.out.println("**************************");
            System.out.println("***** Customer Owes ******");
            System.out.println("**************************");
        }
    }

    /**  有局部变量替换 */
    class UsingLocalVariableRefactor{
        void printOwing() {
            Enumeration e = _orders.elements();
            double outstanding = 0.0;
            printBanner();
            // calculate outstanding
            while (e.hasMoreElements()) {
                Order each = (Order) e.nextElement();
                outstanding += each.getAmount();
            }
            printDetails(outstanding);
        }
        /**  有局部变量 */
        void printDetails (double outstanding) {
            System.out.println ("name:" + _name);
            System.out.println ("amount" + outstanding);
        }
        void printBanner() {
            // print banner
            System.out.println("**************************");
            System.out.println("***** Customer Owes ******");
            System.out.println("**************************");
        }
    }

    /**  有局部变量再赋值替换 */
    class ReassigningALocalVariableRefactor{

        void printOwing() {
            printBanner();
            double outstanding = getOutstanding();
            printDetails(outstanding);
        }
        void printDetails (double outstanding) {
            System.out.println ("name:" + _name);
            System.out.println ("amount" + outstanding);
        }
        void printBanner() {
            // print banner
            System.out.println("**************************");
            System.out.println("***** Customer Owes ******");
            System.out.println("**************************");
        }
        double getOutstanding() {
            Enumeration e = _orders.elements();
            double outstanding = 0.0;
            while (e.hasMoreElements()) {
                Order each = (Order) e.nextElement();
                outstanding += each.getAmount();
            }
            return outstanding;
        }
    }

    /**  有局部变量 且代码对局部变量进行了其他处理 再赋值 */
    class ReassigningALocalVariableWhileInitializeBefore{

        void printOwing(double previousAmout) {
            printBanner();
            Enumeration e = _orders.elements();
            double outstanding = previousAmout * 1.2;
            while (e.hasMoreElements()) {
                Order each = (Order) e.nextElement();
                outstanding += each.getAmount();
            }
            printDetails(outstanding);
        }
        void printDetails (double outstanding) {
            System.out.println ("name:" + _name);
            System.out.println ("amount" + outstanding);
        }
        void printBanner() {
            // print banner
            System.out.println("**************************");
            System.out.println("***** Customer Owes ******");
            System.out.println("**************************");
        }
    }

    class ReassigningALocalVariableWhileInitializeRefactor{

        void printOwing(double previousAmout) {
            double outstanding = previousAmout * 1.2;
            printBanner();
            outstanding = getOutstanding(outstanding);
            printDetails(outstanding);
        }

        void printOwing1(double previousAmount) {
            printBanner();
            double outstanding = getOutstanding(previousAmount * 1.2);
            printDetails(outstanding);
        }

        private double getOutstanding(double outstanding) {
            double result = outstanding;
            Enumeration e = _orders.elements();
            while (e.hasMoreElements()) {
                Order each = (Order) e.nextElement();
                result += each.getAmount();
            }
            return result;
        }

        void printDetails (double outstanding) {
            System.out.println ("name:" + _name);
            System.out.println ("amount" + outstanding);
        }
        void printBanner() {
            // print banner
            System.out.println("**************************");
            System.out.println("***** Customer Owes ******");
            System.out.println("**************************");
        }
    }
}
