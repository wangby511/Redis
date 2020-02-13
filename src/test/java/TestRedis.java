
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
	}

}