package com.springdemo.test;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.springdemo.pojo.Plugin;
import com.springdemo.service.PluginService;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath*:/conf/spring-servlet.xml"})
public class TestSpring {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Resource
    private PluginService pluginService = null;
	
	@Before
    public void before() {
        //在运行测试之前的业务代码
		LOG.info("before");
    }
	
	@Test
    public void test() {
		LOG.info("test");
        Plugin plugin = pluginService.getPluginById(1);
        LOG.info("plugin=" + JSON.toJSONString(plugin));
    }
	
	@After
    public void after() {
        //在测试完成之后的业务代码
		LOG.info("after");
    }
	
/*    @Resource
    private IUserService userService = null;

    // @Before
    // public void before() {
    // ac = new ClassPathXmlApplicationContext("applicationContext.xml");
    // userService = (IUserService) ac.getBean("userService");
    // }

    @Test
    public void test() {
        User user = userService.getUserById(1);
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        logger.info(JSON.toJSONString(user));
    }*/
}
