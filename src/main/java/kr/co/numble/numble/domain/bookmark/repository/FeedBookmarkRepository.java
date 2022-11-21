package kr.co.numble.numble.domain.bookmark.repository;

import kr.co.numble.numble.domain.bookmark.entity.FeedBookmark;
import kr.co.numble.numble.domain.bookmark.entity.FeedBookmarkId;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedBookmarkRepository extends JpaRepository<FeedBookmark, FeedBookmarkId> {

    Optional<FeedBookmark> findByFeedAndUser(Feed feed, User user);

}
