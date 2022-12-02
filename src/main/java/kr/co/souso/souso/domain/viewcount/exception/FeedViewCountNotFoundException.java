package kr.co.souso.souso.domain.viewcount.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class FeedViewCountNotFoundException extends SousoException {

    public static final SousoException EXCEPTION = new FeedViewCountNotFoundException();

    public FeedViewCountNotFoundException() {
        super(GlobalErrorCode.FEED_VIEW_COUNT_NOT_FOUND);
    }
}