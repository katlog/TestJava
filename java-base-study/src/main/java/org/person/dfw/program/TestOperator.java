package org.person.dfw.program;

import java.util.Arrays;

import org.junit.Test;
import org.person.dfw.util.Print;

/**
 * 运算符测试
 * @version:v1.0
 * @date: 2017年3月22日 上午9:28:33
 *
 */
public class TestOperator {
	
	/**单目运算*/
	@Test public void _2_unary(){
		int i = 0;
		System.out.println(i++);
		int j = 0;
		System.out.println(++j);
	}
	
	/**创建对象和类型转换*/
	@Test public void _3_createObject_typeCast(){
		
		//创建对象
		
		Object object = new Object();				//创建对象
		int[] num = new int[26];					//创建数组对象，全部初始化为0
		System.out.println(Arrays.toString(num));
		
		//类型转换
		
		//类型转换  会有异常
//		String str = (String) object;				//java.lang.Object cannot be cast to java.lang.String
		//必须是向下转型，再向上转型
		String str = "a";
		Object obj = str;
		String str1 = (String) obj;
		System.out.println(str1);
		
		//基本数据类型强制转换
		double d1 = 3.82;
		System.out.println((int)d1);				//精度丢失：3
		double d2 = -3.82;
		System.out.println((int)d2);				//精度丢失：-3
		
	}
	
	/** 乘 除 余*/
	@Test public void _4_times_multiply(){
		
		//
		System.out.println(1/10);
		System.out.println(10/10);
		System.out.println(11/10);
		
		System.out.println(18.0/3);					//
		System.out.println(1%10);
		System.out.println(10%10);
		System.out.println(11%10);
		
		
		System.out.println("取得一个数的十位数："+(1242/10)%10);//通过循环可以继续取百、千、万...
	}
	

    /**位运算*/
    @Test
	public void _6_bit() {
        // 1、左移( << )
        // 0000 0000 0000 0000 0000 0000 0000 0101 然后左移2位后，低位补0
		Print.bin(5);
        // 0000 0000 0000 0000 0000 0000 0001 0100 换算成10进制为20
        System.out.println("5 << 2:"+(5 << 2));
        // 运行结果是20
		Print.bin(20);

        // 2、右移( >> ) 高位补符号位
        // 0000 0000 0000 0000 0000 0000 0000 0101 然后右移2位，高位补0：
        // 0000 0000 0000 0000 0000 0000 0000 0001
        System.out.println("5 >> 2:"+(5 >> 2));
        // 运行结果是1
		Print.bin(1);

        // 3、无符号右移( >>> ) 高位补0
        // 0000 0000 0000 0000 0000 0000 0000 0101    :5
        // 1111 1111 1111 1111 1111 1111 1111 1011    :-5  为5的二进制取反加1
        
        // 0000 0000 0000 0000 0000 0000 0000 0000  :0          为5的二进制 右移三位  高位补符号位(0)
        // 1111 1111 1111 1111 1111 1111 1111 1111  :-1         为-5的二进制 右移三位  高位补符号位(1)
        // 0001 1111 1111 1111 1111 1111 1111 1111  :536870911  为-5的二进制 右移三位  高位补0
        System.out.println("5 >> 3:"+(5 >> 3));
        // 结果是0
		Print.bin(0);
        System.out.println("-5 >> 3:"+(-5 >> 3));
        // 结果是-1
		Print.bin(-1);
        System.out.println("-1 >>> 3:"+(-1 >>> 3));
        // 结果是536870911  正数和>>相同，负数好像多移动了一位
        System.out.println("-5 >>> 3:"+(-5 >>> 3));
        // 结果是536870911

        // 4、位与( & )
        // 位与：第一个操作数的的第n位于第二个操作数的第n位如果都是1，那么结果的第n为也为1，否则为0
        // 0000 0000 0000 0000 0000 0000 0000 0101     :5
        // 0000 0000 0000 0000 0000 0000 0000 0011     :3
        //-----------------------------------------------与
        // 0000 0000 0000 0000 0000 0000 0000 0001     :1
        // 结果为1
        System.out.println("5 & 3:"+(5 & 3));
        // 0000 0000 0000 0000 0000 0000 0000 0101     :5
        // 1111 1111 1111 1111 1111 1111 1111 1101     :-3
        //-----------------------------------------------与
        // 0000 0000 0000 0000 0000 0000 0000 0101     :5
        // 结果为1
        System.out.println("5 & -3:"+(5 & -3));

        // 5、位或( | )
        // 第一个操作数的的第n位于第二个操作数的第n位 只要有一个是1，那么结果的第n为也为1，否则为0
        // 0000 0000 0000 0000 0000 0000 0000 0101     :5
        // 0000 0000 0000 0000 0000 0000 0000 0011     :3
        //-----------------------------------------------或
        // 0000 0000 0000 0000 0000 0000 0000 0111     :7
        System.out.println("5 | 3:"+(5 | 3));// 结果为7

        // 6、位异或( ^ )
        // 第一个操作数的的第n位于第二个操作数的第n位 相反，那么结果的第n为也为1，否则为0
        // 0000 0000 0000 0000 0000 0000 0000 0101     :5
        // 0000 0000 0000 0000 0000 0000 0000 0011     :3
        //-----------------------------------------------异或
        // 0000 0000 0000 0000 0000 0000 0000 0110     :6
        System.out.println("5 ^ 3:"+(5 ^ 3));// 结果为6

        // 7、位非( ~ )
        // 操作数的第n位为1，那么结果的第n位为0，反之。
        // 0000 0000 0000 0000 0000 0000 0000 0101     :5
        // 1111 1111 1111 1111 1111 1111 1111 1010     :-6
        System.out.println("~5:"+(~5));// 结果为-6
     
        System.out.println(formatBin((5)));
    }

