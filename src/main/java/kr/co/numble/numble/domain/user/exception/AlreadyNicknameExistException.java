package kr.co.numble.numble.domain.user.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class AlreadyNicknameExistException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new AlreadyNicknameExistException();

    private AlreadyNicknameExistException() {
        super(GlobalErrorCode.ALREADY_NICKNAME_EXIST);
    }

}
