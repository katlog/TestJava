package name.katlog.persistence.redis.embedded;

import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;

/**
 * Created by fw on 2019/5/16
 */
public class EmbeddedRedis {
    public static void main(String[] args) throws InterruptedException {
        RedisServer redisServer =RedisServer.builder()
                . redisExecProvider(RedisExecProvider.defaultProvider())
                . port(6379)
                . slaveOf("locahost", 6378)
                . configFile("/path/to/your/redis.conf")
                . build();
        redisServer.start();

        Thread.sleep(10000);

        redisServer.stop();

    }
}
