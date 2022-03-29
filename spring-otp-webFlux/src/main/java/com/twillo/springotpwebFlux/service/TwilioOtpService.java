package com.twillo.springotpwebFlux.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twillo.springotpwebFlux.config.TwilioConfig;
import com.twillo.springotpwebFlux.dto.OtpStatus;
import com.twillo.springotpwebFlux.dto.PasswordResetRequestDto;
import com.twillo.springotpwebFlux.dto.PasswordResetResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class TwilioOtpService {
    @Autowired
    private TwilioConfig config;

    Map<String , String> otpMap = new HashMap<>();
    public Mono<PasswordResetResponseDto> sendOtpForPasswordReset(PasswordResetRequestDto requestDto) {
        PasswordResetResponseDto passwordResetResponseDto = null;
        try {
            PhoneNumber to = new PhoneNumber(requestDto.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(config.getTrialNumber());
            String otp = generateOtp();
            String otpMessage = "Dear Customer your otp is : " + otp;

            Message message = Message
                    .creator(to, from,
                            otpMessage)
                    .create();
            otpMap.put(requestDto.getUserName() , otp);
            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception e) {
            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.FAILED, e.getMessage());
        }
        return Mono.just(passwordResetResponseDto);
    }

    public Mono<String> validateOTP(String userInputOtp, String userName) {
        if (userInputOtp.equals(otpMap.get(userName))) {
            return Mono.just("OTP Validated");
        } else {
            return Mono.error(new IllegalArgumentException("OTP Validation Failed"));
        }
    }

    private String generateOtp() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}
