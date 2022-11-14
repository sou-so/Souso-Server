package kr.co.numble.numble.domain.category.entity;

import kr.co.numble.numble.domain.feed.domain.Feed;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feed_category")
public class FeedCategory {

    @EmbeddedId
    private FeedCategoryId id;

    @MapsId("feedId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;


    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Builder
    public FeedCategory(FeedCategoryId id, Feed feed, Category category) {
        this.id = id;
        this.feed = feed;
        this.category = category;
    }
}
