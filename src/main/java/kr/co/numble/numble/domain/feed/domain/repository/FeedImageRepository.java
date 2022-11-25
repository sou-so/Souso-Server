package kr.co.numble.numble.domain.feed.domain.repository;

import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.domain.FeedImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedImageRepository extends JpaRepository<FeedImage, Long> {

    void deleteAllByFeed(Feed feed);

    List<FeedImage> findByFeed(Feed feed);

    List<FeedImage> findByFeedId(Long feedId);

}
