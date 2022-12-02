package kr.co.souso.souso.domain.user.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class AlreadyAuthCodeExistException extends SousoException {
    public static final SousoException EXCEPTION =
            new AlreadyAuthCodeExistException();

    private AlreadyAuthCodeExistException() {
        super(GlobalErrorCode.ALREADY_AUTH_CODE_EXIST);
    }
}
