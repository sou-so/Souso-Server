package kr.co.souso.souso.domain.comment.presentation;

import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.comment.presentation.dto.request.CreateCommentRequest;
import kr.co.souso.souso.domain.comment.presentation.dto.request.CreateReplyRequest;
import kr.co.souso.souso.domain.comment.service.CreateCommentService;
import kr.co.souso.souso.domain.comment.service.CreateReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CreateCommentService createCommentService;
    private final CreateReplyService createReplyService;

    @ApiOperation(value = "댓글 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-id}")
    public void createComment(@PathVariable("feed-id") Long feedId, @RequestBody @Valid CreateCommentRequest request) {
        createCommentService.execute(feedId, request);
    }

    @ApiOperation(value = "답글 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reply/{comment-id}")
    public void createReply(@PathVariable("comment-id") Long commentId, @RequestBody @Valid CreateReplyRequest request) {
        createReplyService.execute(commentId, request);
    }
}
