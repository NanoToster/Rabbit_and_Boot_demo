package ru.toster;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.toster.Receiver;
import ru.toster.configuration.RabbitConfigs;

import java.util.concurrent.TimeUnit;

/**
 * @author Ivan Rovenskiy
 * 04 November 2020
 */
@Component
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public Runner(RabbitTemplate rabbitTemplate, Receiver receiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfigs.emailSenderQueueExchangeName, "foo.bar.baz", "hello!");
        final boolean await = receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
        System.out.println("Result: " + await);
    }
}
