package kr.co.numble.numble.domain.feed.domain.repository;


import kr.co.numble.numble.domain.feed.domain.repository.vo.FeedDetailsVO;
import kr.co.numble.numble.global.enums.SortPageType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedRepositoryCustom {

    FeedDetailsVO queryFeedDetails(Long FeedId, Long userId);

    Slice<FeedDetailsVO> queryFeedPages(Long userId, Long cursorId, SortPageType sortType, Pageable pageable);
}