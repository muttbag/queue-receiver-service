package co.uk.queue.receiver.group.service;

import co.uk.queue.receiver.group.aws.SQS;
import org.apache.http.MethodNotSupportedException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    SQS sqs;

    private final Path fileLocation = Paths.get("queuemessage.txt");

    @Override
    public List<String> readMessagesFromRabbitMQ() throws IOException {

        return Files.readAllLines(Paths.get(fileLocation.toString()));
    }

    @Override
    public MethodNotSupportedException postMessageToSQS(String message) throws IOException {
        return new MethodNotSupportedException("Method not implimented yet");
    }

    @Override
    public void onMessage(Message message) {


        try {
            if (!Files.exists(fileLocation)) {
                Files.createFile(fileLocation);
            }

            Files.writeString(fileLocation, System.lineSeparator() + new String( message.getBody()), StandardOpenOption.APPEND);
            sqs.send(new String( message.getBody()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}