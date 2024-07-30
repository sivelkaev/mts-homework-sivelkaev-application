package mts.homework.sivelkaev.application.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.kafka.service.CreateAccountEventService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateAccountEventConsumer {
    private final CreateAccountEventService createAccountEventService;

    @KafkaListener(topics = "create.account",
                   groupId = "application")
    public void consume(String message) {
        log.info("Принято сообщение из Kafka {}.", message);
        createAccountEventService.consume(message);
    }
}
