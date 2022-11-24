package kr.co.numble.numble.domain.bookmark.domain.repository;

import kr.co.numble.numble.domain.bookmark.domain.FeedBookmark;
import kr.co.numble.numble.domain.bookmark.domain.FeedBookmarkId;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedBookmarkRepository extends JpaRepository<FeedBookmark, FeedBookmarkId> {

    Optional<FeedBookmark> findByFeedAndUser(Feed feed, User user);

}
