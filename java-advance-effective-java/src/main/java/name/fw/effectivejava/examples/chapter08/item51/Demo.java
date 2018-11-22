package name.fw.effectivejava.examples.chapter08.item51;

public class Demo {
    private static final int LINE_WIDTH = 0;

    // Inappropriate use of string concatenation - Performs horribly!
//    public String statement() {
//        String result = "";
//        for (int i = 0; i < numItems(); i++) {
//            result += lineForItem(i); // String concatenation
//        }
//        return result;
//    }
//    
    public String statement() {
        StringBuilder result = new StringBuilder(numItems()*LINE_WIDTH);
        for (int i = 0; i < numItems(); i++) {
           result.append(lineForItem(i));
        }
        return result.toString();
    }
    
    
    

    private String lineForItem(int i) {
        return null;
    }

    private int numItems() {
        // TODO Auto-generated _03method stub
        return 0;
    }
}
