package com.twillo.springotpwebFlux.resource;

import com.twillo.springotpwebFlux.dto.PasswordResetRequestDto;
import com.twillo.springotpwebFlux.service.TwilioOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TwilioOtpHandler {

    @Autowired
    private TwilioOtpService service;

    public Mono<ServerResponse> sendOTP(ServerRequest request) {
        return request.bodyToMono(PasswordResetRequestDto.class)
                .flatMap(dto -> service.sendOtpForPasswordReset(dto))
                .flatMap(dto -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromValue(dto)));
    }

    public Mono<ServerResponse> validateOTP(ServerRequest request) {
        return request.bodyToMono(PasswordResetRequestDto.class)
                .flatMap(dto -> service.validateOTP(dto.getOtp(), dto.getUserName()))
                .flatMap(dto -> ServerResponse.status(HttpStatus.OK)
                        .bodyValue(dto));
    }
}
