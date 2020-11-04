package ru.toster;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author Ivan Rovenskiy
 * 01 November 2020
 */
public class Consumer {
    public static final String emailSenderQueue = "email_sender";

    public static void main(String[] args) throws Exception {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        final Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(emailSenderQueue, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            final String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "', ConsumerTag: " + consumerTag);
        };
        final String generatedConsumerTag = channel.basicConsume(
                emailSenderQueue, true, deliverCallback, consumerTag -> {
                });

        System.out.println("generatedConsumerTag: " + generatedConsumerTag);

    }
}
