package kr.co.numble.numble.domain.user.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class InvalidCodeException extends NumbleException {
    public static final NumbleException EXCEPTION =
            new InvalidCodeException();

    private InvalidCodeException() {
        super(GlobalErrorCode.INVALID_CODE);
    }
}
