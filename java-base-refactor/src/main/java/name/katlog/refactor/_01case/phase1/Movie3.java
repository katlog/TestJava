package name.katlog.refactor._01case.phase1;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by dell on 2018/4/12
 */
public class Movie3 {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title; //名称
    private int _priceCode; //价格（代号）

    public Movie3(String title, int priceCode) {
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

class Rental3 {
    private Movie3 _movie; //影片
    private int _daysRented; //租期

    public Rental3(Movie3 movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie3 getMovie() {
        return _movie;
    }

    double getCharge() {
        double result = 0;
        switch (getMovie().getPriceCode()) {
            case Movie3.REGULAR:
                result += 2;
                if (getDaysRented() > 2) result += (getDaysRented() - 2) * 1.5;
                break;
            case Movie3.NEW_RELEASE:
                result += getDaysRented() * 3;
                break;
            case Movie3.CHILDRENS:
                result += 1.5;
                if (getDaysRented() > 3) result += (getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }
}

class Customer3 {
    private String _name; //姓名
    private Vector _rentals = new Vector(); //租借记。

    public Customer3(String name) {
        _name = name;
    }

    public void addRental(Rental3 arg) {
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
            double thisAmount = 0;
            Rental3 each = (Rental3) rentals.nextElement();
            thisAmount = each.getCharge();
            // add frequent renter points
            frequentRenterPoints++;// add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    private double amountFor(Rental3 aRental) {
        return aRental.getCharge();
    }

}