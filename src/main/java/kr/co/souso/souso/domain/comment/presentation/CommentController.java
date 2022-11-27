package kr.co.souso.souso.domain.comment.presentation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.comment.presentation.dto.request.CreateCommentRequest;
import kr.co.souso.souso.domain.comment.presentation.dto.request.CreateReplyRequest;
import kr.co.souso.souso.domain.comment.presentation.dto.response.CommentResponse;
import kr.co.souso.souso.domain.comment.presentation.dto.response.QueryCommentResponse;
import kr.co.souso.souso.domain.comment.service.CreateCommentService;
import kr.co.souso.souso.domain.comment.service.CreateReplyService;
import kr.co.souso.souso.domain.comment.service.QueryCommentPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentController {

    private final CreateCommentService createCommentService;
    private final CreateReplyService createReplyService;
    private final QueryCommentPagesService queryCommentPagesService;

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

    @ApiOperation(value = "댓글 리스트 조회")
    @GetMapping("/{feed-id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageId", value = "현재 조회한 페이지 번호, 처음은 0", dataType = "string", paramType = "query", defaultValue = "0"),
    })
    public CommentResponse getComments(@RequestParam(defaultValue = "0") Integer pageId, @PathVariable("feed-id") Long feedId) {
        return queryCommentPagesService.execute(pageId, feedId);
    }
}
