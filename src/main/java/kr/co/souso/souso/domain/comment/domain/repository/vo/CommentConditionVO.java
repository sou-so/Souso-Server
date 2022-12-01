package kr.co.souso.souso.domain.comment.domain.repository.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentConditionVO {

    Long userId;
    Long feedId;
    Integer pageId;
    Long parentCommentId;

}
