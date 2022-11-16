package kr.co.numble.numble.domain.user.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class NotValidUserException extends NumbleException {
    public static final NumbleException EXCEPTION =
            new NotValidUserException();

    private NotValidUserException() {
        super(GlobalErrorCode.NOT_VALID_USER);
    }
}