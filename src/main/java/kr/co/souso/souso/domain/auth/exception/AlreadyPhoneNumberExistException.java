package kr.co.souso.souso.domain.auth.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class AlreadyPhoneNumberExistException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new AlreadyPhoneNumberExistException();

    private AlreadyPhoneNumberExistException() {
        super(GlobalErrorCode.ALREADY_PHONE_NUMBER_EXIST);
    }
}
