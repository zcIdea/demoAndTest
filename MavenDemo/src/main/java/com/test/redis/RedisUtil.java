package com.test.redis;

import redis.clients.jedis.Jedis;

import redis.clients.jedis.JedisPool;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @功能：Redis通用类
 * 
 * @作者：郑海树
 * 
 * @日期：2016－1－17
 * 
 * 为了避免上述忘记释放连接的情况，咱们可以把连接的代码封装到RedisUtil类里，在RedisUtil内部实现释放连接的逻辑，
 * 这样程序员在外部调用的时候不需要释放也无法释放，从而达到了安全连接的目的。 
 * 
 */

public class RedisUtil {

	private JedisPool pool = null;

	/**
	 * 
	 * @功能：带参数的构造函数
	 * 
	 * @参数：host，主机名或主机IP
	 * 
	 * @参数：port，端口
	 * 
	 * @参数：password，访问Redis数据库的密码
	 * 
	 */

	public RedisUtil(String host, int port, String password) {

		if (pool == null) {

			JedisPoolConfig config = new JedisPoolConfig();

			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；

			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxTotal个jedis实例，则此时pool的状态为exhausted(耗尽)。

			 config.setMaxTotal(1);//高版本的(commons-pool2-2.3.jar)
//			config.setMaxActive(20);
			//最大空闲连接数, 默认8个
			config.setMaxIdle(5);
//			config.setMaxWait(1000l);

			if(password==null||"".equals(password)){
				pool = new JedisPool(config, host, port, 60000);
			}else{
				pool = new JedisPool(config, host, port, 60000, password);
			}
			

		}

	}

	/**
	 * 
	 * @功能：通过Redis的key获取值，并释放连接资源
	 * 
	 * @参数：key，键值
	 * 
	 * @返回： 成功返回value，失败返回null
	 * 
	 */

	public String get(String key) {

		Jedis jedis = null;

		String value = null;

		try {

			jedis = pool.getResource();

			value = jedis.get(key);

		} catch (Exception e) {

			pool.returnBrokenResource(jedis);

			e.printStackTrace();

		} finally {

			if (null != pool) {

				pool.returnResource(jedis);

			}

		}

		return value;

	}

	/**
	 * 
	 * @功能：向redis存入key和value（如果key已经存在 则覆盖），并释放连接资源
	 * 
	 * @参数：key，键
	 * 
	 * @参数：value，与key对应的值
	 * 
	 * @返回：成功返回“OK”，失败返回“0”
	 * 
	 */

	public String set(String key, String value) {

		Jedis jedis = null;

		try {

			jedis = pool.getResource();

			return jedis.set(key, value);

		} catch (Exception e) {

			pool.returnBrokenResource(jedis);

			e.printStackTrace();

			return "0";

		} finally {

			if (null != pool) {

				pool.returnResource(jedis);

			}

		}

	}
	
	public static void main(String[] args) {

        String host = "127.0.0.1";
        String password = "";
        int port = 6379;

        RedisUtil redisUtil = new RedisUtil(host, port, password);

        for(int i = 0; i < 10; i++) {

            redisUtil.set("foo", "bar");

            System.out.println("第" + (i+1) + "个连接, 得到的值为" + redisUtil.get("foo"));

        }

    }

}