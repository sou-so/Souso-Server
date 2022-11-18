package kr.co.numble.numble.domain.feed.presentation;

import kr.co.numble.numble.domain.feed.presentation.dto.CreateFeedRequest;
import kr.co.numble.numble.domain.feed.presentation.dto.UpdateFeedRequest;
import kr.co.numble.numble.domain.feed.service.AddLikeService;
import kr.co.numble.numble.domain.feed.service.CreateFeedService;
import kr.co.numble.numble.domain.feed.service.DeleteFeedService;
import kr.co.numble.numble.domain.feed.service.UpdateFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class FeedController {

    private final CreateFeedService createFeedService;
    private final UpdateFeedService updateFeedService;
    private final DeleteFeedService deleteFeedService;
    private final AddLikeService addLikeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createFeed(@RequestBody @Valid CreateFeedRequest request) {
        createFeedService.execute(request);
    }

    @PatchMapping("/{feed-id}")
    public void updateFeed(@RequestBody @Valid UpdateFeedRequest request, @PathVariable("feed-id") Long feedId) {
        updateFeedService.execute(request, feedId);
    }

    @DeleteMapping("/{feed-id}")
    public void deleteFeed(@PathVariable("feed-id") Long feedId) {
        deleteFeedService.execute(feedId);
    }

    @PostMapping("/{feed-id}/like")
    public void like(@PathVariable("feed-id") Long feedId) {
        addLikeService.execute(feedId);
    }
}
