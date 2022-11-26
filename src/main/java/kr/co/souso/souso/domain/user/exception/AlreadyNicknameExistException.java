package kr.co.souso.souso.domain.user.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class AlreadyNicknameExistException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new AlreadyNicknameExistException();

    private AlreadyNicknameExistException() {
        super(GlobalErrorCode.ALREADY_NICKNAME_EXIST);
    }
}
