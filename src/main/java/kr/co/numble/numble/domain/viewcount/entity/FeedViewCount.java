package kr.co.numble.numble.domain.viewcount.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class FeedViewCount {

    @Id
    private Long feedId;

    private Long viewCount;

    @Builder
    public FeedViewCount(Long feedId) {
        this.feedId = feedId;
        this.viewCount = 0L;
    }
}
