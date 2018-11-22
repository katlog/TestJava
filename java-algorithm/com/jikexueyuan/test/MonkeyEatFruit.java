package com.jikexueyuan.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * 猴子吃桃问题
 */
public class MonkeyEatFruit {

	/**
	 * 递推算法
	 */
	public int eat01(int n) {
		int a = 1;
		// 也可以这样考虑,“第1天开始吃桃子，连续吃了n-1天”
		// 写成for(int i=1;i<=n-1;i++)，无所谓，结果一样
		for (int i = 2; i <= n; i++) {
			a = 2 * a + 2;
		}
		return a;
	}
	/**
	 * 递归算法
	 */
	public int eat02(int n) {
		System.out.println("f(" + n + ")压栈");
		if (n == 1) {
			System.out.println("此时函数栈达到最大深度!");
			System.out.println("f(" + n + ")弹栈");
			return 1;
		} else {
			int a = eat02(n - 1) * 2 + 2;
			System.out.println("f(" + n + ")弹栈");
			return a;
		}
	}
	/**
	 * 递归算法 用三元运算符把代码简化为一行
	 */
	public int eat03(int n) {
		return n == 1 ? 1 : eat03(n - 1) * 2 + 2;
	}
	/**
	 * 模拟猴子吃桃的过程 用断言验证正确性
	 */
	public void check(int n, int num) {
		int a = num;
		for (int i = 2; i <= n; i++) {
			a = a / 2 - 1;
		}
		System.out.println(a);
		Assert.assertTrue(a == 1);
	}
	@Test
	public void test01() {
		int n = 10;
		int num = eat01(n);
		System.out.println(num);
	}
	@Test
	public void test02() {
		int n = 10;
		int num = eat02(n);
		System.out.println(num);
	}
	@Test
	public void test03() {
		// 当n很大的时候，函数栈会溢出
		int n = 12000;
		int num = eat03(n);
		System.out.println(num);
	}
	@Test
	public void testCheck() {
		int n = 10;
		int num = 1534;
		check(n, num);
	}
	@Test public void test111(){
		int days = 10;
		int sum =1 ;
		for (int i = 0; i < days; i++) {
			sum +=sum+1;
		}
		System.out.println(sum);
	}
}
