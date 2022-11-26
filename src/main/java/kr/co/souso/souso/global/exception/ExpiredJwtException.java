package kr.co.souso.souso.global.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class ExpiredJwtException extends NumbleException {

    public static final ExpiredJwtException EXCEPTION =
            new ExpiredJwtException();

    private ExpiredJwtException() {
        super(GlobalErrorCode.EXPIRED_JWT);
    }
}
