package name.katlog.json.netjson;

import lombok.Data;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

/**
 * @moudle: TestJson_netsf 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年7月25日 下午4:25:30
 *
 */
public class TestJson_netsf {
	
	private static String jsonStr = "{\"username\":\"huangwuyi\",\"sex\":\"男\",\"QQ\":\"413425430\",\"Min.score\":99,\"nickname\":\"梦中心境\",\"address\":\"福建省厦门市\",\"jsonArray\":[\"this is a jsonArray value\",\"another jsonArray value\"]}";
	
	private static String jsonArrayStr = "[{\"no\":\"104\",\"provience\":\"陕西\",\"road\":\"高新路\",\"streate\":\"\"},{\"no\":\"105\",\"provience\":\"陕西\",\"road\":\"未央路\",\"streate\":\"张办\"}]";
	
	@Test public void _common() {
    	
		//创建json对象
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "huangwuyi");
        jsonObject.put("sex", "男");
        jsonObject.put("QQ", "413425430");
        jsonObject.put("Min.score", new Integer(99));
        jsonObject.put("nickname", "梦中心境");
        
        //输出json对象
        System.out.println("jsonObject：" + jsonObject);
        
        //遍历json对象
        Set<Entry<String, Object>>set = jsonObject.entrySet();
        for (Entry<String, Object> entry : set) {
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
        
        //添加属性，在json后面追加元素。
        jsonObject.element("address", "福建省厦门市");
        System.out.println("添加属性后的对象：" + jsonObject);

        //判读输出对象的类型
        boolean isArray = jsonObject.isArray();
        boolean isEmpty = jsonObject.isEmpty();
        boolean isNullObject = jsonObject.isNullObject();
        System.out.println("是否为数组:" + isArray + " 是否为空:" + isEmpty + " isNullObject:" + isNullObject);
        
        //添加json对象
        JSONObject json = new JSONObject();
        json.put("weigth", 110);
        json.put("heigth", 170);
        jsonObject.put("data", json);
        System.out.println("添加json后的对象：" + jsonObject);
        
        //获取添加的json对象
        json = jsonObject.getJSONObject("data");
        System.out.println("获取添加的json对象："+json);

        // JSONArray对象
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(0, "this is a jsonArray value");
        jsonArray.add(1, "another jsonArray value");
        System.out.println(jsonArray);
        
        //添加jsonArray
        jsonObject.element("jsonArray", jsonArray);
        //获取jsonArray
        JSONArray array = jsonObject.getJSONArray("jsonArray");
        System.out.println(array);

		// 根据key返回一个字符串
		String username = jsonObject.getString("username");
		System.out.println("username==>" + username);

	}
	
	@Test public void str2json(){
		JSONObject json = JSONObject.fromObject(jsonStr);
		System.out.println(json);
	}
		
	@Test public void str2jsonArray(){
		JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);
		System.out.println(jsonArray);
	}
	
	@Test public void str2Bean(){
		String string ="{\"code\":0,\"codeDesc\":\"Success\",\"found\":1,\"idFound\":1,\"message\":\"No Error\",\"riskInfo\":[{\"riskCode\":5,\"riskCodeValue\":2}],\"riskScore\":40}";
		Map config = new  HashMap();  
		config.put("riskInfo", RiskInfo.class);  
//		Risk bean = (Risk) JSONObject.toBean(JSONObject.fromString(string), Risk.class,config);
//		System.out.println(bean);
	}
	
	@Test public void json2Object() {
		
		JSONObject jsonObject = JSONObject.fromObject("{\"no\":\"104\",\"provience\":\"陕西\",\"road\":\"高新路\",\"streate\":\"\"}");
		
		//【Address需要单独出来成public class否则报错】
		Address address = (Address) JSONObject.toBean(jsonObject, Address.class);
		System.out.println(address);
	}
	
	@Test public void object2Json(){

		Address address = new Address();
		address.setNo("104");
		address.setProvience("陕西");
		address.setRoad("高新路");
		address.setStreate("");

		JSONArray json = JSONArray.fromObject(address);
		System.out.println(json);
	}
	
	@Test public void object2Json1(){
		
		Address address = new Address();
		address.setNo("104");
		address.setProvience("陕西");
		address.setRoad("高新路");
		address.setStreate(null);
		
		JSONObject json = JSONObject.fromObject(address);
		System.out.println(json);
	}
	
	@Test public void objectList2JsonArray(){

		Address address = new Address();
		address.setNo("104");
		address.setProvience("陕西");
		address.setRoad("高新路");
		address.setStreate("");

		Address address2 = new Address();
		address2.setNo("105");
		address2.setProvience("陕西");
		address2.setRoad("未央路");
		address2.setStreate("张办");

		List list = new ArrayList();
		list.add(address);
		list.add(address2);

		JSONArray json = JSONArray.fromObject(list);
		System.out.println(json);
	}
	
	@Test public void jsonArray2ObjectList(){
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add("{\"no\":\"104\",\"provience\":\"陕西\",\"road\":\"高新路\",\"streate\":\"\"}");
		jsonArray.add("{\"no\":\"104\",\"provience\":\"陕西\",\"road\":\"高新路\",\"streate\":\"123\"}");
		
		List<Address> list = JSONArray.toList(jsonArray, Address.class);
		
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		String jsonStr = "{\"hasBill\":\"0\",\"headPic\":\"http://image.5i5j.com/images/20170904/f2a91e344eae4aef8fc1c1f23f42db2e.jpg\",\"lastPhase\":\"0\",\"mobile\":\"18612273002\",\"nickName\":\"18612273002\"}";
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		System.out.println(jsonObject.get("credentialsNo"));
	}

	@Data
	public static class Address {
			private String road;
			private String streate;
			private String provience;
			private String no;
	}

	@Data
	public static class RiskInfo {
		private int riskCode;
		private int riskCodeValue;
	}
}
