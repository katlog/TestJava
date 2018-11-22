package name.dfw.refactor._01case.phase2;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by dell on 2018/4/12
 */
public class Movie8 {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title; //名称
    private int _priceCode; //价格（代号）

    private Price8 _price;

    public Movie8(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() { //取得价格代号
        return _price.getPriceCode();
    }

    public void setPriceCode(int arg) { //设定价格代号
        switch (arg) {
            case REGULAR:
                _price = new RegularPrice8();
                break;
            case CHILDRENS:
                _price = new ChildrensPrice8();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice8();
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

        if ((getPriceCode() == Movie8.NEW_RELEASE) && daysRented > 1)
            return 2;
        return 1;
    }
}

abstract class Price8 {
    abstract int getPriceCode(); //取得价格代号

    double  getCharge(int daysRented) {
        double result = 0;
        switch (getPriceCode()) {
            case Movie8.REGULAR:
                result += 2;
                if (daysRented > 2) result += (daysRented - 2) * 1.5;
                break;
            case Movie8.NEW_RELEASE:
                result += daysRented * 3;
                break;
            case Movie8.CHILDRENS:
                result += 1.5;
                if (daysRented > 3) result += (daysRented - 3) * 1.5;
                break;
        }
        return result;
    }
}

class ChildrensPrice8 extends Price8 {
    int getPriceCode() {
        return Movie8.CHILDRENS;
    }
}

class NewReleasePrice8 extends Price8 {
    int getPriceCode() {
        return Movie8.NEW_RELEASE;
    }
}

class RegularPrice8 extends Price8 {
    int getPriceCode() {
        return Movie8.REGULAR;
    }
}


class Rental8 {
    Movie8 _movie; //影片
    private int _daysRented; //租期

    public Rental8(Movie8 movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie8 getMovie() {
        return _movie;
    }

    double getCharge() {
        return _movie.getCharge(_daysRented);
    }

    int getFrequentRenterPoints() {
        return _movie.getFrequentRenterPoints(_daysRented);
    }
}

class Customer8 {
    private String _name; //姓名
    private Vector _rentals = new Vector(); //租借记。

    public Customer8(String name) {
        _name = name;
    }

    public void addRental(Rental8 arg) {
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

            Rental8 each = (Rental8) rentals.nextElement();
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
            Rental8 each = (Rental8) rentals.nextElement();
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
            Rental8 rental8 = (Rental8) elements.nextElement();
            result += rental8.getCharge();
        }

        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;

        Enumeration elements = _rentals.elements();
        while (elements.hasMoreElements()) {
            Rental8 rental8 = (Rental8) elements.nextElement();
            result += rental8.getFrequentRenterPoints();
        }

        return result;
    }

    private double amountFor(Rental8 aRental) {
        return aRental.getCharge();
    }

}