package kr.co.souso.souso.global.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class UserNotFoundException extends SousoException {

    public static final SousoException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(GlobalErrorCode.USER_NOT_FOUND);
    }
}
