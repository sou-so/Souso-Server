package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.bookmark.entity.FeedBookmark;
import kr.co.numble.numble.domain.bookmark.repository.FeedBookmarkRepository;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.exception.FeedNotFoundException;
import kr.co.numble.numble.domain.feed.repository.FeedRepository;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddBookmarkService {

    private final FeedBookmarkRepository feedBookmarkRepository;
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (isNotAlreadyBookmark(feed, user)) {
            FeedBookmark feedBookmark = FeedBookmark.builder()
                    .feed(feed)
                    .user(user)
                    .build();
            feed.addBookmark();
            feedBookmarkRepository.save(feedBookmark);
        }
    }

    private boolean isNotAlreadyBookmark(Feed feed, User user) {
        return feedBookmarkRepository.findByFeedAndUser(feed, user).isEmpty();
    }

}
