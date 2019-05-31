package name.katlog.persistence.redis.practice.best;

import redis.clients.jedis.Jedis;

/**
 * Created by fw on 2019/3/13
 */
public class StringRedisBestPractice {

    private Jedis redis;

    /** 1、 缓存功能 */
    public <T> T cache(long id) {
        // 定义键
        String userRedisKey = "user:info:" + id;
        // 从Redis获取值
        String value = redis.get(userRedisKey);
        if (value != null) {
            // 将值进行反序列化为UserInfo并返回结果
            T userInfo = deserialize(value);
            return userInfo;
        }
        return null;
    }

    private <T> T deserialize(String value) {
        return null;
    }

    /** 2、 计数 */
   public long incrVideoCounter(long id) {
       String key = "video:playCount:" + id;
        return redis.incr(key);
    }

    /** 3、限制次数 */
    public void limit(){
        String phoneNum = "138xxxxxxxx";
        String key = "shortMsg:limit:" + phoneNum;
        // SET key value EX 60 NX
        String  isExists = redis.set(key,"1","NX","EX",60L);
        if (isExists != null || redis.incr(key) <= 5) {
            // 通过
        } else {
            // 限速
        }
    }
}
