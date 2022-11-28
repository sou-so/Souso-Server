package kr.co.souso.souso.domain.feed.presentation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.feed.presentation.dto.request.CreateFeedRequest;
import kr.co.souso.souso.domain.feed.presentation.dto.request.UpdateFeedRequest;
import kr.co.souso.souso.domain.feed.presentation.dto.response.CreateFeedResponse;
import kr.co.souso.souso.domain.feed.presentation.dto.response.QueryFeedDetailsResponse;
import kr.co.souso.souso.domain.feed.presentation.dto.response.QueryFeedPagesResponse;
import kr.co.souso.souso.domain.feed.service.*;
import kr.co.souso.souso.global.enums.SortPageType;
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
    private final QueryFeedDetailsService queryFeedDetailsService;
    private final QueryFeedPagesService queryFeedPagesService;

    @ApiOperation(value = "게시글 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateFeedResponse createFeed(@RequestPart(required = false) List<MultipartFile> images, @RequestPart @Valid CreateFeedRequest request) {
        return createFeedService.execute(images, request);
    }

    @ApiOperation(value = "게시글 수정")
    @PatchMapping("/{feed-id}")
    public void updateFeed(@RequestPart(required = false) List<MultipartFile> images, @RequestPart @Valid UpdateFeedRequest request, @PathVariable("feed-id") Long feedId) {
        updateFeedService.execute(images, request, feedId);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/{feed-id}")
    public void deleteFeed(@PathVariable("feed-id") Long feedId) {
        deleteFeedService.execute(feedId);
    }

    @ApiOperation(value = "게시글 상세 조회")
    @GetMapping("/{feed-id}")
    public QueryFeedDetailsResponse getFeed(@PathVariable("feed-id") Long feedId) {
        return queryFeedDetailsService.execute(feedId);
    }

    @ApiOperation(value = "게시글 리스트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cursorId", value = "LATEST: 마지막으로 불러온 가장 작은 게시글ID, 처음은 0", dataType = "string", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "pageId", value = "POPULAR: 현재 조회한 페이지 번호, 처음은 0", dataType = "string", paramType = "query", defaultValue = "0"),
    })
    @GetMapping
    public QueryFeedPagesResponse getFeeds(@RequestParam(required = false, defaultValue = "0") Long cursorId, @RequestParam(required = false, defaultValue = "0") Integer pageId, @RequestParam SortPageType sortType) {
        return queryFeedPagesService.execute(cursorId, pageId, sortType);
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