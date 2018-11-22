package name.dfw.refactor._01case.phase1;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by dell on 2018/4/12
 */
public class Movie6 {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title; //名称
    private int _priceCode; //价格（代号）

    public Movie6(String title, int priceCode) {
        _title = title;
        _priceCode = priceCode;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    public void setPriceCode(int arg) {
        _priceCode = arg;
    }

    public String getTitle() {
        return _title;
    }
}

class Rental6 {
    private Movie6 _movie; //影片
    private int _daysRented; //租期

    public Rental6(Movie6 movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie6 getMovie() {
        return _movie;
    }

    double getCharge() {
        double result = 0;
        switch (getMovie().getPriceCode()) {
            case Movie6.REGULAR:
                result += 2;
                if (getDaysRented() > 2) result += (getDaysRented() - 2) * 1.5;
                break;
            case Movie6.NEW_RELEASE:
                result += getDaysRented() * 3;
                break;
            case Movie6.CHILDRENS:
                result += 1.5;
                if (getDaysRented() > 3) result += (getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }

    int getFrequentRenterPoints() {

        if ((getMovie().getPriceCode() == Movie6.NEW_RELEASE) && getDaysRented() > 1)
            return 2;
        return 1;
    }
}

class Customer6 {
    private String _name; //姓名
    private Vector _rentals = new Vector(); //租借记。

    public Customer6(String name) {
        _name = name;
    }

    public void addRental(Rental6 arg) {
        _rentals.addElement(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {

            Rental6 each = (Rental6) rentals.nextElement();
            frequentRenterPoints += each.getFrequentRenterPoints();

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        Enumeration rentals = _rentals.elements();
        String result = "<H1>Rentals for <EM>" + getName() + "</EM></ H1><P>\n";
        while (rentals.hasMoreElements()) {
            Rental6 each = (Rental6) rentals.nextElement();
            //show figures for each rental
            result += each.getMovie().getTitle() + ": " + String.valueOf(each.getCharge()) + "<BR>\n";
        }
        //add footer lines
        result += "<P>You owe <EM>" + String.valueOf(getTotalCharge()) + "</EM><P>\n";
        result += "On this rental you earned <EM>" + String.valueOf(getTotalFrequentRenterPoints()) + "</EM> frequent renter points<P>";
        return result;
    }

    public double getTotalCharge() {
        double result = 0;

        Enumeration elements = _rentals.elements();
        while (elements.hasMoreElements()) {
            Rental6 rental6 = (Rental6) elements.nextElement();
            result += rental6.getCharge();
        }

        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;

        Enumeration elements = _rentals.elements();
        while (elements.hasMoreElements()) {
            Rental6 rental6 = (Rental6) elements.nextElement();
            result += rental6.getFrequentRenterPoints();
        }

        return result;
    }

    private double amountFor(Rental6 aRental) {
        return aRental.getCharge();
    }

}