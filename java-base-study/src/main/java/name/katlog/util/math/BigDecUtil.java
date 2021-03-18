package name.katlog.util.math;

import java.math.BigDecimal;

public class BigDecUtil {

	public static void main(String[] args) {
		System.out.println(divUp(1000,10));
	}
	
	/**  
	* 提供精确的加法运算。  
	* @param v1 被加数  
	* @param v2 加数  
	* @return 两个参数的和  
	*/  
	public static double add(double v1,double v2){   
		return add(Double.toString(v1), Double.toString(v2));
		
	}
	
	/**  
	* 提供精确的加法运算。  
	* @param v1 被加数  
	* @param v2 加数  
	* @return 两个参数的和  
	*/  
	public static double add(String v1,String v2){   
		BigDecimal b1 = new BigDecimal(v1);   
		BigDecimal b2 = new BigDecimal(v2);   
		return b1.add(b2).doubleValue();   
	} 
	
	/**
	 * 除法四啥五入,小数点保留两位
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double divUp(double v1, double v2) {
		return div(v1, v2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除法保留两位小数,后面截断
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double divDown(double v1, double v2){
		return div(v1, v2, BigDecimal.ROUND_DOWN);
	}

	public static double divDown(String v1, String v2){
		BigDecimal b1 = new BigDecimal(v1);  
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, 2, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * 除法
	 * @param v1
	 * @param v2
	 * @param type 规则:四舍五入，或截断
	 * @return
	 */
	public static double div(double v1, double v2,int type){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, 2, type).doubleValue();
	}
	
	/**
	 * 减法
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double sub(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));     
		BigDecimal b2 = new BigDecimal(Double.toString(v2));     
		return b1.subtract(b2).doubleValue();     
	}
	
	/**
	 * 乘法保留 保留 小数点后两位，四舍五入
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mulUp(double v1, double v2) {  
	   return mul(v1, v2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 乘法保留 小数点后两位，其余截断
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double mulDown(double v1, double v2) {  
	   return mul(v1, v2, BigDecimal.ROUND_DOWN);
	}
	
	public static double mulDown(String v1,String v2) {  
		BigDecimal b1 = new BigDecimal(v1);  
		BigDecimal b2 = new BigDecimal(v2);  
		return b1.multiply(b2).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();  
	}

	/**
	 * 乘法
	 * @param v1
	 * @param v2
	 * @param type  小数点后保留 规则
	 * @return
	 */
	public static double mul(double v1,double v2,int type){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));  
		BigDecimal b2 = new BigDecimal(Double.toString(v2));  
		return b1.multiply(b2).setScale(2, type).doubleValue();  
	}
}
