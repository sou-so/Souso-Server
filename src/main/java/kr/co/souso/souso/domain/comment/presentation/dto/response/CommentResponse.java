package kr.co.souso.souso.domain.comment.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class CommentResponse {

    List<QueryCommentResponse> comment;

    @ApiModelProperty(value = "다음 댓글 존재 여부", example = "true")
    Boolean hasNext;

}
