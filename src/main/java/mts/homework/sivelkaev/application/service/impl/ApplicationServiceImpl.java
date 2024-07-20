package mts.homework.sivelkaev.application.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.controller.dto.CreateApplicationRequest;
import mts.homework.sivelkaev.application.model.entity.ApplicationEntity;
import mts.homework.sivelkaev.application.model.repository.ApplicationRepository;
import mts.homework.sivelkaev.application.service.ApplicationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final ApplicationRepository applicationRepository;

    @Override
    public String createApplication(CreateApplicationRequest req) {
        ApplicationEntity applicationEntity = ApplicationEntity.builder()
                .createDate(LocalDate.now())
                .type(req.getType())
                .firstName(req.getFirstName())
                .middleName(req.getMiddleName())
                .lastName(req.getLastName())
                .birthDate(LocalDate.parse(req.getBirthDate(), FORMATTER))
                .passportNumber(req.getPassportNumber())
                .build();
        applicationRepository.save(applicationEntity);

        return "Создана заявка " + applicationEntity.getId() + " на открытие счета.";
    }
}
