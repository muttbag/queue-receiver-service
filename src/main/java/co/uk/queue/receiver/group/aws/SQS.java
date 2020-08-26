package co.uk.queue.receiver.group.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class SQS {

    //private static final Logger logger = LoggerFactory.getLogger(SpringCloudSQS.class);

    static final String QUEUE_NAME = "RS-SQS-standard-queue";

    /*
     * CountDownLatch is added to wait for messages
     * during integration test
     */
//    CountDownLatch countDownLatch;
//
//    public void setCountDownLatch(CountDownLatch countDownLatch) {
//        this.countDownLatch = countDownLatch;
//    }

    @Autowired
    QueueMessagingTemplate queueMessagingTemplate;

//    @SqsListener(QUEUE_NAME)
//    public void receiveMessage(String message, @Header("SenderId") String senderId) {
//        logger.info("Received message: {}, having SenderId: {}", message, senderId);
//        if (countDownLatch != null) {
//            countDownLatch.countDown();
//        }
//    }

    public void send(Object message) {
        queueMessagingTemplate.convertAndSend(QUEUE_NAME, message);
    }
}