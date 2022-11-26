package kr.co.souso.souso.infrastructure.sms;

public interface SmsUtil {
    void sendCode(String phoneNumber, String code);
}
