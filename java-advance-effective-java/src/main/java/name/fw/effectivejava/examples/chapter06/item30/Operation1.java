// Enum type with constant-specific _03method implementations
package name.fw.effectivejava.examples.chapter06.item30;

public enum Operation1 {
	PLUS{ double apply(double x,double y){return x+y;}},
	MINUS{ double apply(double x,double y){return x-y;}},
	TIMES{ double apply(double x,double y){return x*y;}},
	DIVIDE{ double apply(double x,double y){return x/y;}};

	abstract double apply(double x,double y);
}
