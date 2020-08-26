package co.uk.queue.receiver.group.service;

import org.apache.http.MethodNotSupportedException;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;
import java.util.List;

public interface QueueService extends MessageListener {
    List<String> readMessagesFromRabbitMQ() throws IOException;
    MethodNotSupportedException postMessageToSQS(final String message) throws IOException;
}
