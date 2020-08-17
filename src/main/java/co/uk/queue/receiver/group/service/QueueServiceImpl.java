package co.uk.queue.receiver.group.service;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class QueueServiceImpl implements QueueService, MessageListener {

    private final Path fileLocation = Paths.get("queuemessage.txt");

    @Override
    public List<String> readMessages() throws IOException {

        return Files.readAllLines(Paths.get(fileLocation.toString()));
    }

    @Override
    public void onMessage(Message message) {

        try {
            if (!Files.exists(fileLocation)) {
                Files.createFile(fileLocation);
            }

            Files.writeString(fileLocation, new String(message.getBody()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}