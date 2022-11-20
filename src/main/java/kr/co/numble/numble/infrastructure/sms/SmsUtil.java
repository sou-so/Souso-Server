package kr.co.numble.numble.infrastructure.sms;

public interface SmsUtil {
    void sendCode(String phoneNumber, String code);
}
