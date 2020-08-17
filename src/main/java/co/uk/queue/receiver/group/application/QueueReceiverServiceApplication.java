package co.uk.queue.receiver.group.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("co.uk.queue.receiver.group.*")
public class QueueReceiverServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueReceiverServiceApplication.class, args);
    }
}
