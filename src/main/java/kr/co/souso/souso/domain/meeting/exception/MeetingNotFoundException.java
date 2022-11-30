package kr.co.souso.souso.domain.meeting.exception;

import kr.co.souso.souso.global.error.exception.GlobalErrorCode;
import kr.co.souso.souso.global.error.exception.SousoException;

public class MeetingNotFoundException extends SousoException {

    public static final SousoException EXCEPTION =
            new MeetingNotFoundException();

    private MeetingNotFoundException() {
        super(GlobalErrorCode.MEETING_NOT_FOUND);
    }
}
