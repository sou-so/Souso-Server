package kr.co.souso.souso.domain.category.service;

import kr.co.souso.souso.domain.category.presentation.dto.response.QueryFeedCategoryDetailsResponse;
import kr.co.souso.souso.domain.category.presentation.dto.response.QueryFeedCategoryPagesResponse;
import kr.co.souso.souso.domain.feed.domain.FeedImage;
import kr.co.souso.souso.domain.feed.domain.repository.FeedImageRepository;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.feed.domain.repository.vo.FeedConditionVO;
import kr.co.souso.souso.domain.feed.domain.repository.vo.FeedDetailsVO;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.domain.repository.vo.AuthorVO;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.AuthorResponse;
import kr.co.souso.souso.domain.viewcount.domain.FeedViewCount;
import kr.co.souso.souso.domain.viewcount.domain.repository.FeedViewCountRepository;
import kr.co.souso.souso.domain.viewcount.exception.FeedViewCountNotFoundException;
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
public class QueryFeedCategoryPagesService {

    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryFeedCategoryPagesResponse execute(Long categoryId, Long cursorId) {
        User user = userFacade.getCurrentUser();

        Slice<FeedDetailsVO> feedList = feedRepository.queryFeedPagesByCursor(
                FeedConditionVO.builder()
                        .userId(user.getId())
                        .cursorId(PagingSupportUtil.applyCursorId(cursorId))
                        .categoryId(categoryId)
                        .build(),
                PagingSupportUtil.applyPageSize()
        );

        List<QueryFeedCategoryDetailsResponse> queryFeedDetailsResponseList = new ArrayList<>();

        for (FeedDetailsVO feedDetailsVO : feedList) {
            List<String> imageUrl = feedImageRepository.findByFeedId(feedDetailsVO.getFeedId())
                    .stream()
                    .map(FeedImage::getImageUrl)
                    .collect(Collectors.toList());

            FeedViewCount feedViewCount = feedViewCountRepository.findById(feedDetailsVO.getFeedId())
                    .orElseThrow(() -> FeedViewCountNotFoundException.EXCEPTION);

            queryFeedDetailsResponseList.add(
                    buildFeedCategoryDetailsResponse(feedDetailsVO, imageUrl, feedViewCount.getViewCount())
            );
        }

        return new QueryFeedCategoryPagesResponse(queryFeedDetailsResponseList, feedList.hasNext(), queryFeedDetailsResponseList.size());
    }

    private AuthorResponse buildAuthorResponse(AuthorVO authorVO) {
        return AuthorResponse.builder()
                .userId(authorVO.getUserId())
                .nickname(authorVO.getNickname())
                .birth(authorVO.getBirth())
                .profileImageUrl(authorVO.getProfileImageUrl())
                .build();
    }

    private QueryFeedCategoryDetailsResponse buildFeedCategoryDetailsResponse(FeedDetailsVO feedDetailsVO, List<String> imageUrl, Long feedViewCount) {
        return QueryFeedCategoryDetailsResponse.builder()
                .author(buildAuthorResponse(feedDetailsVO.getAuthorVO()))
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
