package name.katlog.persistence.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fw on 2019/9/11
 */
public class TestMongo {

    private MongoClient mongoClient = new MongoClient("localhost", 27017);
    private MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

    /** 1）不通过认证连接mongoDB服务 */
    @Test
    public void client_1(){

        //连接到 mongodb 服务 默认服务器地址为 "localhost" 默认端口号27017
        MongoClient mongoClient = new MongoClient("localhost", 27017);

    }


    /** 2）通过认证连接mongoDB服务 */
    @Test
    public void client_(){

        List<ServerAddress> adds = new ArrayList<>();
        //ServerAddress()两个参数分别为 服务器地址 和 端口
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        adds.add(serverAddress);

        List<MongoCredential> credentials = new ArrayList<>();
        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("username", "databaseName", "password".toCharArray());
        credentials.add(mongoCredential);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(adds, credentials);
    }

    /** 3）连接到数据库 */
    @Test
    public void getDatabase(){
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
    }


    /** -------------------------------------------------------- crud ------------------------------------------------------------------------ */
    /** -------------------------------------------------------- insert ------------------------------------------------------------------------ */
    @Test
    public void getCollection(){

        //获取集合
        MongoCollection<Document> user = mongoDatabase.getCollection("user");
    }

    @Test
    public void insertOneTest(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //创建文档
        Document document = new Document("name","张三")
                .append("sex", "男")
                .append("age", 18);
        //插入一个文档
        collection.insertOne(document);
    }

    //插入多个文档
    @Test
    public void insertManyTest(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //要插入的数据
        List<Document> list = new ArrayList<>();
        for(int i = 1; i <= 3; i++) {
            Document document = new Document("name", "张三")
                    .append("sex", "男")
                    .append("age", 18);
            list.add(document);
        }
        //插入多个文档
        collection.insertMany(list);
    }
    /** -------------------------------------------------------- insert ------------------------------------------------------------------------ */

    /** -------------------------------------------------------- delete ------------------------------------------------------------------------ */
    //删除与筛选器匹配的单个文档
    @Test
    public void deleteOneTest(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //申明删除条件
        Bson filter = Filters.eq("age",18);
        //删除与筛选器匹配的单个文档
        collection.deleteOne(filter);
    }


    //删除与筛选器匹配的所有文档
    @Test
    public void deleteManyTest(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //申明删除条件
        Bson filter = Filters.eq("age",18);
        //删除与筛选器匹配的所有文档
        collection.deleteMany(filter);
    }
    /** -------------------------------------------------------- delete ------------------------------------------------------------------------ */


    /** -------------------------------------------------------- update ------------------------------------------------------------------------ */
    //修改单个文档
    @Test
    public void updateOneTest(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //修改过滤器
        Bson filter = Filters.eq("name", "张三");
        //指定修改的更新文档
        Document document = new Document("$set", new Document("age", 100));
        //修改单个文档
        collection.updateOne(filter, document);
    }


    //修改多个文档
    @Test
    public void updateManyTest(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //修改过滤器
        Bson filter = Filters.eq("name", "张三");
        //指定修改的更新文档
        Document document = new Document("$set", new Document("age", 100));
        //修改多个文档
        collection.updateMany(filter, document);
    }
    /** -------------------------------------------------------- update ------------------------------------------------------------------------ */



    /** -------------------------------------------------------- update ------------------------------------------------------------------------ */
    //查找集合中的所有文档
    @Test
    public void findTest1(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //查找集合中的所有文档
        FindIterable findIterable = collection.find();
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
    //指定查询过滤器查询
    @Test
    public void FilterfindTest(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //指定查询过滤器
        Bson filter = Filters.eq("name", "张三");
        //指定查询过滤器查询
        FindIterable findIterable = collection.find(filter);
        MongoCursor cursor = findIterable.iterator();
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }


    //取出查询到的第一个文档
    @Test
    public void findTest(){
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("user");
        //查找集合中的所有文档
        FindIterable findIterable = collection.find();
        //取出查询到的第一个文档
        Document document = (Document) findIterable.first();
        //打印输出
        System.out.println(document);
    }

    /** -------------------------------------------------------- update ------------------------------------------------------------------------ */

    /** -------------------------------------------------------- CRUD ------------------------------------------------------------------------ */
}
