package kr.co.numble.numble.domain.feed.presentation;

import kr.co.numble.numble.domain.feed.presentation.dto.CreateFeedRequest;
import kr.co.numble.numble.domain.feed.presentation.dto.UpdateFeedRequest;
import kr.co.numble.numble.domain.feed.service.CreateFeedService;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createFeed(@RequestBody @Valid CreateFeedRequest request) {
        createFeedService.execute(request);
    }

    @PatchMapping("/{feed-id}")
    public void updateFeed(@RequestBody @Valid UpdateFeedRequest request, @PathVariable("feed-id") Long feedId) {
        updateFeedService.execute(request, feedId);
    }
}
