package kr.co.souso.souso.domain.comment.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateReplyRequest {

    @ApiModelProperty(value = "답글 내용", example = "내용")
    @NotNull(message = "content는 Null을 허용하지 않습니다.")
    private String content;

}
