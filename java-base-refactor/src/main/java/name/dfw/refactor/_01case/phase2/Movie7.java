package name.dfw.refactor._01case.phase2;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by dell on 2018/4/12
 */
public class Movie7 {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title; //名称
    private int _priceCode; //价格（代号）

    public Movie7(String title, int priceCode) {
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

    double getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()) {
            case Movie7.REGULAR:
                result += 2;
                if (daysRented > 2) result += (daysRented - 2) * 1.5;
                break;
            case Movie7.NEW_RELEASE:
                result += daysRented * 3;
                break;
            case Movie7.CHILDRENS:
                result += 1.5;
                if (daysRented > 3) result += (daysRented - 3) * 1.5;
                break;
        }
        return result;
    }

    int getFrequentRenterPoints(int daysRented) {

        if ((getPriceCode() == Movie7.NEW_RELEASE) && daysRented > 1)
            return 2;
        return 1;
    }
}

class Rental7 {
    Movie7 _movie; //影片
    private int _daysRented; //租期

    public Rental7(Movie7 movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie7 getMovie() {
        return _movie;
    }

    double getCharge() {
        return _movie.getCharge(_daysRented);
    }

    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
    }
}

class Customer7 {
    private String _name; //姓名
    private Vector _rentals = new Vector(); //租借记。

    public Customer7(String name) {
        _name = name;
    }

    public void addRental(Rental7 arg) {
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

            Rental7 each = (Rental7) rentals.nextElement();
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
            Rental7 each = (Rental7) rentals.nextElement();
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
            Rental7 rental7 = (Rental7) elements.nextElement();
            result += rental7.getCharge();
        }

        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;

        Enumeration elements = _rentals.elements();
        while (elements.hasMoreElements()) {
            Rental7 rental7 = (Rental7) elements.nextElement();
            result += rental7.getFrequentRenterPoints();
        }

        return result;
    }

    private double amountFor(Rental7 aRental) {
        return aRental.getCharge();
    }

}