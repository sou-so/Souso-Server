package kr.co.souso.souso.domain.feed.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class FeedNotFoundException extends SousoException {

    public static final SousoException EXCEPTION = new FeedNotFoundException();

    public FeedNotFoundException() {
        super(GlobalErrorCode.FEED_NOT_FOUND);
    }
}