package name.dfw.refactor._01case.phase1;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by dell on 2018/4/12
 */
public class Movie2 {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title; //名称
    private int _priceCode; //价格（代号）

    public Movie2(String title, int priceCode) {
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

class Rental2 {
    private Movie2 _movie; //影片
    private int _daysRented; //租期

    public Rental2(Movie2 movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie2 getMovie() {
        return _movie;
    }
}

class Customer2 {
    private String _name; //姓名
    private Vector _rentals = new Vector(); //租借记。

    public Customer2(String name) {
        _name = name;
    }

    public void addRental(Rental2 arg) {
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
            Rental2 each = (Rental2) rentals.nextElement();
            thisAmount = amountFor(each); //计算一笔租片费。
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie2.NEW_RELEASE) && each.getDaysRented() > 1)
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

    private double amountFor(Rental2 aRental) { //计算一笔租片费。
        double result = 0;
        switch (aRental.getMovie().getPriceCode()) {
            case Movie2.REGULAR: //普通片
                result += 2;
                if (aRental.getDaysRented() > 2)result += (aRental.getDaysRented() - 2) * 1.5;
                break;
            case Movie2.NEW_RELEASE: //新片
                result += aRental.getDaysRented() * 3;
                break;
            case Movie2.CHILDRENS: //儿童。
                result += 1.5;
                if (aRental.getDaysRented() > 3)result += (aRental.getDaysRented() - 3) * 1.5;
                break;
        } return result;
    }

}