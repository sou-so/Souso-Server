package kr.co.souso.souso.domain.feed.service;

import kr.co.souso.souso.domain.category.presentation.dto.response.CategoryResponse;
import kr.co.souso.souso.domain.category.domain.repository.vo.CategoryVO;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.feed.domain.FeedImage;
import kr.co.souso.souso.domain.feed.domain.repository.vo.FeedDetailsVO;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.feed.presentation.dto.response.QueryFeedDetailsResponse;
import kr.co.souso.souso.domain.feed.domain.repository.FeedImageRepository;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.domain.repository.vo.AuthorVO;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.AuthorResponse;
import kr.co.souso.souso.domain.viewcount.domain.repository.FeedViewCountRepository;
import kr.co.souso.souso.domain.viewcount.domain.FeedViewCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryFeedDetailsService {

    private final FeedImageRepository feedImageRepository;
    private final FeedRepository feedRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional
    public QueryFeedDetailsResponse execute(Long feedId) {
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

        return QueryFeedDetailsResponse.builder()
                .category(buildCategoryResponse(feedDetailsVO.getCategoryVO()))
                .author(buildAuthorResponse(feedDetailsVO.getAuthorVO()))
                .feedId(feedDetailsVO.getFeedId())
                .content(feedDetailsVO.getContent())
                .imageUrl(imageUrl)
                .isLike(feedDetailsVO.getIsLike())
                .isBookmark(feedDetailsVO.getIsBookmark())
                .viewCount(feedViewCount.getViewCount())
                .commentCount(feedDetailsVO.getCommentCount())
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