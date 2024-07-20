package mts.homework.sivelkaev.application.controller;

import mts.homework.sivelkaev.application.controller.dto.CreateApplicationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping(value = "mts-homework-sivelkaev-application")
public interface ApplicationController {
    @PostMapping(value = "/application")
    ResponseEntity<String> createApplication(@RequestBody @Valid CreateApplicationRequest req);
}
