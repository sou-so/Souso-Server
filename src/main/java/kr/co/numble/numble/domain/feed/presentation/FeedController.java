package kr.co.numble.numble.domain.feed.presentation;

import io.swagger.annotations.ApiOperation;
import kr.co.numble.numble.domain.feed.presentation.dto.request.CreateFeedRequest;
import kr.co.numble.numble.domain.feed.presentation.dto.response.FeedDetailsResponse;
import kr.co.numble.numble.domain.feed.presentation.dto.request.UpdateFeedRequest;
import kr.co.numble.numble.domain.feed.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class FeedController {

    private final CreateFeedService createFeedService;
    private final UpdateFeedService updateFeedService;
    private final DeleteFeedService deleteFeedService;
    private final AddLikeService addLikeService;
    private final SubLikeService subLikeService;
    private final AddBookmarkService addBookmarkService;
    private final SubBookmarkService subBookmarkService;
    private final FeedDetailsService feedDetailsService;

    @ApiOperation(value = "게시글 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createFeed(@RequestPart List<MultipartFile> images, @RequestPart @Valid CreateFeedRequest request) {
        createFeedService.execute(images, request);
    }

    @ApiOperation(value = "게시글 수정")
    @PatchMapping("/{feed-id}")
    public void updateFeed(@RequestPart List<MultipartFile> images, @RequestPart @Valid UpdateFeedRequest request, @PathVariable("feed-id") Long feedId) {
        updateFeedService.execute(images, request, feedId);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/{feed-id}")
    public void deleteFeed(@PathVariable("feed-id") Long feedId) {
        deleteFeedService.execute(feedId);
    }

    @ApiOperation(value = "게시글 상세 조회")
    @GetMapping("/{feed-id}")
    public FeedDetailsResponse getFeed(@PathVariable("feed-id") Long feedId) {
        return feedDetailsService.execute(feedId);
    }

    @ApiOperation(value = "게시글 좋아요")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-id}/like")
    public void like(@PathVariable("feed-id") Long feedId) {
        addLikeService.execute(feedId);
    }

    @ApiOperation(value = "게시글 좋아요 취소")
    @DeleteMapping("/{feed-id}/like")
    public void unlike(@PathVariable("feed-id") Long feedId) {
        subLikeService.execute(feedId);
    }

    @ApiOperation(value = "게시글 북마크")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-id}/bookmark")
    public void bookmark(@PathVariable("feed-id") Long feedId) {
        addBookmarkService.execute(feedId);
    }

    @ApiOperation(value = "게시글 북마크 취소")
    @DeleteMapping("/{feed-id}/bookmark")
    public void unBookmark(@PathVariable("feed-id") Long feedId) {
        subBookmarkService.execute(feedId);
    }

}
