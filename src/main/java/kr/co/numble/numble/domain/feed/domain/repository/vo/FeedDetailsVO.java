package kr.co.numble.numble.domain.feed.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import kr.co.numble.numble.domain.category.domain.repository.vo.CategoryVO;
import kr.co.numble.numble.domain.user.domain.repository.vo.AuthorVO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FeedDetailsVO {

    private CategoryVO categoryVO;
    private AuthorVO authorVO;
    private String content;
    private Long feedId;
    private Long likeCount;
    private Long bookmarkCount;
    private Long commentCount;
    private Boolean isLike;
    private Boolean isBookmark;
    private LocalDateTime createdAt;

    @QueryProjection
    public FeedDetailsVO(CategoryVO categoryVO, AuthorVO authorVO, String content, Long feedId, Long likeCount, Long bookmarkCount, Long commentCount, Boolean isLike, Boolean isBookmark, LocalDateTime createdAt) {
        this.categoryVO = categoryVO;
        this.authorVO = authorVO;
        this.content = content;
        this.feedId = feedId;
        this.likeCount = likeCount;
        this.bookmarkCount = bookmarkCount;
        this.commentCount = commentCount;
        this.isLike = isLike;
        this.isBookmark = isBookmark;
        this.createdAt = createdAt;
    }
}
