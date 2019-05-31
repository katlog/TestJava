package name.katlog.refactor._01case.phase1;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by dell on 2018/4/12
 */
public class Movie5 {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title; //名称
    private int _priceCode; //价格（代号）

    public Movie5(String title, int priceCode) {
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

class Rental5 {
    private Movie5 _movie; //影片
    private int _daysRented; //租期

    public Rental5(Movie5 movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie5 getMovie() {
        return _movie;
    }

    double getCharge() {
        double result = 0;
        switch (getMovie().getPriceCode()) {
            case Movie5.REGULAR:
                result += 2;
                if (getDaysRented() > 2) result += (getDaysRented() - 2) * 1.5;
                break;
            case Movie5.NEW_RELEASE:
                result += getDaysRented() * 3;
                break;
            case Movie5.CHILDRENS:
                result += 1.5;
                if (getDaysRented() > 3) result += (getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }


    int getFrequentRenterPoints() {

        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) && getDaysRented() > 1)
            return 2;
        return 1;
    }
}

class Customer5 {
    private String _name; //姓名
    private Vector _rentals = new Vector(); //租借记。

    public Customer5(String name) {
        _name = name;
    }

    public void addRental(Rental5 arg) {
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

            Rental5 each = (Rental5) rentals.nextElement();
            frequentRenterPoints += each.getFrequentRenterPoints();

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
            totalAmount += each.getCharge();
        }

        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }


    private double amountFor(Rental5 aRental) {
        return aRental.getCharge();
    }

}