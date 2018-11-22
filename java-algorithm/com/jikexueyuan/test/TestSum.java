package com.jikexueyuan.test;

import org.junit.Test;

public class TestSum {
	@Test
	public void testMethod(){
		int n=100;
		SumExceptionMethod sem=new SumExceptionMethod(n);
		int sum=sem.sumMethod(1);
		System.out.println(sum);
	}
	@Test
	public void testConstructor(){
		int n=100;
		SumExceptionConstructor.n=n;
		SumExceptionConstructor.array=new int[n+1];
		new SumExceptionConstructor(1);
	}
}
