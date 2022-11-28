package kr.co.souso.souso.domain.comment.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class InvalidCommentException extends SousoException {

    public static final SousoException EXCEPTION =
            new InvalidCommentException();

    private InvalidCommentException() {
        super(GlobalErrorCode.INVALID_COMMENT);
    }

}
