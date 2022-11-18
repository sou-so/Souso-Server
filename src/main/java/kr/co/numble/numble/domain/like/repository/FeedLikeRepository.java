package kr.co.numble.numble.domain.like.repository;

import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.like.entity.FeedLike;
import kr.co.numble.numble.domain.like.entity.FeedLikeId;
import kr.co.numble.numble.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedLikeRepository extends JpaRepository<FeedLike, FeedLikeId> {

    Optional<FeedLike> findByFeedAndUser(Feed feed, User user);

}
