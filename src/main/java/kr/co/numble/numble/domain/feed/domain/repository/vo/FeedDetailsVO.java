package kr.co.numble.numble.domain.feed.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import kr.co.numble.numble.domain.category.domain.repository.vo.CategoryVO;
import kr.co.numble.numble.domain.user.domain.repository.vo.AuthorVO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class FeedDetailsVO {

    private CategoryVO categoryVO;
    private AuthorVO authorVO;
    private String content;
    private List<String> imageUrl;
    private Boolean isLike;
    private Boolean isBookmark;
    private LocalDateTime createdAt;
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