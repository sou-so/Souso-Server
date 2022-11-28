package kr.co.souso.souso.domain.comment.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateReplyResponse {

    @ApiModelProperty(value = "답글 ID")
    private Long replyId;

}