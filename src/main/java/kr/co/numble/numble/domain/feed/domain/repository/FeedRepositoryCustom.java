package kr.co.numble.numble.domain.feed.domain.repository;


import kr.co.numble.numble.domain.feed.domain.repository.vo.FeedDetailsVO;

public interface FeedRepositoryCustom {

    FeedDetailsVO queryFeedDetails(Long FeedId, Long userId);

}