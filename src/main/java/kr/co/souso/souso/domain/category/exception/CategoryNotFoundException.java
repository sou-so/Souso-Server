package kr.co.souso.souso.domain.category.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class CategoryNotFoundException extends NumbleException {

    public static final NumbleException EXCEPTION = new CategoryNotFoundException();

    public CategoryNotFoundException() {
        super(GlobalErrorCode.CATEGORY_NOT_FOUND);
    }
}
