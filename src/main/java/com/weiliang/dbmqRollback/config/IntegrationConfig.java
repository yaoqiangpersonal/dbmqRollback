package com.weiliang.dbmqRollback.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.channel.PollableAmqpChannel;
import org.springframework.messaging.MessageChannel;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class IntegrationConfig {

	private final RabbitTemplate template;

	@Bean
	public MessageChannel messageChannel() {
		template.setChannelTransacted(true);
		return new PollableAmqpChannel("rollback", template);
	}

}
