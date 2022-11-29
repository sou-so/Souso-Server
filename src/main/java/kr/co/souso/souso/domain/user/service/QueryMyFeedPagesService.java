package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.category.domain.repository.vo.CategoryVO;
import kr.co.souso.souso.domain.category.presentation.dto.response.CategoryResponse;
import kr.co.souso.souso.domain.feed.domain.FeedImage;
import kr.co.souso.souso.domain.feed.domain.repository.FeedImageRepository;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.feed.domain.repository.vo.FeedDetailsVO;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.feed.presentation.dto.response.QueryFeedPagesResponse;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyFeedDetailsResponse;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyFeedPagesResponse;
import kr.co.souso.souso.domain.viewcount.domain.FeedViewCount;
import kr.co.souso.souso.domain.viewcount.domain.repository.FeedViewCountRepository;
import kr.co.souso.souso.global.utils.code.PagingSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryMyFeedPagesService {

    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyFeedPagesResponse execute(Long cursorId) {
        User user = userFacade.getCurrentUser();

        Slice<FeedDetailsVO> feedList = feedRepository.queryFeedPagesByCursor(user.getId(), PagingSupportUtil.applyCursorId(cursorId), null, user.getId(), PagingSupportUtil.applyPageSize());

        List<QueryMyFeedDetailsResponse> queryMyFeedDetailsResponseList = new ArrayList<>();

        for (FeedDetailsVO feedDetailsVO : feedList) {
            List<String> imageUrl = feedImageRepository.findByFeedId(feedDetailsVO.getFeedId())
                    .stream()
                    .map(FeedImage::getImageUrl)
                    .collect(Collectors.toList());

            FeedViewCount feedViewCount = feedViewCountRepository.findById(feedDetailsVO.getFeedId())
                    .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

            queryMyFeedDetailsResponseList.add(
                    buildQueryMyFeedDetailsResponse(feedDetailsVO, imageUrl, feedViewCount.getViewCount())
            );
        }

        return new QueryMyFeedPagesResponse(queryMyFeedDetailsResponseList, feedList.hasNext(), queryMyFeedDetailsResponseList.size());
    }

    private CategoryResponse buildCategoryResponse(CategoryVO categoryVO) {
        return CategoryResponse.builder()
                .categoryId(categoryVO.getCategoryId())
                .categoryName(categoryVO.getCategoryName())
                .build();
    }

    private QueryMyFeedDetailsResponse buildQueryMyFeedDetailsResponse(FeedDetailsVO feedDetailsVO, List<String> imageUrl, Long feedViewCount) {
        return QueryMyFeedDetailsResponse.builder()
                .category(buildCategoryResponse(feedDetailsVO.getCategoryVO()))
                .feedId(feedDetailsVO.getFeedId())
                .imageUrl(imageUrl)
                .createdAt(feedDetailsVO.getCreatedAt())
                .content(feedDetailsVO.getContent())
                .isLike(feedDetailsVO.getIsLike())
                .isBookmark(feedDetailsVO.getIsBookmark())
                .likeCount(feedDetailsVO.getLikeCount())
                .commentCount(feedDetailsVO.getCommentCount())
                .bookmarkCount(feedDetailsVO.getBookmarkCount())
                .viewCount(feedViewCount)
                .build();
    }
}
