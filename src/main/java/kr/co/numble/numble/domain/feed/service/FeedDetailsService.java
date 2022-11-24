package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.bookmark.domain.repository.FeedBookmarkRepository;
import kr.co.numble.numble.domain.category.presentation.dto.response.CategoryResponse;
import kr.co.numble.numble.domain.category.domain.repository.vo.CategoryVO;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.domain.FeedImage;
import kr.co.numble.numble.domain.feed.domain.repository.vo.FeedDetailsVO;
import kr.co.numble.numble.domain.feed.exception.FeedNotFoundException;
import kr.co.numble.numble.domain.feed.presentation.dto.response.FeedDetailsResponse;
import kr.co.numble.numble.domain.feed.domain.repository.FeedImageRepository;
import kr.co.numble.numble.domain.feed.domain.repository.FeedRepository;
import kr.co.numble.numble.domain.like.domain.repository.FeedLikeRepository;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.domain.repository.vo.AuthorVO;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import kr.co.numble.numble.domain.user.presentation.dto.response.AuthorResponse;
import kr.co.numble.numble.domain.viewcount.domain.repository.FeedViewCountRepository;
import kr.co.numble.numble.domain.viewcount.domain.FeedViewCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FeedDetailsService {

    private final FeedImageRepository feedImageRepository;
    private final FeedRepository feedRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional
    public FeedDetailsResponse execute(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        User user = userFacade.getCurrentUser();

        FeedViewCount feedViewCount = feedViewCountRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);
        feedViewCount.addViewCount();
        feedViewCountRepository.save(feedViewCount);

        List<String> imageUrl = feedImageRepository.findByFeed(feed)
                .stream()
                .map(FeedImage::getImageUrl)
                .collect(Collectors.toList());

        FeedDetailsVO feedDetailsVO = feedRepository.queryFeedDetails(feedId, user.getId());
        return FeedDetailsResponse.builder()
                .category(buildCategoryResponse(feedDetailsVO.getCategoryVO()))
                .author(buildAuthorResponse(feedDetailsVO.getAuthorVO()))
                .content(feedDetailsVO.getContent())
                .imageUrl(imageUrl)
                .isLike(feedDetailsVO.getIsLike())
                .isBookmark(feedDetailsVO.getIsBookmark())
                .viewCount(feedViewCount.getViewCount())
                .createdAt(feedDetailsVO.getCreatedAt())
                .likeCount(feed.getLikeCount())
                .bookmarkCount(feed.getBookmarkCount())
                .build();
    }

    private CategoryResponse buildCategoryResponse(CategoryVO categoryVO) {
        return CategoryResponse.builder()
                .categoryId(categoryVO.getCategoryId())
                .categoryName(categoryVO.getCategoryName())
                .build();
    }

    private AuthorResponse buildAuthorResponse(AuthorVO authorVO) {
        return AuthorResponse.builder()
                .userId(authorVO.getUserId())
                .nickname(authorVO.getNickname())
                .birth(authorVO.getBirth())
                .profileImageUrl(authorVO.getProfileImageUrl())
                .build();
    }
}