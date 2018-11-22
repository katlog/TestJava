package name.fw.effectivejava.examples.chapter08.item52;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Demo {
    public static void main(String[] args) {
        
        // Good - uses interface as type
        List<Subscriber> subscribers = new Vector<Subscriber>();
        
        // Bas - uses class as type
        Vector<Subscriber> subscribers2 = new Vector<Subscriber>();
        
        
        List<Subscriber> subscribers3 = new ArrayList<Subscriber>();
    }
}

class Subscriber{}