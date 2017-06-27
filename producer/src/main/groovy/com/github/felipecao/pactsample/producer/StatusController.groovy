package com.github.felipecao.pactsample.producer

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.time.LocalDateTime

@RestController
@CrossOrigin
class StatusController {

    private static final Logger log = LoggerFactory.getLogger(StatusController.class)

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Map currentStatus() {
        log.info("New request received!")
        [status: "OK", currentDateTime: LocalDateTime.now().toString()]
    }
}
