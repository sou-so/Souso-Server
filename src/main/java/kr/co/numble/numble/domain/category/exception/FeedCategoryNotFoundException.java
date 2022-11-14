package kr.co.numble.numble.domain.category.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class FeedCategoryNotFoundException extends NumbleException {

    public static final NumbleException EXCEPTION = new FeedCategoryNotFoundException();

    public FeedCategoryNotFoundException() {
        super(GlobalErrorCode.FEED_CATEGORY_NOT_FOUND);
    }


}
