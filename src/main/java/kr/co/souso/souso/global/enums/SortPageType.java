package kr.co.souso.souso.global.enums;

import kr.co.souso.souso.global.utils.code.Constant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortPageType implements Constant {

    LATEST("LATEST"),
    POPULAR("POPULAR");

    private final String code;

}
