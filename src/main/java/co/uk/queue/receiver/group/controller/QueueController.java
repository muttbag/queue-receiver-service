package co.uk.queue.receiver.group.controller;

import co.uk.queue.receiver.group.aws.SQS;
import co.uk.queue.receiver.group.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    QueueService queueService;

    @Autowired
    SQS sqs;

    @GetMapping(value = "/messages/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMessage() throws IOException {

        return queueService.readMessagesFromRabbitMQ();
    }

    @GetMapping(value = "/messages/posttosqs", produces = MediaType.APPLICATION_JSON_VALUE)
    public String sendToSQS() throws IOException {

        sqs.send("Test message");

        return "Message sent to SQS";
    }
}
