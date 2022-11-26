package kr.co.souso.souso.domain.feed.service;

import kr.co.souso.souso.domain.category.domain.Category;
import kr.co.souso.souso.domain.category.domain.FeedCategory;
import kr.co.souso.souso.domain.category.domain.repository.FeedCategoryRepository;
import kr.co.souso.souso.domain.category.facade.CategoryFacade;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.feed.domain.FeedImage;
import kr.co.souso.souso.domain.feed.domain.repository.FeedImageRepository;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.feed.presentation.dto.request.UpdateFeedRequest;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.exception.NotValidUserException;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.infrastructure.image.s3.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UpdateFeedService {

    private final FeedRepository feedRepository;
    private final UserFacade userFacade;
    private final CategoryFacade categoryFacade;
    private final FeedCategoryRepository feedCategoryRepository;
    private final FeedImageRepository feedImageRepository;
    private final S3Facade s3Facade;

    @Transactional
    public void execute(List<MultipartFile> images, UpdateFeedRequest request, Long feedId) {
        User user = userFacade.getCurrentUser();
        Category category = categoryFacade.getCategoryById(request.getCategoryId());

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (!user.getId().equals(feed.getUser().getId())) {
            throw NotValidUserException.EXCEPTION;
        }

        feedImageRepository.deleteAllByFeed(feed);

        images.stream()
                .map(s3Facade::uploadImage)
                .map(image -> FeedImage.builder()
                        .feed(feed)
                        .imageUrl(image)
                        .build()
                )
                .forEach(feedImageRepository::save);

        feed.updateFeed(request.getContent());

        FeedCategory feedCategory = feedCategoryRepository.findFeedCategoryByFeedId(feedId);
        feedCategory.updateFeedCategory(feed, category);
    }
}
