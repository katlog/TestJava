
/**  
 * @Title: TestJunit4Parameter.java
 * @Package: org.person.dfw.junit
 * @author: katlog
 * @date: 2017年5月8日 上午11:53:22
 * @version: V1.0  
 */ 
package dfw.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**参数化测试
 *  1、为参数化测试类用@RunW1th注释指定特殊的运行器：Parameterized.class
 *  2、在测试类中声明几个变量，分别用于存储期望值和测试用的数据，并创建一个带参的构造函数
 *  3、创建一个静态(static)测试数据供给(feed)方法，其返回类型为Collection，并用@Parameter注释以修饰
 *  4、编写测试方法（用@Test注释）
 */
//指定Parameter的运行器
@RunWith(Parameterized.class)
public class TestJunit4Parameter {
    private SimpleDateFormat simpleDateFormat;
    private String date;
    private String dateformat;
    private String expectedDate;
    
    public TestJunit4Parameter(String date, String  dateformat, String expectedDate){
        this.date = date;
        this.dateformat = dateformat;
        this.expectedDate = expectedDate;
    }

    //测试数据的提供者
    @Parameters  @SuppressWarnings("unchecked")
    public static Collection getParamters() {
        String[][] object = { 
                { "2011-07-01 00:30:59", "yyyyMMdd", "20110701" },
                { "2011-07-01 00:30:59", "yyyy年MM月dd日", "2011年07月01日" },
                { "2011-07-01 00:30:59", "HH时mm分ss秒", "00时30分59秒" } };
        return Arrays.asList(object);
    }

    @Test
    public void testSimpleDateFormat() throws ParseException{
        SimpleDateFormat df  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = df.parse(this.date);
        simpleDateFormat = new SimpleDateFormat(this.dateformat);
        String result = simpleDateFormat.format(d);
        assertEquals(this.expectedDate, result);
    }
}
