package ru.toster;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.toster.configuration.RabbitConfigs;

/**
 * @author Ivan Rovenskiy
 * 04 November 2020
 */
@Component
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;

    public Runner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("Sending message: " + i);
            rabbitTemplate.convertAndSend(RabbitConfigs.emailSenderQueueExchangeName, "foo.bar.baz", "" + i);
            Thread.sleep(200);
        }
    }
}
