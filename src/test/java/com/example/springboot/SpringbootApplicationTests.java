package com.example.springboot;

import com.example.springboot.service.ITestSpringBootService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private ITestSpringBootService iTestSpringBootService;

	@Test
	public void contextLoads() {
        List<Map<String, Object>> hashMaps = iTestSpringBootService.testDaoOperation();
        System.out.println(hashMaps.toString());
    }

}
