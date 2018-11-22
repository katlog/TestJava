package com.jikexueyuan.test;

/**
 * 递归调用普通函数，并捕获异常
 */
public class SumExceptionMethod {
private int n;
private int[] array;

public SumExceptionMethod() {
	super();
}
public SumExceptionMethod(int n) {
	super();
	this.n = n;
	array=new int[n+1];
}
public int sumMethod(int i){
	try {
		array[i]=array[i-1]+i;
		int k=sumMethod(i+1);
		return k;
	} catch (ArrayIndexOutOfBoundsException e) {
		return array[n];
	}
}
}
