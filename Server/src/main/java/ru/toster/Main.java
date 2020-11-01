package ru.toster;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Ivan Rovenskiy
 * 01 November 2020
 */
public class Main {
    public static final String emailSenderQueue = "email_sender";
    public static void main(String[] args) {
        final ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try (final Connection connection = connectionFactory.newConnection();
             final Channel channel = connection.createChannel()) {

            channel.queueDeclare(emailSenderQueue, false, false, false, null);
            final String message = "Hello!";
            channel.basicPublish("", emailSenderQueue, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

        } catch (final IOException | TimeoutException ex) {
            throw new RuntimeException(ex);
        }
    }
}
