package com.jikexueyuan.test;

import org.junit.Test;

public class CommonSum {

/**
 *递推算法
 */
public int commonMethod01(int n){
	int sum=0;
	for(int i=1;i<=n;i++){
		sum+=i;
	}
	return sum;
}
/**
 * 递归算法
 */
public int commonMethod02(int n){
	if(n==1){
		return 1;
	}else{
		return commonMethod02(n-1)+n;
	}
}
/**
 * 等差数列求和公式
 */
public int commonMethod03(int n){
	return n*(1+n)/2;
}
@Test
public void test(){
	int n=100;
	int sum1=commonMethod01(n);
	int sum2=commonMethod02(n);
	int sum3=commonMethod03(n);
	System.out.println(sum1+" "+sum2+" "+sum3);
}
}