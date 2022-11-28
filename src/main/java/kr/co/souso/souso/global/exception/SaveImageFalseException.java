package kr.co.souso.souso.global.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class SaveImageFalseException extends SousoException {
    public static final SousoException EXCEPTION =
            new SaveImageFalseException();

    private SaveImageFalseException() {
        super(GlobalErrorCode.IMAGE_NOT_FOUND);
    }
}
