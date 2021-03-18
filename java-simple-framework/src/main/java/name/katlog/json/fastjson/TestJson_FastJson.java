package name.katlog.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

import static org.junit.Assert.assertEquals;

/**
 * 
 * Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以在JSON类上的静态方法直接完成。
 * 
    public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject    
    public static final <T> T parseObject(String text, Class<T> clazz); // 把JSON文本parse为JavaBean 
    public static final <T> List<T> parseArray(String text, Class<T> clazz); //把JSON文本parse成JavaBean集合 
    public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray 
    public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray 
    
    public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本 
    public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本 
    public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
 *
 */
public class TestJson_FastJson {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class User{
        private String name;
        private int age;
        private String password;
    }
    
    private static String jsonText="{\"age\":27,\"name\":\"张三\",\"password\":\"12567\"}";  
    private static String jsonArraysText="[{\"age\":27,\"name\":\"张三\",\"password\":\"12567\"},{\"age\":30,\"name\":\"李四\",\"password\":\"8888\"}]";  
    private static String jsonExpectArraysText="[{\"age\":0,\"name\":\"haha\",\"password\":\"11111111\"},{\"age\":0,\"name\":\"haha2\",\"password\":\"22222\"}]";  
    
   @Test public void _common(){
	   JSONObject json=JSON.parseObject(jsonText);  
	   
	   //添加
	   json.put("favorite", "footable");
	   System.out.println(json);
	   
	   //entrySet遍历
	   Set<Entry<String, Object>> entrySet = json.entrySet();
	   for (Entry<String, Object> entry : entrySet) {
		System.out.println(entry.getKey()+":"+entry.getValue());
	}
   }
    
    
    
    /** javabean转成string*/
    @Test public void bean2jsonStr(){
        User user1 = new User("fw",18,"123123"); 
        System.out.println(JSON.toJSONString(user1));
    }
    
    /** string 转成javabean对象
     *      JSON.parseObject(jsonText, Class<T>)
     */
    @Test public void jsonStr2bean(){
    	User user = JSON.parseObject(jsonText, User.class);
    	assertEquals("12567", user.getPassword());
    }
    
    /**1、string 转成 jsonObject  
     *      JSON.parseObject(String jsonStr)
     */
    @Test public void str2JSONObject(){
        JSONObject jobj=JSON.parseObject(jsonText);  
        assertEquals("12567", jobj.get("password"));
        assertEquals(jobj.toJSONString(), jobj.toString());
        assertEquals(jobj.toJSONString(), jsonText);
    }
    
    /**3、字符串转换成list
     *      JSON.parseObject(jsonArraysText,new TypeReference<Class<T extends iterator>>(){})
     */
    @Test public void string2List(){  
        //转成成数组  
        User[] stu1 = JSON.parseObject(jsonArraysText,new TypeReference<User[]>(){});  
        List<User> list1 = Arrays.asList(stu1);  
        // 转换成ArrayList  
        ArrayList<User> list2 = JSON.parseObject(jsonArraysText, new TypeReference<ArrayList<User>>(){});  
        
    }  
  
    /** 4、list转字符串
     *      JSON.toJSONString(Collections list)
     */  
    @Test  public void getListObject(){  
        List<User> userList = new ArrayList<>();  
        User   user1 = new User();  user1.setName("haha"); user1.setPassword("11111111");  
        User   user2 = new User();  user2.setName("haha2");   user2.setPassword("22222");  
        userList.add(user1);  
        userList.add(user2);  
        //List 对象转JSON 字符串  
        assertEquals(jsonExpectArraysText, JSON.toJSONString(userList));
        //JSON 字符串 转 List 对象  
        List<User> userList2 = JSON.parseArray(jsonExpectArraysText,User.class);  
        assertEquals(userList2.get(0).getName(),"haha");

        String jsonString = JSON.toJSONString(Arrays.asList("1", "2", "3"));
        System.out.println("jsonString = " + jsonString);
    }  
  
  
    /**5、Map换成字符串
     *      JSON.toJSONString(Object object, boolean prettyFormat)
     */  
    @Test public void map2jsonStr(){  
        //创建一个Map对象  
        Map<String,String> map = new HashMap<String, String>();  
        map.put("username", "张三");  
        map.put("password", "2222");  
        map.put("age", "198");  
        String json = JSON.toJSONString(map,true); //转成JSON数据  
        System.out.println(json);
        Map<String,String> map1 = (Map<String,String>)JSON.parse(json);  
        //遍历数组数据  
        System.out.println(map1);
    }  
  
    /** 6、字符串转map
     * 
     */  
    @Test public  void jsonStr2map() {  
        Map map = new HashMap();  
        map.put("name", "张三");  
        map.put("password", "3333");  
        map.put("age", "198");  
        String json = JSON.toJSONString(map);  
        Map map1 = JSON.parseObject(json);  
        printMap(map);  
    }
    
    @Test public  void map2jsonObject() {  
    	Map map = new HashMap();  
    	map.put("name", "张三");  
    	map.put("password", "3333");  
    	map.put("age", "198");  
    	JSONObject json = (JSONObject)JSON.toJSON(map);
    	System.out.println(json);
    }  
    
    @Test public  void jsonObject2map() {  
    	JSONObject json = new JSONObject();  
    	json.put("name", "张三");  
    	json.put("password", "3333");  
    	json.put("age", "198");  
    	Map map = JSON.toJavaObject(json, Map.class);
        printMap(map); 
    }  
    
    /**7、使用SerializerFeature进行格式化
     *         JSON.toJSONString(Object object, SerializerFeature... features)
     */
    @Test public void serializerFeature(){
        /**使用SerializerFeature特性格式化日期。*/
        String dateJson = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
        System.out.println(dateJson);
        
        /**也可以指定输出日期格式*/
        dateJson = JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dateJson);
        
        /**使用单引号*/
        String listJson = JSON.toJSONString(JSON.parseArray(jsonArraysText), SerializerFeature.UseSingleQuotes);
        System.out.println(listJson);
        
        /**输出Null字段:缺省情况下FastJSON不输入为值Null的字段，可用SerializerFeature.WriteMapNullValue使其输出*/
        Map<String, Object> map = new HashMap<String,Object>();
        String b = null;map.put("a", b);
        Integer i = 1; map.put("b", i);
        listJson = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
        System.out.println(listJson);
        
        /**序列化时写入类型信息。*/
        User user = new User();
        user.setAge(18);
        user.setName("李四");
        listJson = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println(listJson);
        
        /**由于序列化带了类型信息，使得反序列化时能够自动进行类型识别*/
        User user1 = (User) JSON.parse(listJson);
        System.out.println(user1.getAge());
    }
    

	private void printMap(Map map) {
		for (Object obj : map.entrySet()) {  
            Entry<String, String> entry = (Entry<String, String>) obj;
            System.out.println(entry.getKey() + "--->" + entry.getValue());  
        }
	}  
}
