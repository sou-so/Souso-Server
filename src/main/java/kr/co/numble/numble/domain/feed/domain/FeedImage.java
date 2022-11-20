package kr.co.numble.numble.domain.feed.domain;

import kr.co.numble.numble.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feed_image")
public class FeedImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @Builder
    public FeedImage(String imageUrl, Feed feed) {
        this.imageUrl = imageUrl;
        this.feed = feed;
    }

}
