package kr.co.souso.souso.domain.feed.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.NumbleException;

public class FeedNotFoundException extends NumbleException {

    public static final NumbleException EXCEPTION = new FeedNotFoundException();

    public FeedNotFoundException() {
        super(GlobalErrorCode.FEED_NOT_FOUND);
    }
}