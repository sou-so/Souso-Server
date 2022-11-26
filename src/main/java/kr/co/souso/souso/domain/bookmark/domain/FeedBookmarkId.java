package kr.co.souso.souso.domain.bookmark.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FeedBookmarkId implements Serializable {

    private Long userId;
    private Long feedId;

    @Builder
    public FeedBookmarkId(Long userId, Long feedId) {
        this.userId = userId;
        this.feedId = feedId;
    }
}
