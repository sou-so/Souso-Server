package kr.co.numble.numble.domain.feed.repository;

import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.domain.FeedImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedImageRepository extends JpaRepository<FeedImage, Long> {

    void deleteAllByFeed(Feed feed);

}
