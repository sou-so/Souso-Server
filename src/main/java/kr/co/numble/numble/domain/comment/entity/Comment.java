package kr.co.numble.numble.domain.comment.entity;

import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_comment")
public class Comment extends BaseTimeEntity {

    @PrePersist
    public void prePersist() {
        this.parentComment = this.parentComment == null ? this : this.parentComment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @Builder
    public Comment(String content, User user, Feed feed, Comment parentComment) {
        this.content = content;
        this.user = user;
        this.feed = feed;
        this.parentComment = parentComment;
    }
}
