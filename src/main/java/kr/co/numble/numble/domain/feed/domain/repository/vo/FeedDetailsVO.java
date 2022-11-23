package kr.co.numble.numble.domain.feed.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import kr.co.numble.numble.domain.category.domain.repository.vo.CategoryVO;
import kr.co.numble.numble.domain.user.presentation.dto.response.AuthorVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class FeedDetailsVO {

    @ApiModelProperty(value = "카테고리")
    private CategoryVO categoryVO;
    @ApiModelProperty(value = "작성자")
    private AuthorVO authorVO;
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

    @QueryProjection
    public FeedDetailsVO(CategoryVO categoryVO, AuthorVO authorVO, String content, Boolean isLike, Boolean isBookmark, LocalDateTime createdAt) {
        this.categoryVO = categoryVO;
        this.authorVO = authorVO;
        this.content = content;
        this.isLike = isLike;
        this.isBookmark = isBookmark;
        this.createdAt = createdAt;
    }

}