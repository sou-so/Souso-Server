package kr.co.souso.souso.domain.feed.service;

import kr.co.souso.souso.domain.bookmark.domain.FeedBookmark;
import kr.co.souso.souso.domain.bookmark.domain.repository.FeedBookmarkRepository;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
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
