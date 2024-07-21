package mts.homework.sivelkaev.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.controller.dto.CreateApplicationRequest;
import mts.homework.sivelkaev.application.helper.JsonMarshallingHelper;
import mts.homework.sivelkaev.application.kafka.KafkaProducerMethod;
import mts.homework.sivelkaev.application.kafka.request.ApplicationStartFlowRequest;
import mts.homework.sivelkaev.application.kafka.request.UpdateApplicationStatusRequest;
import mts.homework.sivelkaev.application.model.entity.ApplicationEntity;
import mts.homework.sivelkaev.application.model.repository.ApplicationRepository;
import mts.homework.sivelkaev.application.service.ApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final ApplicationRepository applicationRepository;
    private final JsonMarshallingHelper jsonMarshallingHelper;
    private final KafkaProducerMethod kafkaProducerMethod;

    @Override
    public String createApplication(CreateApplicationRequest req) {
        ApplicationEntity applicationEntity = ApplicationEntity.builder()
                .createDate(LocalDate.now())
                .type(req.getType())
                .status("NEW")
                .firstName(req.getFirstName())
                .middleName(req.getMiddleName())
                .lastName(req.getLastName())
                .birthDate(LocalDate.parse(req.getBirthDate(), FORMATTER))
                .passportNumber(req.getPassportNumber())
                .build();
        applicationRepository.save(applicationEntity);

        ApplicationStartFlowRequest startFlowReq = ApplicationStartFlowRequest.builder()
                .id(applicationEntity.getId())
                .type(applicationEntity.getType())
                .status(applicationEntity.getStatus())
                .firstName(applicationEntity.getFirstName())
                .middleName(applicationEntity.getMiddleName())
                .lastName(applicationEntity.getLastName())
                .birthDate(applicationEntity.getBirthDate().format(FORMATTER))
                .passportNumber(applicationEntity.getPassportNumber())
                .build();
        var message = jsonMarshallingHelper.marshall(startFlowReq);
        kafkaProducerMethod.send("application.flow.start", message);

        return "Создана заявка " + applicationEntity.getId() + " на открытие счета.";
    }

    @Override
    @Transactional
    public void updateApplicationStatus(UpdateApplicationStatusRequest req) {
        applicationRepository.findById(req.getId())
                .ifPresentOrElse(application -> {
                            if (!application.getStatus().equals(req.getStatus())) {
                                application.setStatus(req.getStatus());
                                log.info("Статус заявки {} обновлен на {}.", req.getId(), req.getStatus());
                            }
                        },
                        () -> {
                            log.error("Заявка {} не найдена.", req.getId());
                            throw new IllegalArgumentException();
                        });
    }
}
