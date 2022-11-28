package kr.co.souso.souso.domain.user.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class AlreadyNicknameExistException extends SousoException {

    public static final SousoException EXCEPTION =
            new AlreadyNicknameExistException();

    private AlreadyNicknameExistException() {
        super(GlobalErrorCode.ALREADY_NICKNAME_EXIST);
    }
}
