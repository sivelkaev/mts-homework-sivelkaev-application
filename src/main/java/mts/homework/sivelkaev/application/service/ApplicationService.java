package mts.homework.sivelkaev.application.service;

import mts.homework.sivelkaev.application.controller.dto.CreateApplicationRequest;
import mts.homework.sivelkaev.application.kafka.request.UpdateApplicationStatusRequest;

public interface ApplicationService {
    /**
     * @param req - данные заявки
     *
     * @return Номер заявки
     */
    String createApplication(CreateApplicationRequest req);

    /**
     * @param req - данные по обновлению статуса
     */
    void updateApplicationStatus(UpdateApplicationStatusRequest req);
}
