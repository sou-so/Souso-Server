package kr.co.souso.souso.domain.feed.service;

import kr.co.souso.souso.domain.category.domain.FeedCategory;
import kr.co.souso.souso.domain.category.domain.repository.FeedCategoryRepository;
import kr.co.souso.souso.domain.category.facade.CategoryFacade;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.feed.domain.FeedImage;
import kr.co.souso.souso.domain.feed.domain.repository.FeedImageRepository;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.feed.presentation.dto.request.CreateFeedRequest;
import kr.co.souso.souso.domain.feed.presentation.dto.response.CreateFeedResponse;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.viewcount.domain.FeedViewCount;
import kr.co.souso.souso.domain.viewcount.domain.repository.FeedViewCountRepository;
import kr.co.souso.souso.infrastructure.image.s3.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateFeedService {

    private final UserFacade userFacade;
    private final CategoryFacade categoryFacade;
    private final FeedRepository feedRepository;
    private final FeedCategoryRepository feedCategoryRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final S3Facade s3Facade;

    @Transactional
    public CreateFeedResponse execute(List<MultipartFile> images, CreateFeedRequest request) {
        User user = userFacade.getCurrentUser();

        Feed feed = Feed.builder()
                .content(request.getContent())
                .user(user)
                .build();

        feedRepository.save(feed);
        if (images != null) {
            images.stream()
                    .map(s3Facade::uploadImage)
                    .map(image -> FeedImage.builder()
                            .feed(feed)
                            .imageUrl(image)
                            .build()
                    )
                    .forEach(feedImageRepository::save);
        }
        FeedCategory feedCategory = FeedCategory.builder()
                .feed(feed)
                .category(categoryFacade.getCategoryById(request.getCategoryId()))
                .build();

        FeedViewCount feedViewCount = FeedViewCount.builder()
                .feedId(feed.getId())
                .build();

        feedViewCountRepository.save(feedViewCount);
        feedCategoryRepository.save(feedCategory);

        return new CreateFeedResponse(feed.getId());
    }
}
