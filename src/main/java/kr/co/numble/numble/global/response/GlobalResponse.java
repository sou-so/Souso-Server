package kr.co.numble.numble.global.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class GlobalResponse<T> {
    private Boolean isSuccess;
    private T data;
    private String message;

    public static <T> GlobalResponse<T> success() {
        return GlobalResponse.<T>builder()
                .isSuccess(true)
                .message("")
                .data(null)
                .build();
    }

    public static <T> GlobalResponse<T> success(String message) {
        return GlobalResponse.<T>builder()
                .isSuccess(true)
                .message(message)
                .data(null)
                .build();
    }

    public static <T> GlobalResponse<T> success(T data) {
        return GlobalResponse.<T>builder()
                .isSuccess(true)
                .message("")
                .data(data)
                .build();
    }

    public static <T> GlobalResponse<T> success(T data, String message) {
        return GlobalResponse.<T>builder()
                .isSuccess(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T extends HttpStatus> GlobalResponse<T> fail(String message) {
        return GlobalResponse.<T>builder()
                .isSuccess(false)
                .message(message)
                .data(null)
                .build();
    }
}
