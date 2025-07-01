package springboot_lovepreetsingh.spring.boot.controller;


import io.prometheus.client.Counter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerController {
    private final Counter kafkaEventsCounter;

    public ConsumerController(){
        kafkaEventsCounter = Counter.build()
                .name("Kafka_events_received_total")
                .help("Total number of kafka events received")
                .register();
    }


    @KafkaListener(topics = "testy",groupId = "metrics-consumer-group")
    public void listen(String eventData){
        //process the received event
        System.out.println("Received event:- "+eventData);

        //Increment the kafka events counter
        kafkaEventsCounter.inc();

    }
}
