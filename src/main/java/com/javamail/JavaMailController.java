package com.javamail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@RestController
public class JavaMailController {

    @Autowired
    private JavaMailService javaMailService;

    @PostMapping("send-otp")
    public Mono<ResponseEntity<Object>> sendOtp(@RequestBody DTO otp) {

        return Mono.fromRunnable(()-> javaMailService.sendMail(otp)).subscribeOn(Schedulers.boundedElastic())
                .then(Mono.just(ResponseEntity.ok("OTP Sent Successfully")));
    }
}
