
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.james.cache.utils.JedisUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.junit.runners.MethodSorters;

/**
* Redis业务测试用例
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRedis {



	@Resource
	private JedisUtils jedis;

	@Test
	public void test() {
		jedis.set("aaaaaa1","xxxxxx");
		System.out.println(jedis.get("aaaaaa1"));
		test1();
		test2();
		test3();
		test4();
		test5();

	}

	private void pushUserOrder(String orderId, String money) {
		HashMap<String,String> userOrder = new HashMap<String,String>();
		userOrder.put("orderId",orderId);
		userOrder.put("money", money);
		userOrder.put("time","2018-01-01");
//		userOrder.put("time",new Date().getTime());
		jedis.hmset("order:" + orderId, userOrder);
	}

	private void printOrderDetail(String orderId) {
		Map<String,String> userOrder = jedis.hgetAll(orderId);
		System.out.print("Details of Order:");
		System.out.print(" orderId:" + userOrder.get("orderId"));
		System.out.print(" money:" + userOrder.get("money"));
		System.out.print(" time:" + userOrder.get("time"));
		System.out.println();

	}

	public void test1() {
		System.out.println("——————————Test1——————————");
		pushUserOrder("1","36.6");
		pushUserOrder("2","38.6");
		pushUserOrder("3","39.6");
		printOrderDetail("order:" + "1");
		printOrderDetail("order:" + "2");
		printOrderDetail("order:" + "3");
	}

	public void test2() {
		System.out.println("——————————Test2——————————");
		jedis.del("user:1:order");
		jedis.lpush("user:1:order","order:1", "order:2", "order:3");
		System.out.println(jedis.lrange("user:1:order",0,-1));
	}

	public void test3() {
		System.out.println("——————————Test3——————————");
		pushUserOrder("4","40.6");
		printOrderDetail("order:" + "4");
	}

	public void test4() {
		System.out.println("——————————Test4——————————");
		jedis.lpush("user:1:order","order:4");
		System.out.println(jedis.lrange("user:1:order",0,-1));
	}

	public void test5() {
		System.out.println("——————————Test5——————————");
		List<String> allOrders = jedis.lrange("user:1:order",0 ,-1);
		for(String order:allOrders){
//			System.out.println(order);
			printOrderDetail(order);
		}

	}


}