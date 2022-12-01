package kr.co.souso.souso.domain.user.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import kr.co.souso.souso.domain.category.presentation.dto.response.CategoryResponse;
import kr.co.souso.souso.domain.comment.presentation.dto.response.CommentSimpleResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryMyCommentDetailsResponse {

    private CategoryResponse category;

    @ApiModelProperty(value = "게시글 고유 ID", example = "2")
    private Long feedId;

    @ApiModelProperty(value = "내용", example = "동네 모임하기 좋은 카페 추천해요.")
    private String feedContent;

    List<CommentSimpleResponse> comment;

}
