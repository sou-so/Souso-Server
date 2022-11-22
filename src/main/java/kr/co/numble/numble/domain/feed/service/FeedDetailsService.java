package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.domain.FeedImage;
import kr.co.numble.numble.domain.feed.exception.FeedNotFoundException;
import kr.co.numble.numble.domain.feed.presentation.dto.response.FeedDetailsResponse;
import kr.co.numble.numble.domain.feed.repository.FeedImageRepository;
import kr.co.numble.numble.domain.feed.repository.FeedRepository;
import kr.co.numble.numble.domain.image.presentation.dto.response.ImageUrlResponse;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import kr.co.numble.numble.domain.viewcount.FeedViewCountRepository;
import kr.co.numble.numble.domain.viewcount.entity.FeedViewCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedDetailsService {

    private final FeedImageRepository feedImageRepository;
    private final FeedRepository feedRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional
    public FeedDetailsResponse execute(Long feedId){
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        User user = userFacade.getCurrentUser();

        FeedViewCount feedViewCount = feedViewCountRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        List<String> imageUrl = feedImageRepository.findByFeed(feed)
                .stream()
                .map(FeedImage::getImageUrl)
                .collect(Collectors.toList());

        feedViewCount.addViewCount();
        feedViewCountRepository.save(feedViewCount);

        return FeedDetailsResponse.builder()
                .feedDetailsResponse(feedRepository.queryFeedDetails(feedId, user.getId()))
                .imageUrl(imageUrl)
                .viewCount(feedViewCount.getViewCount())
                .build();
    }
}