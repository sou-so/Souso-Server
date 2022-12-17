package kr.co.souso.souso.domain.comment.domain;

import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_comment")
public class Comment extends BaseTimeEntity {

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
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Comment parentComment;

    @Builder
    public Comment(String content, User user, Feed feed, Comment parentComment) {
        this.content = content;
        this.user = user;
        this.feed = feed;
        this.parentComment = parentComment;
    }

    @PrePersist
    public void prePersist() {
        this.parentComment = this.parentComment == null ? this : this.parentComment;
    }

    public void updateComment(String content){
        this.content = content;
    }
}
