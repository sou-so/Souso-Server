package kr.co.souso.souso.domain.feed.service;

import kr.co.souso.souso.domain.category.domain.repository.FeedCategoryRepository;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.feed.domain.repository.FeedImageRepository;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.exception.NotValidUserException;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.viewcount.domain.FeedViewCount;
import kr.co.souso.souso.domain.viewcount.domain.repository.FeedViewCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteFeedService {

    private final UserFacade userFacade;
    private final FeedRepository feedRepository;
    private final FeedCategoryRepository feedCategoryRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        FeedViewCount feedViewCount = feedViewCountRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (!user.getId().equals(feed.getUser().getId())) {
            throw NotValidUserException.EXCEPTION;
        }
        user.subFeed();
        feedImageRepository.deleteAllByFeed(feed);
        feedCategoryRepository.deleteByFeed(feed);
        feedRepository.delete(feed);
        feedViewCountRepository.delete(feedViewCount);
    }
}
