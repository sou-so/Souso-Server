package kr.co.souso.souso.domain.feed.domain;

import kr.co.souso.souso.domain.bookmark.domain.FeedBookmark;
import kr.co.souso.souso.domain.category.domain.FeedCategory;
import kr.co.souso.souso.domain.comment.domain.Comment;
import kr.co.souso.souso.domain.like.domain.FeedLike;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feed")
public class Feed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ColumnDefault("0")
    private Long likeCount;

    @NotNull
    @ColumnDefault("0")
    private Long bookmarkCount;

    @NotNull
    @ColumnDefault("0")
    private Long commentCount;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedLike> feedLikes;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedBookmark> feedBookmarks;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedImage> feedImages;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.REMOVE)
    private List<FeedCategory> feedCategories;

    @Builder
    public Feed(String content, User user, Long likeCount, Long bookmarkCount) {
        this.content = content;
        this.user = user;
        this.likeCount = likeCount;
        this.bookmarkCount = bookmarkCount;
    }

    @PrePersist
    public void prePersist() {
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
        this.bookmarkCount = this.bookmarkCount == null ? 0 : this.bookmarkCount;
        this.commentCount = this.commentCount == null ? 0 : this.commentCount;
    }

    public void updateFeed(String content) {
        this.content = content;
    }

    public void addLike() {
        this.likeCount++;
    }

    public void subLike(){
        this.likeCount--;
    }

    public void addBookmark() {
        this.bookmarkCount++;
    }

    public void subBookmark(){
        this.bookmarkCount--;
    }

    public void addComment() {
        this.commentCount++;
    }

    public void subComment() {
        this.commentCount--;
    }
}
