package kr.co.numble.numble.domain.comment.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class CommentNotFoundException extends NumbleException {
    public static final NumbleException EXCEPTION =
            new CommentNotFoundException();

    private CommentNotFoundException() {
        super(GlobalErrorCode.COMMENT_FOT_FOUND);
    }
}
