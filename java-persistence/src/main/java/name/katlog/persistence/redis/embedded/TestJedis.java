package name.katlog.persistence.redis.embedded;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.junit.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;
import redis.embedded.RedisCluster;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.util.JedisUtil;

import java.io.Serializable;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by fw on 2019/9/23
 */
@Log4j
public class TestJedis {


    public static class SomeIntegrationTestThatRequiresRedis {
        private RedisCluster cluster;
        private Set<String> jedisSentinelHosts;

        @Before
        public void setup() throws Exception {
            //creates a cluster with 3 sentinels, quorum size of 2 and 3 replication groups, each with one master and one slave
            cluster = RedisCluster.builder().ephemeral().sentinelCount(3).quorumSize(2)
                    .replicationGroup("master1", 1)
                    .replicationGroup("master2", 1)
                    .replicationGroup("master3", 1)
                    .build();
            cluster.start();

            //retrieve ports on which sentinels have been started, using a simple Jedis utility class
            jedisSentinelHosts = JedisUtil.sentinelHosts(cluster);
        }

        @Test
        public void test() throws Exception {
            // testing code that requires redis running
            JedisSentinelPool pool = new JedisSentinelPool("master1", jedisSentinelHosts);
        }

        @After
        public void tearDown() throws Exception {
            cluster.stop();
        }
    }


    public static final RedisServer REDIS_SERVER = RedisServer.builder()
            .redisExecProvider(RedisExecProvider.defaultProvider())
            .setting("maxheap 200m")
            .setting("bind 127.0.0.1")
            .port(6379)
            // .configFile("/path/to/your/redis.conf")
            .build();


    public static final RedisServer REDIS_SERVER_SLAVE1 = RedisServer.builder()
            .redisExecProvider(RedisExecProvider.defaultProvider())
            .setting("maxheap 200m")
            .port(6378)
            .slaveOf("localhost", 6379)
            .build();

    @BeforeClass
    public static void tearUp() {
        REDIS_SERVER.start();
        REDIS_SERVER_SLAVE1.start();
    }

    @AfterClass
    public static void tearDown() {
        REDIS_SERVER.stop();
        REDIS_SERVER_SLAVE1.stop();
    }


    @Test
    public void _common() {
        //1. 生成一个 Jedis对象，这个对象负责和指定 Redis实例进行通信
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2. jedis执行 set操作
        jedis.set("hello", "world");
        //3. jedis执行 get操作 , value="world"
        String value = jedis.get("hello");

        String setResult = jedis.set("hello", "world");
        String getResult = jedis.get("hello");
        System.out.println(setResult);
        System.out.println(getResult);

        assertEquals("world", value);
    }

    @Test
    public void _useJedis() {
        Jedis jedis = null;
        try {
            jedis = new Jedis("127.0.0.1", 6379);
            jedis.get("hello");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Test
    public void _5dataType() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        // 1.string
        // 输出结果： OK
        jedis.set("hello", "world");
        // 输出结果： world
        jedis.get("hello");
        // 输出结果： 1
        jedis.incr("counter");
        // 2.hash
        jedis.hset("myhash", "f1", "v1");
        jedis.hset("myhash", "f2", "v2");
        // 输出结果： {f1=v1, f2=v2}
        jedis.hgetAll("myhash");
        // 3.list
        jedis.rpush("mylist", "1");
        jedis.rpush("mylist", "2");
        jedis.rpush("mylist", "3");
        // 输出结果： [1, 2, 3]
        jedis.lrange("mylist", 0, -1);
        // 4.set
        jedis.sadd("myset", "a");
        jedis.sadd("myset", "b");
        jedis.sadd("myset", "a");
        // 输出结果： [b, a]
        jedis.smembers("myset");
        // 5.zset
        jedis.zadd("myzset", 99, "tom");
        jedis.zadd("myzset", 66, "peter");
        jedis.zadd("myzset", 33, "james");
        // 输出结果： [[["james"],33.0], [["peter"],66.0], [["tom"],99.0]]
        jedis.zrangeWithScores("myzset", 0, -1);
    }


    // 俱乐部
    @Data
    @AllArgsConstructor
    public static class Club implements Serializable {
        private int id;             // id
        private String name;                // 名称
        private String info;                // 描述
        private Date createDate;    // 创建日期
        private int rank;           // 排名
        // 相应的 getter setter不占用篇幅
    }

    //序列化工具
    public class ProtostuffSerializer {
        private Schema<Club> schema = RuntimeSchema.createFrom(Club.class);

        public byte[] serialize(final Club club) {
            final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
            try {
                return serializeInternal(club, schema, buffer);
            } catch (final Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            } finally {
                buffer.clear();
            }
        }

        public Club deserialize(final byte[] bytes) {
            try {
                Club club = deserializeInternal(bytes, schema.newMessage(), schema);
                if (club != null) {
                    return club;
                }
            } catch (final Exception e) {
                throw new IllegalStateException(e.getMessage(), e);
            }
            return null;
        }

        private <T> byte[] serializeInternal(final T source, final Schema<T> schema, final LinkedBuffer buffer) {
            return ProtostuffIOUtil.toByteArray(source, schema, buffer);
        }

        private <T> T deserializeInternal(final byte[] bytes, final T result, final Schema<T> schema) {
            ProtostuffIOUtil.mergeFrom(bytes, result, schema);
            return result;
        }
    }

    @Test
    public void _serialize() {
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();

        Jedis jedis = new Jedis("127.0.0.1", 6379);

        String key = "club:1";
        // 定义实体对象
        Club club = new Club(1, "AC", "米兰 ", new Date(), 1);
        // 序列化
        byte[] clubBtyes = protostuffSerializer.serialize(club);
        jedis.set(key.getBytes(), clubBtyes);

        byte[] resultBtyes = jedis.get(key.getBytes());
        // 反序列化 [id=1, clubName=AC, clubInfo=米兰 , createDate=Tue Sep 15 09:53:18 CST // 2015, rank=1]
        Club resultClub = protostuffSerializer.deserialize(resultBtyes);

        assertEquals(club, resultClub);
        System.out.println("resultClub = " + resultClub);
    }

    @Test
    public void pipeline() {
        List<String> keys = Arrays.asList("", "");

        Jedis jedis = new Jedis("127.0.0.1");
        // 1)生成   pipeline对象
        Pipeline pipeline = jedis.pipelined();
        // 2)pipeline执行命令，注意此时命令并未真正执行
        for (String key : keys) {
            pipeline.del(key);
        }
        // 3)执行命令
        pipeline.sync();
    }

    @Test
    public void pipeline_syncAndReturnAll() {
        Jedis jedis = new Jedis("127.0.0.1");
        Pipeline pipeline = jedis.pipelined();
        pipeline.set("hello", "world");
        pipeline.incr("counter");
        List<Object> resultList = pipeline.syncAndReturnAll();
        for (Object object : resultList) {
            System.out.println(object);
        }
    }

    @Test
    public void eval(){
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.set("hello", "world");

        String key = "hello";
        String script = "return redis.call('get',KEYS[1])";
        Object result = jedis.eval(script, 1, key);
        // 打印结果为   world
        System.out.println(result);
    }

    @Test
    public void scriptLoad(){
        Jedis jedis = new Jedis("127.0.0.1");
        String script = "return redis.call('get',KEYS[1])";
        //scriptLoad和evalsha函数要一起用，首先用scriptLoad将脚本加载到Redis中

        String scriptSha = jedis.scriptLoad(script);

        String key = "hello";
        Object result = jedis.evalsha(scriptSha, 1, key);
        // 打印结果为world
        System.out.println(result);


    }
}
