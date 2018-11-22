
/**  
 * @Title: TestRedis.java
 * @Package: org.dfw.test.redis
 * @Description: TODO
 * @author: chensl
 * @date: 2017年2月23日 下午4:28:06
 * @version: V1.0  
 */ 
package dfw.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @moudle: TestRedis 
 * @version:v1.0
 * @author: chensl
 * @date: 2017年2月23日 下午4:28:06
 *
 */
public class TestRedis {
	private static Jedis jedis = new Jedis("localhost");
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println(jedis.ping());
//		jedis.set("fengwei", "niubi1");
		System.out.println(jedis.get("fengwei"));
		
	}
	
	@Test
	public void testRedisList(){
		jedis.lpush("tutorial-list", "Redis"); 
		jedis.lpush("tutorial-list", "Mongodb"); 
		jedis.lpush("tutorial-list", "Mysql"); 
		// Get the stored data and print it 
		List<String> list = jedis.lrange("tutorial-list", 0 ,10); 
		System.out.println(list.size());
		for(int i = 0; i<list.size(); i++) { 
			System.out.println("Stored string in redis:: "+list.get(i)); 
		} 
	}
	
	@Test
	public void testRedisList1(){
		Set<String> keys = jedis.keys("*");
		for (String string : keys) {
			System.out.println(string);
		}
	}
}
