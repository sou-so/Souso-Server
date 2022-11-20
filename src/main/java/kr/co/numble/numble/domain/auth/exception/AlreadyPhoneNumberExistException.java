package kr.co.numble.numble.domain.auth.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class AlreadyPhoneNumberExistException extends NumbleException{

    public static final NumbleException EXCEPTION =
            new AlreadyPhoneNumberExistException();

    private AlreadyPhoneNumberExistException() {
        super(GlobalErrorCode.ALREADY_PHONE_NUMBER_EXIST);
    }
}
