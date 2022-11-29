package kr.co.souso.souso.domain.user.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class QueryMyBookmarksPagesResponse {

    List<QueryMyBookmarksDetailsResponse> feedList;

    @ApiModelProperty(value = "다음 게시글 존재 여부", example = "true")
    Boolean hasNext;

    @ApiModelProperty(value = "불러온 게시글 개수", example = "10")
    private Integer feedCount;

    @ApiModelProperty(value = "현재 불러온 페이지 번호", example = "10")
    private Integer pageId;

}
