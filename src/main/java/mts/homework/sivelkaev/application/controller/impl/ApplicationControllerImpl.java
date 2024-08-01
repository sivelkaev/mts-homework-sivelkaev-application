package mts.homework.sivelkaev.application.controller.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mts.homework.sivelkaev.application.controller.ApplicationController;
import mts.homework.sivelkaev.application.controller.dto.CreateApplicationRequest;
import mts.homework.sivelkaev.application.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ApplicationControllerImpl implements ApplicationController {
    private final ApplicationService applicationService;

    @Override
    public ResponseEntity<String> createApplication(CreateApplicationRequest req) {
        return ResponseEntity.ok(applicationService.createApplication(req));
    }
}
