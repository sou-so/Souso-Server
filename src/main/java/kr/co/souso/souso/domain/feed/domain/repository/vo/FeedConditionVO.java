package kr.co.souso.souso.domain.feed.domain.repository.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class FeedConditionVO {

    private Long userId;

    private Long cursorId;

    private Integer pageId;

    private Long categoryId;

    private Long findUserId;

}
