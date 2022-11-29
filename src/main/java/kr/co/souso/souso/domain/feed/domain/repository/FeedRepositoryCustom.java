package kr.co.souso.souso.domain.feed.domain.repository;


import kr.co.souso.souso.domain.feed.domain.repository.vo.FeedConditionVO;
import kr.co.souso.souso.domain.feed.domain.repository.vo.FeedDetailsVO;
import kr.co.souso.souso.global.enums.SortPageType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedRepositoryCustom {

    FeedDetailsVO queryFeedDetails(Long FeedId, Long userId);

    Slice<FeedDetailsVO> queryFeedPagesByCursor(FeedConditionVO feedConditionVO, Pageable pageable);

    Slice<FeedDetailsVO> queryFeedPagesByOffset(FeedConditionVO feedConditionVO, Pageable pageable);

}