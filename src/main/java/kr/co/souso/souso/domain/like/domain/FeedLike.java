package kr.co.souso.souso.domain.like.domain;

import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feed_like")
public class FeedLike extends BaseTimeEntity {

    @EmbeddedId
    private FeedLikeId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @MapsId("feedId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @Builder
    public FeedLike(User user, Feed feed) {
        this.id = new FeedLikeId(user.getId(), feed.getId());
        this.user = user;
        this.feed = feed;
    }
}
