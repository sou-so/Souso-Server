package kr.co.souso.souso.domain.comment.presentation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.comment.presentation.dto.request.CreateCommentRequest;
import kr.co.souso.souso.domain.comment.presentation.dto.request.CreateReplyRequest;
import kr.co.souso.souso.domain.comment.presentation.dto.request.UpdateCommentRequest;
import kr.co.souso.souso.domain.comment.presentation.dto.response.QueryCommentPagesResponse;
import kr.co.souso.souso.domain.comment.presentation.dto.response.CreateCommentResponse;
import kr.co.souso.souso.domain.comment.presentation.dto.response.CreateReplyResponse;
import kr.co.souso.souso.domain.comment.service.*;
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
    private final UpdateCommentService updateCommentService;
    private final QueryCommentPagesService queryCommentPagesService;
    private final DeleteCommentService deleteCommentService;


    @ApiOperation(value = "댓글 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-id}")
    public CreateCommentResponse createComment(@PathVariable("feed-id") Long feedId, @RequestBody @Valid CreateCommentRequest request) {
        return createCommentService.execute(feedId, request);
    }

    @ApiOperation(value = "댓글 수정")
    @PatchMapping("/{comment-id}")
    public void updateComment(@PathVariable("comment-id") Long commentId, @RequestBody @Valid UpdateCommentRequest request) {
        updateCommentService.execute(commentId, request);
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping("/{comment-id}")
    public void deleteComment(@PathVariable("comment-id") Long commentId) {
        deleteCommentService.execute(commentId);
    }

    @ApiOperation(value = "답글 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reply/{comment-id}")
    public CreateReplyResponse createReply(@PathVariable("comment-id") Long commentId, @RequestBody @Valid CreateReplyRequest request) {
        return createReplyService.execute(commentId, request);
    }

    @ApiOperation(value = "댓글 리스트 조회")
    @GetMapping("/{feed-id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageId", value = "현재 조회한 페이지 번호, 처음은 0", required = true, dataType = "string", paramType = "query", defaultValue = "0"),
    })
    public QueryCommentPagesResponse getComments(@RequestParam(defaultValue = "0") Integer pageId, @PathVariable("feed-id") Long feedId) {
        return queryCommentPagesService.execute(pageId, feedId);
    }
}
