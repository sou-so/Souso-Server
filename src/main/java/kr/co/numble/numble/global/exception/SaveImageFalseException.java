package kr.co.numble.numble.global.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class SaveImageFalseException extends NumbleException {
    public static final NumbleException EXCEPTION =
            new SaveImageFalseException();

    private SaveImageFalseException() {
        super(GlobalErrorCode.IMAGE_NOT_FOUND);
    }
}
