package kr.co.numble.numble.domain.like.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FeedLikeId implements Serializable {

    private Long userId;
    private Long feedId;

    @Builder
    public FeedLikeId(Long userId, Long feedId) {
        this.userId = userId;
        this.feedId = feedId;
    }
}
