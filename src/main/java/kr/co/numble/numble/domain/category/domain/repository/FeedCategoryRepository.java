package kr.co.numble.numble.domain.category.domain.repository;

import kr.co.numble.numble.domain.category.domain.FeedCategory;
import kr.co.numble.numble.domain.category.domain.FeedCategoryId;
import kr.co.numble.numble.domain.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCategoryRepository extends JpaRepository<FeedCategory, FeedCategoryId> {

    FeedCategory findFeedCategoryByFeedId(Long feedId);

    void deleteByFeed(Feed feed);
}
