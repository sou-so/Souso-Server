package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.category.entity.FeedCategory;
import kr.co.numble.numble.domain.category.repository.FeedCategoryRepository;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.exception.FeedNotFoundException;
import kr.co.numble.numble.domain.feed.repository.FeedRepository;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.exception.NotValidUserException;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteFeedService {

    private final UserFacade userFacade;
    private final FeedRepository feedRepository;
    private final FeedCategoryRepository feedCategoryRepository;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (!user.getId().equals(feed.getUser().getId())) {
            throw NotValidUserException.EXCEPTION;
        }

        feedCategoryRepository.deleteByFeed(feed);
        feedRepository.delete(feed);

    }
}
