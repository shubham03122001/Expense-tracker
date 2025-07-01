package springboot_lovepreetsingh.spring.boot.controller;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private static final String TOPIC_NAME = "testy";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/event")
    public void sendEventToKafka(@RequestBody String eventData) {
        kafkaTemplate.send(TOPIC_NAME, "userEvent", eventData)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.err.println("Failed to send message: " + ex.getMessage());
                    } else {
                        System.out.println("Message sent successfully, offset: " + result.getRecordMetadata().offset());
                    }
                });
    }
}
