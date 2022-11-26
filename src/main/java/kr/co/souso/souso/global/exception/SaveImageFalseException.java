package kr.co.souso.souso.global.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class SaveImageFalseException extends NumbleException {
    public static final NumbleException EXCEPTION =
            new SaveImageFalseException();

    private SaveImageFalseException() {
        super(GlobalErrorCode.IMAGE_NOT_FOUND);
    }
}
