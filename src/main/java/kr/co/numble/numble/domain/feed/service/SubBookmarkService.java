package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.bookmark.domain.FeedBookmark;
import kr.co.numble.numble.domain.bookmark.domain.repository.FeedBookmarkRepository;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.domain.repository.FeedRepository;
import kr.co.numble.numble.domain.feed.exception.FeedNotFoundException;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubBookmarkService {

    private final FeedBookmarkRepository feedBookmarkRepository;
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if(isAlreadyBookmark(feed, user)){
            feed.subBookmark();
            FeedBookmark feedBookmark = FeedBookmark.builder()
                    .feed(feed)
                    .user(user)
                    .build();
            feedBookmarkRepository.delete(feedBookmark);
        }
    }

    private boolean isAlreadyBookmark(Feed feed, User user) {
        return feedBookmarkRepository.findByFeedAndUser(feed, user).isPresent();
    }
}
