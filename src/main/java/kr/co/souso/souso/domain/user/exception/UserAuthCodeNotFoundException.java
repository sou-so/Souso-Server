package kr.co.souso.souso.domain.user.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class UserAuthCodeNotFoundException extends SousoException {

    public static final SousoException EXCEPTION =
            new UserAuthCodeNotFoundException();

    private UserAuthCodeNotFoundException() {
        super(GlobalErrorCode.USER_AUTH_CODE_NOT_FOUND);
    }
}
