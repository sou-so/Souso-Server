package kr.co.souso.souso.domain.comment.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryCommentPagesResponse {

    List<QueryCommentDetailsResponse> comment;

    @ApiModelProperty(value = "다음 댓글 존재 여부", example = "true")
    Boolean hasNext;

    @ApiModelProperty(value = "현재 불러온 페이지 번호", example = "10")
    private Integer pageId;

}
