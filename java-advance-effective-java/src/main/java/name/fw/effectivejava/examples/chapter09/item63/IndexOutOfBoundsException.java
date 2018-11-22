package name.fw.effectivejava.examples.chapter09.item63;


public class IndexOutOfBoundsException extends RuntimeException {
    
    int lowerBound ;
    int upperBound ;
    int index ;
    
    
    /**
     * Constructor an IndexOutOfBoundsException
     * @param lowerBound the lowest legal index value.
     * @param upperBound the highest legal index value plus one
     * @param index      the actual index value
     */ 
    public  IndexOutOfBoundsException(int lowerBound,int upperBound,int index) {
        // Generate a detail message that captures the failuer
        super("Lower band:"+lowerBound+", Upper bound:"+upperBound+
            ",Index:"+index);
        // Save failure information for programatic access
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.index = index;
    }
}
