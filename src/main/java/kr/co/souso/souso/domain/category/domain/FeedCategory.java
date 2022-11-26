package kr.co.souso.souso.domain.category.domain;

import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feed_category")
public class FeedCategory extends BaseTimeEntity {

    @EmbeddedId
    private FeedCategoryId id;

    @MapsId("feedId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Builder
    public FeedCategory(Feed feed, Category category) {
        this.id = new FeedCategoryId(feed.getId());
        this.feed = feed;
        this.category = category;
    }

    public void updateFeedCategory(Feed feed, Category category) {
        this.feed = feed;
        this.category = category;
    }
}
