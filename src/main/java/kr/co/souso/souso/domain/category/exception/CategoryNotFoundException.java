package kr.co.souso.souso.domain.category.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class CategoryNotFoundException extends SousoException {

    public static final SousoException EXCEPTION = new CategoryNotFoundException();

    public CategoryNotFoundException() {
        super(GlobalErrorCode.CATEGORY_NOT_FOUND);
    }
}
