package kr.co.souso.souso.global.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class ExpiredJwtException extends SousoException {

    public static final ExpiredJwtException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(GlobalErrorCode.EXPIRED_JWT);
    }
}