    /** 位移 */
    @Test
    public void _bit_op(){
		int count_bits = 29;

		Print.bin(1);
		Print.bin(-1);

		int running    = -1 << count_bits;
		Print.bin(running);

		int shutdown   =  0 << count_bits;
		Print.bin(shutdown);

		int stop       =  1 << count_bits;
		Print.bin(stop);

		int tidying    =  2 << count_bits;
		Print.bin(tidying);

		int terminated =  3 << count_bits;
		Print.bin(terminated);

    }
    
    /**
     * 将int类型格式化为二进制，高位为0的那种
     */
    private String formatBin(int i){
        StringBuffer string = new StringBuffer(String.valueOf(Integer.toBinaryString(i)));
        while (string.length()<32) {
            string.insert(0, "0");
        }
        int length = string.length();
        for (int j = 4; j < length+3; j+=5) {
            string.insert(j, new char[]{' '}, 0, 1);
        }
        return string.toString();
    }
    
    /**关系运算*/
    interface C {};
    @Test public void _7_relational(){
    	
    	/** "实例  instanceof 类" 运算  */
    	System.out.println("" instanceof String) ;			//true
//    	System.out.println(1 instanceof Integer);			//这种情况无法自动包装
    	class A{};
    	class B extends A{};
    	class D implements C{};
    	
    	System.out.println(new A() instanceof B);			//false
    	System.out.println(new B() instanceof B);			//true 实例
    	System.out.println(new B() instanceof A); 			//true 子类
    	System.out.println(new D() instanceof D); 			//true 实例
    	System.out.println(new D() instanceof C);			//true 子类
    	
    	/** < <= > >= 不再进行测试*/
    }
    
    
    @Test public void _14_ternay(){//三目运算
    	
    	//使用条件：仅当if语句中内嵌的语句为赋值语句(且两个分支都给同一变量赋值)时才能代替if语句
    	int a=1;
    	//1、if使用格式
    	if(a%2==0) System.out.println("even/n");
    	else System.out.println("odd/n");
    	
    	//2.1、三目运算使用方式【不适用】
//    	(a%2==0)?println("even/n"):printf("odd/n");
    	//2.2、更改三目运算符的使用方式
    	System.out.printf("%s/n",(a%2==0?"even":"odd"));
    	
    	
    }
    
}
