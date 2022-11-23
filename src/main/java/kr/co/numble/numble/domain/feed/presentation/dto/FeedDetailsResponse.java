package kr.co.numble.numble.domain.feed.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import kr.co.numble.numble.domain.category.presentation.dto.response.CategoryResponse;
import kr.co.numble.numble.domain.user.presentation.dto.response.AuthorResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class FeedDetailsResponse {

    @ApiModelProperty(value = "카테고리")
    private CategoryResponse category;
    @ApiModelProperty(value = "작성자")
    private AuthorResponse author;
    @ApiModelProperty(value = "내용", example = "동네 모임하기 좋은 카페 추천해요.")
    private String content;
    @ApiModelProperty(value = "게시글 이미지", example = "https://souso-bucket.s3.ap-northeast-2.amazonaws.com/logo.svg")
    private List<String> imageUrl;
    @ApiModelProperty(value = "본인의 현재 게시글 좋아요 유무", example = "true")
    private Boolean isLike;
    @ApiModelProperty(value = "본인의 현재 게시글 북마크 유무", example = "false")
    private Boolean isBookmark;
    @ApiModelProperty(value = "작성일자", example = "2022-11-22T23:21:54")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "조회수", example = "10")
    private Long viewCount;

}