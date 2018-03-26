package com.taotao.jedis;

import com.taotao.rest.component.JedisClient;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Package com.taotao.jedis
 * @User 12428
 * @Date 2018/3/24 14:56
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class JedisTest {

//    @Autowired
//    private ApplicationContext context;
    //单机版测试
    @Test
    public void testJedisSingle(){
        //创建一个jedis对象
        Jedis jedis = new Jedis("192.168.241.119",6379);
        //jedis.auth("123456");
        jedis.set("test","hello jedis");
        String string = jedis.get("test");
        System.out.println(string);
        jedis.close();
    }

    //使用连接池
    @Test
    public void testJedisPool(){
        //创建连接池
        //系统中应该是单例的
        JedisPool jedisPool = new JedisPool("192.168.241.119",6379);
        //从连接池中获得一个连接
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get("test");
        System.out.println(result);

        //jedis必须关闭
        jedis.close();
        //系统关闭是关闭连接池
        jedisPool.close();
    }

    @Test
    public void testJedisCluster() throws IOException {
        //创建一个JedisCluster对象
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.241.119",6406));
        nodes.add(new HostAndPort("192.168.241.119",6401));
        nodes.add(new HostAndPort("192.168.241.119",6402));
        nodes.add(new HostAndPort("192.168.241.119",6403));
        nodes.add(new HostAndPort("192.168.241.119",6404));
        nodes.add(new HostAndPort("192.168.241.119",6405));

        //对象JedisCluster在系统中是单例的
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("name","张三");
        jedisCluster.set("value","100");
        String name = jedisCluster.get("name");
        String value = jedisCluster.get("value");
        System.out.println(name);
        System.out.println(value);
        //系统关闭时关闭jedisCluster
        jedisCluster.close();
    }

    @Test
    public void testJedisClientSpring(){
        //创建一个spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-*.xml");
        //从容器中获得JedisClient对象
        JedisClient jedisClient = context.getBean(JedisClient.class);
        //使用jedisClient操作redis
        jedisClient.set("client2", "1000");
        String result = jedisClient.get("client2");
        System.out.println(result);
    }

}
