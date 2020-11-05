package ru.toster;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Ivan Rovenskiy
 * 06 November 2020
 */
@Component
public class MessageListener {
    @RabbitListener(queues = "email_sender")
    public void listenMessage(final String message) {
        System.out.println("Email sender: " + message);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

