
/**  
 * @Title: TestEnum.java
 * @Package: org.person.dfw.objectAndclass
 * @author: 丰伟
 * @date: 2017年5月12日 上午11:32:48
 * @version: V1.0  
 */ 
package org.person.dfw.objectAndclass;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map.Entry;

import org.junit.Test;

/**
 * @author: 丰伟
 * @date: 2017年5月12日 上午11:32:48
 */
public class TestEnum {

	
	/**  
	 * 普通枚举 
	 * 	这段代码实际上调用了4次 Enum(String name, int ordinal)：如new Enum<ColorEnum>("red",0);
	 */  
    public enum ColorEnum {  
    	
//    	public int a = 1; //【没有一般属性】
//    	public static int b = 1; //【没有static属性】
        red, green, yellow, blue;  
    }  
      
    /** 
     * 枚举像普通的类一样可以添加属性和方法，可添加静态和非静态的属性或方法 
     */  
    public enum SeasonEnum {  
        //注：枚举写在最前面，否则编译出错  
        spring, summer, autumn, winter;  
        private final static String position = "test";  
  
        public static SeasonEnum getSeason() {  
        	return "test".equals(position) ? spring : winter;  
        }  
    }  
      
    /**性别：实现带有构造器的枚举 */
    public enum Gender{  
        //通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错  
        //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错  
        MAN("MAN"), WOMEN("WOMEN");  
        private final String value;  
  
        //构造器默认也只能是private, 从而保证构造函数只能在内部使用  
        Gender(String value) {  
            this.value = value;  
        }  
        public String getValue() {  
            return value;  
        }  
    }  
      
   /**订单状态 ：实现带有抽象方法的枚举 */  
    public enum OrderState {  
        /** 已取消 */  
        CANCEL {public String getName(){return "已取消";}},  
        /** 待审核 */  
        WAITCONFIRM {public String getName(){return "待审核";}},  
        /** 等待付款 */  
        WAITPAYMENT {public String getName(){return "等待付款";}},  
        /** 正在配货 */  
        ADMEASUREPRODUCT {public String getName(){return "正在配货";}},  
        /** 等待发货 */  
        WAITDELIVER {public String getName(){return "等待发货";}},  
        /** 已发货 */  
        DELIVERED {public String getName(){return "已发货";}},  
        /** 已收货 */  
        RECEIVED {public String getName(){return "已收货";}};  
          
        public abstract String getName();  
    }  
      
   @Test public void main() {  
        //枚举是一种类型，用于定义变量，以限制变量的赋值；赋值时通过“枚举名.值”取得枚举中的值  
        ColorEnum colorEnum = ColorEnum.blue;  
        switch (colorEnum) {  
        case red:  
            System.out.println("color is red");  
            break;  
        case green:  
            System.out.println("color is green");  
            break;  
        case yellow:  
            System.out.println("color is yellow");  
            break;  
        case blue:  
            System.out.println("color is blue");  
            break;  
        }  
          
        //遍历枚举  
        System.out.println("遍历ColorEnum枚举中的值");  
        for(ColorEnum color : ColorEnum.values()){  
            System.out.println(color);  
            System.out.println(color.name());  
        }  
          
        //获取枚举的个数  
        System.out.println("ColorEnum枚举中的值有"+ColorEnum.values().length+"个");  
          
        //获取枚举的索引位置，默认从0开始  
        System.out.println(ColorEnum.red.ordinal());//0  
        System.out.println(ColorEnum.green.ordinal());//1  
        System.out.println(ColorEnum.yellow.ordinal());//2  
        System.out.println(ColorEnum.blue.ordinal());//3  
          
        //枚举默认实现了java.lang.Comparable接口  
        System.out.println(ColorEnum.red.compareTo(ColorEnum.green));//-1  
          
        System.out.println("===========");  
        System.err.println("季节为" + SeasonEnum.getSeason());  
          
        System.out.println("===========");  
        for(Gender gender : Gender.values()){  
            System.out.println(gender.value);  
        }  
         
        System.out.println("===========");  
        for(OrderState order : OrderState.values()){  
            System.out.println(order.getName());  
        }  
    }  
   
   @Test  public void setAmap(){
       // EnumSet的使用
       EnumSet<ColorEnum> colorSet = EnumSet.allOf(ColorEnum.class);
       for (ColorEnum day : colorSet) {
           System.out.println(day);
       }

       // EnumMap的使用
       EnumMap<ColorEnum, String> weekMap = new EnumMap(ColorEnum.class);
       weekMap.put(ColorEnum.red, "红色");
       weekMap.put(ColorEnum.green, "绿色");
       for (Iterator<Entry<ColorEnum, String>> iter = weekMap.entrySet().iterator(); iter.hasNext();) {
           Entry<ColorEnum, String> entry = iter.next();
           System.out.println(entry.getKey().name() + ":" + entry.getValue());
       }
   }
      
}  
