package name.katlog.persistence.redis.embedded;

import lombok.extern.log4j.Log4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;

/**
 * Created by fw on 2019/9/23
 */
@Log4j
public class TestRedisPool {


    private static final RedisServer REDIS_SERVER =RedisServer.builder()
            .redisExecProvider(RedisExecProvider.defaultProvider())
            .setting("maxheap 200m")
            .setting("bind 127.0.0.1")
            .port(6379)
            // .configFile("/path/to/your/redis.conf")
            .build();


    @BeforeClass
    public static void tearUp(){
        REDIS_SERVER.start();
    }

    @AfterClass
    public static void tearDown(){
        REDIS_SERVER.stop();
    }


    @Test
    public void _newPool(){
        // common-pool连接池配置，这里用默认配置，后面小节会介绍具体配置说明
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 初始化   Jedis连接池
        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);

        // 设置最大连接数为默认值的   5倍
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
        // 设置最大空闲连接数为默认值的   3倍
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 3);
        // 设置最小空闲连接数为默认值的   2倍
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
        // 设置开启   jmx功能
        poolConfig.setJmxEnabled(true);
        // 设置连接池没连接后客户端的最大等待时间   (单位为毫秒   )
        poolConfig.setMaxWaitMillis(3000);
    }

    @Test
    public void _commonCommand(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);

        Jedis jedis = null;
        try {
            // 1. 从连接池获取   jedis对象
            jedis = jedisPool.getResource();
            // 2. 执行操作
            jedis.get("hello");
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        } finally {
            if (jedis != null) {
                // 若用JedisPool，close操作不是关闭连接，代表归还连接池
                jedis.close();
            }
        }
    }


}
