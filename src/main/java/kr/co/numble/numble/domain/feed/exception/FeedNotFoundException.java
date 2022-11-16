package kr.co.numble.numble.domain.feed.exception;

import kr.co.numble.numble.global.error.exception.GlobalErrorCode;
import kr.co.numble.numble.global.error.exception.NumbleException;

public class FeedNotFoundException extends NumbleException {

    public static final NumbleException EXCEPTION = new FeedNotFoundException();

    public FeedNotFoundException() {
        super(GlobalErrorCode.FEED_NOT_FOUND);
    }
}