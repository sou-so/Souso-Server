package kr.co.numble.numble.domain.auth.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class PasswordMisMatchException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new PasswordMisMatchException();

    private PasswordMisMatchException() {
        super(GlobalErrorCode.PASSWORD_MISMATCH);
    }
}
