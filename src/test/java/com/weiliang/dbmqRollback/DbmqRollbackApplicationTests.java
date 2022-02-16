package com.weiliang.dbmqRollback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weiliang.dbmqRollback.service.DemoService;



@SpringBootTest
class DbmqRollbackApplicationTests {
	
	@Autowired
	private DemoService demoService;

	@Test
	void contextLoads() {
		demoService.roll();
	}

}
