package name.katlog.persistence.redis.practice.best;

import redis.clients.jedis.Jedis;

import java.util.LinkedHashMap;

/**
 * Created by fw on 2019/3/13
 */
public class HashRedisBestPractice {
    private Jedis redis;

    /**
     * ·lpush+lpop=Stack（栈）
     * ·lpush+rpop=Queue（队列）
     * ·lpsh+ltrim=Capped Collection（有限集合）
     * ·lpush+brpop=Message Queue（消息队列）
     * */

    /** 消息队列 */

    /** 文章列表 */
    public void articleList(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("title", "标题1");
        map.put("timestamp", "标题1");
        map.put("content", "xxxxx");
        redis.hmset("article:1", map);
    }
}
