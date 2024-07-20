package mts.homework.sivelkaev.application.service;

import mts.homework.sivelkaev.application.controller.dto.CreateApplicationRequest;

public interface ApplicationService {
    /**
     * @param req - данные заявки
     *
     * @return Номер заявки
     */
    String createApplication(CreateApplicationRequest req);
}
