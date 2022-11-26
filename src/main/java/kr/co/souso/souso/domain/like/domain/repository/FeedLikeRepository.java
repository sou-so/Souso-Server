package kr.co.souso.souso.domain.like.domain.repository;

import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.like.domain.FeedLike;
import kr.co.souso.souso.domain.like.domain.FeedLikeId;
import kr.co.souso.souso.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedLikeRepository extends JpaRepository<FeedLike, FeedLikeId> {

    Optional<FeedLike> findByFeedAndUser(Feed feed, User user);

}
