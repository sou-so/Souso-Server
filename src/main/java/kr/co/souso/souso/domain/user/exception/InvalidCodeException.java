package kr.co.souso.souso.domain.user.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class InvalidCodeException extends SousoException {

    public static final SousoException EXCEPTION =
            new InvalidCodeException();

    private InvalidCodeException() {
        super(GlobalErrorCode.INVALID_CODE);
    }
}
