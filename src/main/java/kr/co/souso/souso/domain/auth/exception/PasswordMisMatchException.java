package kr.co.souso.souso.domain.auth.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class PasswordMisMatchException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new PasswordMisMatchException();

    private PasswordMisMatchException() {
        super(GlobalErrorCode.PASSWORD_MISMATCH);
    }
}
