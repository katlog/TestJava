
/**  
 * @Title: TestGson.java
 * @Package: org.person.dfw.gson
 * @author: 丰伟
 * @date: 2017年5月11日 上午9:18:10
 * @version: V1.0  
 */ 
package dfw.json.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.*;

/**JSON数据中的JsonElement有下面这四种类型：
 *      JsonPrimitive —— 例如一个字符串或整型
 *      JsonObject—— 一个以 JsonElement 名字（类型为 String）作为索引的集合。也就是说可以把 JsonObject 看作值为 JsonElement 的键值对集合
 *      JsonArray—— JsonElement 的集合。注意数组的元素可以是四种类型中的任意一种，或者混合类型都支持
 *      JsonNull—— 值为null
 */
public class TestJson_Gson {
    
    private Gson  gson = new Gson();
    private static Student student1 = new Student();  
    private static Student student2 = new Student();  
    private static Student student3 = new Student();  
    static{
        student1.setId(1);  student1.setName("李坤");  student1.setBirthDay(new Date());  
        student2.setId(2);  student2.setName("曹贵生");  student2.setBirthDay(new Date());  
        student3.setId(3);  student3.setName("柳波");   student3.setBirthDay(new Date());  
    }
    
    /**json和bean互相转换*/
    @Test public void json2bean() {  
        // 简单的bean转为json  
        String s1 = gson.toJson(student1);  
        System.out.println("简单Bean转化为Json===" + s1);  
  
        // json转为简单Bean  
        Student student = gson.fromJson(s1, Student.class);  
        System.out.println("Json转为简单Bean===" + student);  
    }
    
    /**json和list相互转换*/
    @Test public void list2bean(){
  
        List<Student> list = new ArrayList<Student>();  
        list.add(student2);  
        list.add(student3);  
  
        // 带泛型的list转化为json  
        String s2 = gson.toJson(list);  
        System.out.println("带泛型的list转化为json==" + s2);  
  
        // json转为带泛型的list  
        List<Student> retList = gson.fromJson(s2,new TypeToken<List<Student>>() {  }.getType());  
        for (Student stu : retList) {  
            System.out.println(stu);  
        }  
    }  
    
    /**json和map间的互相转换*/
    @Test public void json2map(){
        
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();  //支持Map的key为复杂对象的形式  

        //map转json
        Map<Point, String> map1 = new LinkedHashMap<Point, String>();// 使用LinkedHashMap将结果按先进先出顺序排列  
        map1.put(new Point(5, 6), "a");  
        map1.put(new Point(8, 8), "b");  
        String s = gson.toJson(map1);  
        System.out.println(s);// 结果:[[{"x":5,"y":6},"a"],[{"x":8,"y":8},"b"]]  
    
        //json转map
        Map<Point, String> retMap = gson.fromJson(s, new TypeToken<Map<Point, String>>() {  }.getType());  
        for (Point p : retMap.keySet()) {  
            System.out.println("key:" + p + " values:" + retMap.get(p));  
        }  
        System.out.println(retMap);  
    }
    
    /**gson注解的使用*/
    @Test public void annotion(){
        //注意这里的Gson的构建方式为GsonBuilder,区别于test1中的Gson gson = new Gson();  
        Gson gson = new GsonBuilder()  
        .excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性  【注意这个的影响】
        .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式  
        .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式    
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.  
        .setPrettyPrinting() //对json结果格式化.  
        .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.  
                            //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么  
                            //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.  
        .create();  
  
        // 简单的bean转为json  
        String s1 = gson.toJson(student1);  
        System.out.println("简单Bean转化为Json===" + s1);  
  
        // json转为简单Bean  
        Student student = gson.fromJson(s1, Student.class);  
        System.out.println("Json转为简单Bean===" + student);  
  
        List<Student> list = new ArrayList<Student>();  
        list.add(student1);  
        list.add(student2);  
        list.add(student3);  
        
        // 带泛型的list转化为json  
        String s2 = gson.toJson(list);  
        System.out.println("带泛型的list转化为json==" + s2);  
  
        // json转为带泛型的list  
        List<Student> retList = gson.fromJson(s2,new TypeToken<List<Student>>() {  }.getType());  
        for (Student stu : retList) {  
            System.out.println(stu);  
        }  
    }
}

class Point {  //json和map间互相转换用的类  【不能使用方法局部类，否则好像无法在方法体中通过new来实例化】
    private int x;  private int y;  
    public Point(int x, int y) {   this.x = x;  this.y = y;}  
    public int getX() {  return x; }  public void setX(int x) { this.x = x;  }  //X字段的getter和setter
    public int getY() {return y;   }   public void setY(int y) {   this.y = y;  } // Y字段的getter和setter
    @Override   public String toString() {  return "Point [x=" + x + ", y=" + y + "]";  }  
}

class Student {  
    private int id;  
    @Expose 
    private String name;  
    @Expose  
    //@SerializedName("bir")  
    private Date birthDay;  
    public int getId() {  return id;  }  
    public void setId(int id) {   this.id = id;  }  
    public String getName() {     return name;    }  
    public void setName(String name) {        this.name = name;    }  
    public Date getBirthDay() {     return birthDay;  }  
    public void setBirthDay(Date birthDay) {   this.birthDay = birthDay;  }  
    @Override  public String toString() {  
        return "Student [birthDay=" + birthDay + ", id=" + id + ", name=" + name + "]";  
    }  
}  