
/**  
 * @Title: TestHamcrest.java
 * @Package: org.person.dfw.junit
 * @author: katlog
 * @date: 2017年5月8日 下午1:37:10
 * @version: V1.0  
 */ 
package name.katlog.junit;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class TestHamcrest {
    class User implements Serializable{
        protected int userId;        public int getUserId() { return userId;} public void setUserId(int userId) { this.userId = userId;}
        protected String userName;   public String getUserName() { return userName;} public void setUserName(String userName) {this.userName = userName;}
        protected String password;   public String getPassword() { return password; } public void setPassword(String password) {this.password = password;}
        protected Date lastVisit;    public Date getLastVisit() { return lastVisit;} public void setLastVisit(Date lastVisit) { this.lastVisit = lastVisit;}
        protected String lastIp;     public String getLastIp() { return lastIp;}public void setLastIp(String lastIp) { this.lastIp = lastIp;}
        private int credits;         public int getCredits() {return credits;}  public void setCredits(int credits) {this.credits = credits;}
        public User(){} public User(String userName,String password){ this.userName = userName; this.password = password; }
    }
    
    private User tom;
    private User john;
    @Before
    public void init() {
        tom = new User("tom","1234");
        tom.setCredits(100);
        john = new User("john","1234");
        john.setCredits(50);
    }

    @Test
    public void testAssert() {

        // 测试user1的访问IP是否等于user2的访问IP
        assertEquals(tom.getLastIp(), tom.getLastIp());

        // 测试user1是否不为空
        assertNotNull(tom);

        // 测试user1和user2引用不是同一个对象。
        assertNotSame(tom, john);
        
        // 测试user1的用户名是否包含"om"
        assertThat( tom.getUserName(), containsString( "om" ) );

    }
    
    @Test
    public void testArrayAssert() {
         String users[] = new String[]{"tom","john","tony"};
         assertArrayEquals(new String[]{"tom","john","tony"}, users);
    }
    
    @Test
    public void testAasertThat() {

        // 1. 数值匹配符
        // 测试变量的值是否大于指定值
        assertThat(tom.getCredits(), greaterThan(50));
        // 测试变量的值小于指定值时
        assertThat(tom.getCredits(), lessThan(150));
        // 测试变量的值大于等于指定值
        assertThat(tom.getCredits(), greaterThanOrEqualTo(100));
        // 测试变量的值小于等于指定值
        assertThat(tom.getCredits(), lessThanOrEqualTo(100));

        // 测试所有条件必须都成立
        assertThat(tom.getCredits(), allOf(greaterThan(50), lessThan(150)));
        // 测试只要有一个条件成立
        assertThat(tom.getCredits(), anyOf(greaterThan(50), lessThan(200)));
        // 测试无论什么条件成立
        assertThat(tom.getCredits(), anything());
        // 测试变量的值等于指定值
        assertThat(tom.getCredits(), is(100));
        // 测试和is相反，变量的值不等于指定值
        assertThat(tom.getCredits(), not(50));

        // 2. 字符串匹配符
        String url = "http://www.baobaotao.com";
        // 测试字符串变量中包含指定字符串
        assertThat(url, containsString("baobaotao.com"));
        // 测试字符串变量以指定字符串开头
        assertThat(url, startsWith("http://"));
        // 测试字符串变量以指定字符串结尾
        assertThat(url, endsWith(".com"));
        // 测试字符串变量等于指定字符串
        assertThat(url, equalTo("http://www.baobaotao.com"));
        // 测试字符串变量在忽略大小写的情况下等于指定字符串
        assertThat(url, equalToIgnoringCase("http://www.BAOBAOTAO.com"));
        // 测试字符串变量在忽略头尾任意空格的情况下等于指定字符串
        assertThat(url, equalToIgnoringWhiteSpace(" http://www.baobaotao.com "));

        // 2. 集合匹配符
        List<User> users = new ArrayList();
        users.add(tom);
        users.add(john);
        // 测试变量中是否含有指定元素
        assertThat(users, hasItem(tom));
        assertThat(users, hasItem(john));

        // 3. Map匹配符
        Map<String, User> userMap = new HashMap();
        userMap.put(tom.getUserName(), tom);
        userMap.put(john.getUserName(), john);
        // 测试Map变量中是否含有指定键值对
        assertThat(userMap, hasEntry(tom.getUserName(), tom));
        // 测试Map变量中是否含有指定键
        //assertThat(userMap, hasKey(john.getUserName()));
        // 测试Map变量中是否含有指定值
        //assertThat(userMap, hasValue(john));
    }
}
