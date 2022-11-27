package kr.co.souso.souso.domain.comment.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import kr.co.souso.souso.domain.user.domain.repository.vo.AuthorVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CommentDetailsVO {

    private AuthorVO author;
    private Long commentId;
    private Long parentCommentId;
    private Boolean isFeedOwner;
    private Boolean isCommentOwner;
    private String content;
    private LocalDateTime createdAt;

    @QueryProjection
    public CommentDetailsVO(AuthorVO author, Long commentId, Long parentCommentId, Boolean isFeedOwner, Boolean isCommentOwner, String content, LocalDateTime createdAt) {
        this.author = author;
        this.commentId = commentId;
        this.parentCommentId = parentCommentId;
        this.isFeedOwner = isFeedOwner;
        this.isCommentOwner = isCommentOwner;
        this.content = content;
        this.createdAt = createdAt;
    }
}
