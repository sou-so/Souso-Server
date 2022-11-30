package kr.co.souso.souso.domain.feed.service;

import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.like.domain.FeedLike;
import kr.co.souso.souso.domain.like.domain.repository.FeedLikeRepository;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddLikeService {

    private final FeedLikeRepository feedLikeRepository;
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (isNotAlreadyLike(feed, user)) {
            feed.addLike();
            user.addLike();
            feedLikeRepository.save(FeedLike.builder()
                    .feed(feed)
                    .user(user)
                    .build());
        }
    }

    private boolean isNotAlreadyLike(Feed feed, User user) {
        return feedLikeRepository.findByFeedAndUser(feed, user).isEmpty();
    }
}
