package kr.co.numble.numble.domain.category.repository;

import kr.co.numble.numble.domain.category.entity.FeedCategory;
import kr.co.numble.numble.domain.category.entity.FeedCategoryId;
import kr.co.numble.numble.domain.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCategoryRepository extends JpaRepository<FeedCategory, FeedCategoryId> {

    FeedCategory findFeedCategoryByFeedId(Long feedId);

    void deleteByFeed(Feed feed);
}
