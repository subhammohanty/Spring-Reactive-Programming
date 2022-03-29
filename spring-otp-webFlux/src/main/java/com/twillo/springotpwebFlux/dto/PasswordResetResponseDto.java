package com.twillo.springotpwebFlux.dto;

public class PasswordResetResponseDto {

    private OtpStatus status;
    private String message;

    public PasswordResetResponseDto(OtpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PasswordResetResponseDto() {
    }

    public OtpStatus getStatus() {
        return status;
    }

    public void setStatus(OtpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
