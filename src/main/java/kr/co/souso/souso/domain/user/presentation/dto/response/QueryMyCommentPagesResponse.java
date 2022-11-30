package kr.co.souso.souso.domain.user.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryMyCommentPagesResponse {

    List<QueryMyCommentDetailsResponse> commentList;

    @ApiModelProperty(value = "다음 댓글 존재 여부", example = "true")
    Boolean hasNext;

    @ApiModelProperty(value = "현재 불러온 페이지 번호", example = "10")
    private Integer pageId;

}
