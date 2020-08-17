package co.uk.queue.receiver.group.controller;

import co.uk.queue.receiver.group.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    QueueService queueService;

    @GetMapping(value = "/messages/read", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMessage() throws IOException {

        return queueService.readMessages();
    }
}
