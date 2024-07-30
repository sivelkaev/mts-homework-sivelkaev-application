package mts.homework.sivelkaev.application.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.helper.JsonMarshallingHelper;
import mts.homework.sivelkaev.application.kafka.request.CreateAccountRequest;
import mts.homework.sivelkaev.application.kafka.request.UpdateApplicationStatusRequest;
import mts.homework.sivelkaev.application.model.entity.AccountEntity;
import mts.homework.sivelkaev.application.model.repository.AccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAccountEventService {
    private final JsonMarshallingHelper jsonMarshallingHelper;
    private final AccountRepository accountRepository;

    public void consume(String message) {
        CreateAccountRequest req = unmarshallMessage(message);

        var account = AccountEntity.builder()
                .createDate(LocalDate.now())
                .clientId(req.getClientId())
                .applicationId(req.getApplicationId())
                .build();

        accountRepository.save(account);
    }

    private CreateAccountRequest unmarshallMessage(String message) {
        if (StringUtils.isBlank(message)) {
            log.error("Отсутствует входящее сообщение.");
            throw new IllegalArgumentException();
        }

        try {
            return jsonMarshallingHelper.unmarshall(CreateAccountRequest.class, message);
        } catch (Exception e) {
            log.error("Ошибка десериализации сообщения типа {}.", UpdateApplicationStatusRequest.class.getSimpleName(), e);
            throw new IllegalArgumentException(e);
        }
    }
}
