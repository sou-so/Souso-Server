package kr.co.numble.numble.domain.feed.repository;

import kr.co.numble.numble.domain.feed.presentation.dto.response.FeedDetailsResponse;
import kr.co.numble.numble.domain.user.domain.User;

public interface FeedRepositoryCustom {

    FeedDetailsResponse queryFeedDetails(Long FeedId, Long userId);

}