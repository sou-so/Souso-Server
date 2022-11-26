package kr.co.souso.souso.domain.user.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class NotValidUserException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new NotValidUserException();

    private NotValidUserException() {
        super(GlobalErrorCode.NOT_VALID_USER);
    }
}