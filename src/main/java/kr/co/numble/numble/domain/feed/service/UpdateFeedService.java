package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.category.entity.Category;
import kr.co.numble.numble.domain.category.entity.FeedCategory;
import kr.co.numble.numble.domain.category.facade.CategoryFacade;
import kr.co.numble.numble.domain.category.repository.FeedCategoryRepository;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.exception.FeedNotFoundException;
import kr.co.numble.numble.domain.feed.presentation.dto.UpdateFeedRequest;
import kr.co.numble.numble.domain.feed.repository.FeedRepository;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.exception.NotValidUserException;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateFeedService {

    private final FeedRepository feedRepository;
    private final UserFacade userFacade;
    private final CategoryFacade categoryFacade;
    private final FeedCategoryRepository feedCategoryRepository;

    @Transactional
    public void execute(UpdateFeedRequest request, Long feedId) {
        User user = userFacade.getCurrentUser();
        Category category = categoryFacade.getCategoryById(request.getCategoryId());
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (!user.getId().equals(feed.getUser().getId())) {
            throw NotValidUserException.EXCEPTION;
        }

        feed.updateFeed(request.getTitle(), request.getContent());

        FeedCategory feedCategory = feedCategoryRepository.findFeedCategoryByFeedId(feedId);
        feedCategory.updateFeedCategory(feed, category);
    }

}
