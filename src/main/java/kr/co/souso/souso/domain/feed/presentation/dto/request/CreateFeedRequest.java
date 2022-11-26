package kr.co.souso.souso.domain.feed.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateFeedRequest {

    @ApiModelProperty(value = "게시글 내용", example = "내용")
    @NotNull(message = "content는 Null을 허용하지 않습니다.")
    private String content;

    @ApiModelProperty(value = "카테고리 id", example = "10")
    @NotNull(message = "categoryId는 필수로 입력해야 합니다.")
    private Long categoryId;
}
