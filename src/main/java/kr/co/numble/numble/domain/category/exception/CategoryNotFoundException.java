package kr.co.numble.numble.domain.category.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class CategoryNotFoundException extends NumbleException {

    public static final NumbleException EXCEPTION = new CategoryNotFoundException();

    public CategoryNotFoundException() {
        super(GlobalErrorCode.CATEGORY_NOT_FOUND);
    }


}
