package kr.co.souso.souso.domain.comment.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentSimpleResponse {

    @ApiModelProperty(value = "댓글 고유 ID", example = "2")
    private Long commentId;

    @ApiModelProperty(value = "내용", example = "여기가 어디인가요?")
    private String content;

}
