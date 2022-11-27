package kr.co.souso.souso.domain.comment.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import kr.co.souso.souso.domain.user.presentation.dto.response.AuthorResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Builder
public class QueryCommentResponse {

    @ApiModelProperty(value = "댓글 작성자")
    private AuthorResponse author;

    @ApiModelProperty(value = "댓글 고유 ID", example = "2")
    private Long commentId;

    @ApiModelProperty(value = "글 작성자인지 유무", example = "true")
    private Boolean isFeedOwner;

    @ApiModelProperty(value = "댓글 작성자인지 유무", example = "true")
    private Boolean isCommentOwner;

    @ApiModelProperty(value = "내용", example = "여기가 어디인가요?")
    private String content;

    @ApiModelProperty(value = "작성일자", example = "2022-11-22T23:21:54")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "답글")
    List<QueryReplyResponse> reply;

}
