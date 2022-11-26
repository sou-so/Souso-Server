package kr.co.souso.souso.domain.auth.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class RefreshTokenNotFoundException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException() {
        super(GlobalErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
