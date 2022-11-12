package kr.co.numble.numble.domain.user.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class AlreadyUserExistException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new AlreadyUserExistException();

    private AlreadyUserExistException() {
        super(GlobalErrorCode.ALREADY_NICKNAME_EXIST);
    }

}
