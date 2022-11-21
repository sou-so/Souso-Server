package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.category.entity.FeedCategory;
import kr.co.numble.numble.domain.category.facade.CategoryFacade;
import kr.co.numble.numble.domain.category.repository.FeedCategoryRepository;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.domain.FeedImage;
import kr.co.numble.numble.domain.feed.presentation.dto.CreateFeedRequest;
import kr.co.numble.numble.domain.feed.repository.FeedImageRepository;
import kr.co.numble.numble.domain.feed.repository.FeedRepository;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import kr.co.numble.numble.infrastructure.image.s3.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateFeedService {

    private final UserFacade userFacade;
    private final CategoryFacade categoryFacade;
    private final FeedRepository feedRepository;
    private final FeedCategoryRepository feedCategoryRepository;
    private final FeedImageRepository feedImageRepository;
    private final S3Facade s3Facade;

    @Transactional
    public void execute(List<MultipartFile> images, CreateFeedRequest request) {
        User user = userFacade.getCurrentUser();

        Feed feed = Feed.builder()
                .content(request.getContent())
                .user(user)
                .build();

        feedRepository.save(feed);

        images.stream()
                .map(s3Facade::uploadImage)
                .map(image -> FeedImage.builder()
                        .feed(feed)
                        .imageUrl(image)
                        .build()
                )
                .forEach(feedImageRepository::save);

        FeedCategory feedCategory = FeedCategory.builder()
                .feed(feed)
                .category(categoryFacade.getCategoryById(request.getCategoryId()))
                .build();

        feedCategoryRepository.save(feedCategory);
    }
}
