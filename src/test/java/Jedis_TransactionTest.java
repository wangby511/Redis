import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class Jedis_TransactionTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("开启Redis事务");
        Transaction transaction = jedis.multi();
        transaction.set("userName", "xxx");
        transaction.set("age", "xxx");
        transaction.set("city", "xxx");
        transaction.get("userName");
        transaction.incrBy("userName", 5);
        transaction.incrBy("age", 5);
        transaction.exec();
        System.out.println("Redis end");
        System.out.println("userName："+jedis.get("userName"));
        System.out.println("age："+jedis.get("age"));
        System.out.println("city："+jedis.get("city"));
    }
}
