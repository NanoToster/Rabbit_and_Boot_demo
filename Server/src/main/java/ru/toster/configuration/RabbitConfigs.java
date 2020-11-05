package ru.toster.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ivan Rovenskiy
 * 04 November 2020
 */
@Configuration
public class RabbitConfigs {
    private static final String emailSenderQueueName = "email_sender";

    public static final String emailSenderQueueExchangeName = "email_sender_exchange";

    @Bean
    protected Queue queue() {
        return new Queue(emailSenderQueueName, false);
    }

    @Bean
    protected TopicExchange topicExchange() {
        return new TopicExchange(emailSenderQueueExchangeName);
    }

    @Bean
    protected Binding binding(final Queue queue, final TopicExchange topicExchange) {
        return BindingBuilder.bind(queue)
                .to(topicExchange)
                .with("foo.bar.#");
    }

    @Bean
    protected MessageListenerAdapter listenerAdapter() {
        return new MessageListenerAdapter();
    }

    @Bean
    protected SimpleMessageListenerContainer simpleContainer(final ConnectionFactory connectionFactory,
                                                             final MessageListenerAdapter listenerAdapter) {
        final SimpleMessageListenerContainer simpleContainer = new SimpleMessageListenerContainer();
        simpleContainer.setConnectionFactory(connectionFactory);
        simpleContainer.setMessageListener(listenerAdapter);
        simpleContainer.setQueueNames(emailSenderQueueName);
        return simpleContainer;
    }
}