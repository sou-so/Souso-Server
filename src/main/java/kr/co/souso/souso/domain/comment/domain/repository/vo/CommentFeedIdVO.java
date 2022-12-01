package kr.co.souso.souso.domain.comment.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.souso.souso.domain.category.domain.repository.vo.CategoryVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CommentFeedIdVO {

    private CategoryVO categoryVO;
    private Long feedId;
    private String feedContent;

    @QueryProjection
    public CommentFeedIdVO(CategoryVO categoryVO, Long feedId, String feedContent) {
        this.categoryVO = categoryVO;
        this.feedId = feedId;
        this.feedContent = feedContent;
    }
}
