package kr.co.souso.souso.domain.user.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class AlreadyEmailExistsException extends SousoException {

    public static final SousoException EXCEPTION =
            new AlreadyEmailExistsException();

    private AlreadyEmailExistsException() {
        super(GlobalErrorCode.ALREADY_EMAIL_EXISTS);
    }
}
