package name.katlog.refactor._01case.phase1;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by dell on 2018/4/12
 */
public class Movie1 {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    private String _title; //名称
    private int _priceCode; //价格（代号）

    public Movie1(String title, int priceCode) {
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

class Rental1 {
    private Movie1 _movie; //影片
    private int _daysRented; //租期

    public Rental1(Movie1 movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie1 getMovie() {
        return _movie;
    }
}

class Customer1 {
    private String _name; //姓名
    private Vector _rentals = new Vector(); //租借记。

    public Customer1(String name) {
        _name = name;
    }

    public void addRental(Rental1 arg) {
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
            Rental1 each = (Rental1) rentals.nextElement();
            thisAmount = amountFor(each); //计算一笔租片费。
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie1.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        //add footer line
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    private int amountFor(Rental1 each) { //计算一笔租片费。
        int thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie1.REGULAR: //普通片
                thisAmount += 2;
                if (each.getDaysRented() > 2) thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie1.NEW_RELEASE: //新片
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie1.CHILDRENS: //儿童。
                thisAmount += 1.5;
                if (each.getDaysRented() > 3) thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

}