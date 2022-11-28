package kr.co.souso.souso.domain.user.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class NotValidUserException extends SousoException {

    public static final SousoException EXCEPTION =
            new NotValidUserException();

    private NotValidUserException() {
        super(GlobalErrorCode.NOT_VALID_USER);
    }
}