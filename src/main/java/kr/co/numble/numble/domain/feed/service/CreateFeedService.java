package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.category.entity.FeedCategory;
import kr.co.numble.numble.domain.category.facade.CategoryFacade;
import kr.co.numble.numble.domain.category.repository.FeedCategoryRepository;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.presentation.dto.CreateFeedRequest;
import kr.co.numble.numble.domain.feed.repository.FeedRepository;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateFeedService {

    private final UserFacade userFacade;
    private final CategoryFacade categoryFacade;
    private final FeedRepository feedRepository;
    private final FeedCategoryRepository feedCategoryRepository;

    @Transactional
    public void execute(CreateFeedRequest request) {
        User user = userFacade.getCurrentUser();

        Feed feed = Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();

        feedRepository.save(feed);

        FeedCategory feedCategory = FeedCategory.builder()
                .feed(feed)
                .category(categoryFacade.getCategoryById(request.getCategoryId()))
                .build();

        feedCategoryRepository.save(feedCategory);
    }
}
