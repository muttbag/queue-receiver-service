package co.uk.queue.receiver.group.service;

import java.io.IOException;
import java.util.List;

public interface QueueService {
    List<String> readMessages() throws IOException;
}
