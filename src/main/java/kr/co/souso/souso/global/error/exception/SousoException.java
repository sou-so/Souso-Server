package kr.co.souso.souso.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SousoException extends RuntimeException {

    private final GlobalErrorCode errorCode;

}
