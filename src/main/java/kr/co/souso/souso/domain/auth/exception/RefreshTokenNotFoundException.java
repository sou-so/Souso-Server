package kr.co.souso.souso.domain.auth.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class RefreshTokenNotFoundException extends SousoException {

    public static final SousoException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(GlobalErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
