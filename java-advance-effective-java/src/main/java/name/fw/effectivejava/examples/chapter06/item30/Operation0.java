// Enum type that switches on its own value - questionable
package name.fw.effectivejava.examples.chapter06.item30;

public enum Operation0 {
	PLUS,	MINUS,	TIMES,	DIVIDE;

	// Do the arithmetic op represented by this constatnt
	double apply(double x,double y){
	    switch (this) {
            case PLUS:return x+y;
            case MINUS:return x+y;
            case TIMES:return x+y;
            case DIVIDE:return x+y;
        }
	    throw new AssertionError("Unkown op:"+this);
	}

}
