package kr.co.souso.souso.domain.category.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryFeedCategoryPagesResponse {

    List<QueryFeedCategoryDetailsResponse> categoryFeedList;

    @ApiModelProperty(value = "다음 게시글 존재 여부", example = "true")
    Boolean hasNext;

    @ApiModelProperty(value = "불러온 게시글 개수", example = "10")
    private Integer feedCount;

}
