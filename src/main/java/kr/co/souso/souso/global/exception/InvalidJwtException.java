package kr.co.souso.souso.global.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class InvalidJwtException extends SousoException {

    public static final SousoException EXCEPTION =
            new InvalidJwtException();

    private InvalidJwtException() {
        super(GlobalErrorCode.INVALID_JWT);
    }
}
