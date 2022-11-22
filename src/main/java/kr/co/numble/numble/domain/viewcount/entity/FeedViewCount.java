package kr.co.numble.numble.domain.viewcount.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.PrePersist;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class FeedViewCount {

    @Id
    private Long feedId;

    private Long viewCount;

    @Builder
    public FeedViewCount(Long feedId, Long viewCount) {
        this.feedId = feedId;
        this.viewCount = viewCount;
    }

    @PrePersist
    public void prePersist() {
        this.viewCount = this.viewCount == null ? 0 : this.viewCount;
    }

    public void addViewCount(){
        this.viewCount++;
    }

}
