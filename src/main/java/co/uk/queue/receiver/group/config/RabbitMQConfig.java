package co.uk.queue.receiver.group.config;

import co.uk.queue.receiver.group.service.QueueServiceImpl;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queue() {
        return new Queue("MyQueue", true);
    }

    //create MessageListenerContainer using default connection factory
//    @Bean
//    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//        simpleMessageListenerContainer.setQueues(queue());
//        simpleMessageListenerContainer.setMessageListener(new RabbitMQListner());
//        return simpleMessageListenerContainer;
//
//    }

    //create custom connection factory
	@Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("35.177.98.192");
		cachingConnectionFactory.setUsername("guest1");
		cachingConnectionFactory.setPassword("guest1");
		return cachingConnectionFactory;
	}

    //create MessageListenerContainer using custom connection factory
	@Bean
    MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(new QueueServiceImpl());
		return simpleMessageListenerContainer;

	}

}
