package name.katlog.refactor._01case.phase2;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by dell on 2018/4/12
 */
public class Movie10 {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title; //名称
    private int _priceCode; //价格（代号）

    private Price10 _price;

    public Movie10(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() { //取得价格代号
        return _price.getPriceCode();
    }

    public void setPriceCode(int arg) { //设定价格代号
        switch (arg) {
            case REGULAR:
                _price = new RegularPrice10();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice10();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice10();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle() {
        return _title;
    }

    double getCharge(int daysRented) {
        return _price.getCharge(daysRented);
    }

    int getFrequentRenterPoints(int daysRented) {
        return _price.getFrequentRenterPoints(daysRented);
    }
}

abstract class Price10 {
    abstract int getPriceCode(); //取得价格代号

    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}

class ChildrensPrice10 extends Price10 {
    int getPriceCode() {
        return Movie10.CHILDRENS;
    }

    double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3) result += (daysRented - 3) * 1.5;
        return result;
    }
}

class NewReleasePrice10 extends Price10 {
    int getPriceCode() {
        return Movie10.NEW_RELEASE;
    }

    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2: 1;
    }
}

class RegularPrice10 extends Price10 {
    int getPriceCode() {
        return Movie10.REGULAR;
    }
    double getCharge(int daysRented){
        double result = 2;
        if (daysRented > 2)result += (daysRented - 2) * 1.5;
        return result;
    }
}


class Rental10 {
    Movie10 _movie; //影片
    private int _daysRented; //租期

    public Rental10(Movie10 movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie10 getMovie() {
        return _movie;
    }

    double getCharge() {
        return _movie.getCharge(_daysRented);
    }

    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
    }
}

class Customer10 {
    private String _name; //姓名
    private Vector _rentals = new Vector(); //租借记。

    public Customer10(String name) {
        _name = name;
    }

    public void addRental(Rental10 arg) {
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

            Rental10 each = (Rental10) rentals.nextElement();
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
            Rental10 each = (Rental10) rentals.nextElement();
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
            Rental10 rental10 = (Rental10) elements.nextElement();
            result += rental10.getCharge();
        }

        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;

        Enumeration elements = _rentals.elements();
        while (elements.hasMoreElements()) {
            Rental10 rental10 = (Rental10) elements.nextElement();
            result += rental10.getFrequentRenterPoints();
        }

        return result;
    }

    private double amountFor(Rental10 aRental) {
        return aRental.getCharge();
    }

}