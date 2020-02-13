import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisJava {

    private static String ADDR = "192.169.4.65";
    private static int PORT = 6379;
    private static String AUTH = "123456";
    private static int MAX_ACTIVE = 1024;
    private static int MAX_IDLE = 200;
    private static int MAX_WAIT = 10000;
    private static int TIMEOUT = 10000;
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    public static final int DEFAULT_DATABASE = 0;

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");

        System.out.println("连接成功");

        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());

        jedis.set("runoobkey", "www.runoob.com");

        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));


        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }


    }
}