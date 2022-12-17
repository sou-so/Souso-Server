package kr.co.souso.souso.infrastructure.sms;

public interface SmsService {

    void sendCode(String phoneNumber, String code);

}
