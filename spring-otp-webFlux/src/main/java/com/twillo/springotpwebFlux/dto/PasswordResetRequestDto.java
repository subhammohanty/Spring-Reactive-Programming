package com.twillo.springotpwebFlux.dto;

public class PasswordResetRequestDto {

    private String phoneNumber;
    private String userName;
    private String otp;

    public PasswordResetRequestDto(String phoneNumber, String userName, String otp) {
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.otp = otp;
    }

    public PasswordResetRequestDto() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
