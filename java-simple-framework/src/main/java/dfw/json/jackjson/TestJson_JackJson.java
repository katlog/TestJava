
/**  
 * @Title: TestJackJson.java
 * @Package: org.person.dfw.jackjson
 * @author: 丰伟
 * @date: 2017年4月13日 上午11:51:43
 * @version: V1.0  
 */ 
package dfw.json.jackjson;

//JSON序列化和反序列化使用的User类  

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

class User1 {  
  private String name;  public String getName() {   return name;  }  public void setName(String name) {   this.name = name; }  
  private Integer age;  public Integer getAge() {   return age;} public void setAge(Integer age) { this.age = age;  }
  private Date birthday;  public Date getBirthday() {return birthday; } public void setBirthday(Date birthday) {this.birthday = birthday;  }
  public User1() { super();}
  public User1( String name, Integer age, Date birthday) { super(); this.name = name; this.age = age;this.birthday = birthday;}  
    
}

class User2 {  
    private String name;             public String getName() {return name; } public void setName(String name) {this.name = name;}  
    //不JSON序列化年龄属性  
    @JsonIgnore   
    private Integer age;             public Integer getAge() { return age;}  public void setAge(Integer age) { this.age = age; }  
    //格式化日期属性  
    @JsonFormat(pattern = "yyyy年MM月dd日") 
    private Date birthday;           public Date getBirthday() {return birthday;} public void setBirthday(Date birthday) {this.birthday=birthday;}
    //序列化email属性为mail  
    @JsonProperty("annotion_mail")  
    private String email;           public String getEmail() { return email;}  public void setEmail(String email) {  this.email = email;}
}

public class TestJson_JackJson {
    /**
     * 对象转json
     * <p>date : 2017年4月13日 下午1:37:39</p>
     * @throws ParseException
     * @throws JsonProcessingException
     */ 
    @Test public void object2json() throws ParseException, JsonProcessingException{
        User1 user = new User1();  
        user.setName("小民");   
        user.setAge(20);  
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");  
        user.setBirthday(dateformat.parse("1996-10-01"));         
          
        /** 
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。 
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。 
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。 
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。 
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。 
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。 
         */  
        ObjectMapper mapper = new ObjectMapper();  
          
        //User类转JSON  
        String json = mapper.writeValueAsString(user);  
        System.out.println(json);  //输出结果：{"name":"小民","age":20,"birthday":844099200000}  
          
        //Java集合转JSON  
        List<User1> users = new ArrayList<User1>();  
        users.add(user);  
        String jsonlist = mapper.writeValueAsString(users);  
        System.out.println(jsonlist);   //输出结果：[{"name":"小民","age":20,"birthday":844099200000}]  
    }
    
    /**
     * json转对象
     * <p>date : 2017年4月13日 下午2:14:48</p>
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */ 
    @Test public void json2object() throws JsonParseException, JsonMappingException, IOException{
        String json = "{\"name\":\"小民\",\"age\":20,\"birthday\":844099200000}";  
        
        /**ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。 */
        ObjectMapper mapper = new ObjectMapper();  
        User1 user = mapper.readValue(json, User1.class);  
        assertEquals(user, new User1("小民",20,new Date(844099200000l)));
    }
    
    /**
     * 简单的jackjson注解 示例
     *  @JsonIgnore 此注解用于属性上，作用是进行JSON操作时忽略该属性。
        @JsonFormat 此注解用于属性上，作用是把Date类型直接转化为想要的格式，如@JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")。
        @JsonProperty 此注解用于属性上，作用是把该属性的名称序列化为另外一个名称，如把trueName属性序列化为name，@JsonProperty("name")。
     * @throws ParseException
     * @throws JsonProcessingException
     */ 
    @Test public void jsonAnnotion() throws ParseException, JsonProcessingException{
        User2 user = new User2();  
        user.setName("小民");   
        user.setEmail("xiaomin@sina.com");  
        user.setAge(20);  
          
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");  
        user.setBirthday(dateformat.parse("1996-10-01"));         
          
        ObjectMapper mapper = new ObjectMapper();  
        String json = mapper.writeValueAsString(user);  
        System.out.println(json); //输出结果：{"name":"小民","birthday":"1996年09月30日","annotion_mail":"xiaomin@sina.com"}  
    }
}
