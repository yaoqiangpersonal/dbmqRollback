package com.weiliang.dbmqRollback.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import com.weiliang.dbmqRollback.dao.DemoRepository;
import com.weiliang.dbmqRollback.pojo.Demo;

@Service
public class DemoServiceImpl implements DemoService {


	@Autowired
	@Qualifier("messageChannel")
	private MessageChannel channel;
	
	@Autowired
	private DemoRepository repository;	

	@Override
	@Transactional
	public void roll() {
		Demo one = new Demo();
		one.setName("one");
		one.setAge(10);
		Demo two = new Demo();
		two.setName("two");
		two.setAge(20);
		repository.save(one);
		channel.send(MessageBuilder.withPayload("aaa").build(), 1000);
//		System.out.println(1/0);

		repository.save(two);
		channel.send(MessageBuilder.withPayload("aaa").build(), 1000);
	}


}
