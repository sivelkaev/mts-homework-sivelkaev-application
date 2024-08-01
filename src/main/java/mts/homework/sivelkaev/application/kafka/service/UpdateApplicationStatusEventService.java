package mts.homework.sivelkaev.application.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.helper.JsonMarshallingHelper;
import mts.homework.sivelkaev.application.kafka.request.UpdateApplicationStatusRequest;
import mts.homework.sivelkaev.application.service.ApplicationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateApplicationStatusEventService {
    private final JsonMarshallingHelper jsonMarshallingHelper;
    private final ApplicationService applicationService;

    public void consume(String message) {
        UpdateApplicationStatusRequest req = unmarshallMessage(message);
        applicationService.updateApplicationStatus(req);
    }

    private UpdateApplicationStatusRequest unmarshallMessage(String message) {
        if (StringUtils.isBlank(message)) {
            log.error("Отсутствует входящее сообщение.");
            throw new IllegalArgumentException();
        }

        try {
            return jsonMarshallingHelper.unmarshall(UpdateApplicationStatusRequest.class, message);
        } catch (Exception e) {
            log.error("Ошибка десериализации сообщения типа {}.", UpdateApplicationStatusRequest.class.getSimpleName(), e);
            throw new IllegalArgumentException(e);
        }
    }
}
