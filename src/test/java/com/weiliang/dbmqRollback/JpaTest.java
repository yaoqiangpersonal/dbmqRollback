package com.weiliang.dbmqRollback;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.weiliang.dbmqRollback.dao.DemoRepository;
import com.weiliang.dbmqRollback.pojo.Demo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("jpa test")
public class JpaTest {
	
	@Autowired
	private DemoRepository repository;
	
	@Test
	@Rollback(value = false)
	public void add() {
		Demo d = new Demo();
		d.setAge(10);
		d.setName("aa");
		repository.save(d);
	}

}
