package kr.co.souso.souso.domain.comment.presentation.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentResponse {

    @ApiModelProperty(value = "댓글 ID")
    private Long commentId;

}