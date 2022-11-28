package kr.co.souso.souso.domain.auth.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class AlreadyPhoneNumberExistException extends SousoException {

    public static final SousoException EXCEPTION =
            new AlreadyPhoneNumberExistException();

    private AlreadyPhoneNumberExistException() {
        super(GlobalErrorCode.ALREADY_PHONE_NUMBER_EXIST);
    }
}
