package kr.co.souso.souso.domain.category.service;

import kr.co.souso.souso.domain.category.domain.repository.FeedCategoryRepository;
import kr.co.souso.souso.domain.category.domain.repository.vo.FeedCategoryDetailsVO;
import kr.co.souso.souso.domain.category.presentation.dto.response.QueryFeedCategoryDetailsResponse;
import kr.co.souso.souso.domain.category.presentation.dto.response.QueryFeedCategoryPagesResponse;
import kr.co.souso.souso.domain.feed.domain.FeedImage;
import kr.co.souso.souso.domain.feed.domain.repository.FeedImageRepository;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.domain.repository.vo.AuthorVO;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.AuthorResponse;
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
public class QueryFeedCategoryPagesService {

    private final FeedCategoryRepository feedCategoryRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryFeedCategoryPagesResponse execute(Long categoryId, Long cursorId) {
        User user = userFacade.getCurrentUser();

        Slice<FeedCategoryDetailsVO> feedList = feedCategoryRepository.queryFeedCategoryPagesByCursor(user.getId(), categoryId, PagingSupportUtil.applyCursorId(cursorId), PagingSupportUtil.applyPageSize());

        List<QueryFeedCategoryDetailsResponse> queryFeedCategoryDetailsResponse = new ArrayList<>();

        for (FeedCategoryDetailsVO feedCategoryDetailsVO : feedList) {
            List<String> imageUrl = feedImageRepository.findByFeedId(feedCategoryDetailsVO.getFeedId())
                    .stream()
                    .map(FeedImage::getImageUrl)
                    .collect(Collectors.toList());

            FeedViewCount feedViewCount = feedViewCountRepository.findById(feedCategoryDetailsVO.getFeedId())
                    .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

            queryFeedCategoryDetailsResponse.add(
                    buildFeedCategoryDetailsResponse(feedCategoryDetailsVO, imageUrl, feedViewCount.getViewCount())
            );
        }

        return new QueryFeedCategoryPagesResponse(queryFeedCategoryDetailsResponse, feedList.hasNext(), queryFeedCategoryDetailsResponse.size());
    }

    private AuthorResponse buildAuthorResponse(AuthorVO authorVO) {
        return AuthorResponse.builder()
                .userId(authorVO.getUserId())
                .nickname(authorVO.getNickname())
                .birth(authorVO.getBirth())
                .profileImageUrl(authorVO.getProfileImageUrl())
                .build();
    }

    private QueryFeedCategoryDetailsResponse buildFeedCategoryDetailsResponse(FeedCategoryDetailsVO feedCategoryDetailsVO, List<String> imageUrl, Long feedViewCount) {
        return QueryFeedCategoryDetailsResponse.builder()
                .author(buildAuthorResponse(feedCategoryDetailsVO.getAuthorVO()))
                .feedId(feedCategoryDetailsVO.getFeedId())
                .imageUrl(imageUrl)
                .createdAt(feedCategoryDetailsVO.getCreatedAt())
                .content(feedCategoryDetailsVO.getContent())
                .isLike(feedCategoryDetailsVO.getIsLike())
                .isBookmark(feedCategoryDetailsVO.getIsBookmark())
                .likeCount(feedCategoryDetailsVO.getLikeCount())
                .commentCount(feedCategoryDetailsVO.getCommentCount())
                .bookmarkCount(feedCategoryDetailsVO.getBookmarkCount())
                .viewCount(feedViewCount)
                .build();
    }
}
