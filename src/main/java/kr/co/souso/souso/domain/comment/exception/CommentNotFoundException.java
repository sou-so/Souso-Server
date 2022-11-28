package kr.co.souso.souso.domain.comment.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class CommentNotFoundException extends SousoException {

    public static final SousoException EXCEPTION =
            new CommentNotFoundException();

    private CommentNotFoundException() {
        super(GlobalErrorCode.COMMENT_FOT_FOUND);
    }
}
