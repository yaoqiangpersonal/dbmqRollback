package com.weiliang.dbmqRollback;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.test.context.SpringRabbitTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.channel.PollableAmqpChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.weiliang.dbmqRollback.dao.DemoRepository;
import com.weiliang.dbmqRollback.pojo.Demo;

import lombok.RequiredArgsConstructor;

@SpringJUnitConfig
@SpringRabbitTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableTransactionManagement
public class MqTest {

	@Autowired
	@Qualifier("rollback")
	private MessageChannel channel;
	
	@Autowired
	private DemoRepository repository;

	@Test
	@Transactional
	@Rollback
	public void send() {
		Demo one = new Demo();
		one.setName("one");
		one.setAge(10);
		Demo two = new Demo();
		two.setName("two");
		two.setAge(20);
		repository.save(one);
		channel.send(MessageBuilder.withPayload("aaa").build(), 1000);
		System.out.println(1/0); 
		repository.save(two);
		channel.send(MessageBuilder.withPayload("aaa").build(), 1000);
	}

	@Configuration
	@RequiredArgsConstructor
	@ComponentScan
	public static class IntegrationConfig {

		@Autowired
		private RabbitTemplate template;

		@Bean
		public MessageChannel rollback() {
//			template.setChannelTransacted(true);
			return new PollableAmqpChannel("rollback", template);
		}
	}

}
