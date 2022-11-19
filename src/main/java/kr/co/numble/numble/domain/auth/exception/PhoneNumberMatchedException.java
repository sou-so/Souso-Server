package kr.co.numble.numble.domain.auth.exception;

import kr.co.numble.numble.domain.user.exception.UserAuthCodeNotFoundException;
import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class PhoneNumberMatchedException extends NumbleException{

    public static final NumbleException EXCEPTION =
            new PhoneNumberMatchedException();

    private PhoneNumberMatchedException() {
        super(GlobalErrorCode.USER_AUTH_CODE_NOT_FOUND);
    }
}
