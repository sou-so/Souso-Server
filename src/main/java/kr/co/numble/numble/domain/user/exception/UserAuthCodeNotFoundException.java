package kr.co.numble.numble.domain.user.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class UserAuthCodeNotFoundException extends NumbleException {
    public static final NumbleException EXCEPTION =
            new UserAuthCodeNotFoundException();

    private UserAuthCodeNotFoundException() {
        super(GlobalErrorCode.USER_AUTH_CODE_NOT_FOUND);
    }
}
