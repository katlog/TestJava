package name.katlog.persistence.redis.practice.best;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by fw on 2019/3/13
 */
public class ZSetRedisBestPractice {
    private final String key = "user:ranking:2016_03_15";
    private final String memberMike = "mike";
    private Jedis jedis;


    /** 1、排行系统 */
    /** 1.1 添加用户赞数 */
    public void _1(){
        // 	例如用户mike上传了一个视频，并获得了3个赞，可用有序集合的zadd和zincrby功能：
        // 	zadd user:ranking:2016_03_15 mike 3
        jedis.zadd(key, 3, memberMike);
        // 	若后再获得一个赞，可用zincrby：
        // zincrby user:ranking:2016_03_15 mike 1
        jedis.zincrby(key, 1, memberMike);
    }
    /** 1.2 取消用户赞数 */
    public void _2(){
        // 由于各种原因（例如用户注销、用户作弊）需要将用户删除，此时需要将用户从榜单中删除掉，可用zrem。例如删除成员tom：
        // zrem user:ranking:2016_03_15 mike
        jedis.zrem(key, memberMike);
    }
    /** 1.3 展示用户赞数最多十人 */
    public void _3(){
        //	此功能用zrevrange命令实现：
        // zrevrangebyrank user:ranking:2016_03_15 0 9
        Set<String> members = jedis.zrevrange(key, 0, 9);
    }
    /** 1.4 展示用户信息及用户分数 */
    public void _4(){
        // 此功能将用户名作为键后缀，将用户信息保存在哈希类型中，至于用户的分数和排名可用zscore和zrank两个功能：
        // hgetall user:info:tom
        // zscore user:ranking:2016_03_15 mike
        Double zscore = jedis.zscore(key, memberMike);
        // zrank user:ranking:2016_03_15 mike
        Long zrank = jedis.zrank(key, memberMike);
    }

}
