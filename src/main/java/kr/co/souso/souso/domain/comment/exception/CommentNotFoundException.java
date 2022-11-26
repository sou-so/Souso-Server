package kr.co.souso.souso.domain.comment.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class CommentNotFoundException extends NumbleException {

    public static final NumbleException EXCEPTION =
            new CommentNotFoundException();

    private CommentNotFoundException() {
        super(GlobalErrorCode.COMMENT_FOT_FOUND);
    }
}
