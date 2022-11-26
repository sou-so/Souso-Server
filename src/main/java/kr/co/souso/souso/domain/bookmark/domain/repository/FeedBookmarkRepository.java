package kr.co.souso.souso.domain.bookmark.domain.repository;

import kr.co.souso.souso.domain.bookmark.domain.FeedBookmark;
import kr.co.souso.souso.domain.bookmark.domain.FeedBookmarkId;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedBookmarkRepository extends JpaRepository<FeedBookmark, FeedBookmarkId> {

    Optional<FeedBookmark> findByFeedAndUser(Feed feed, User user);

}
