package kr.co.souso.souso.domain.feed.domain.repository;

import kr.co.souso.souso.domain.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FeedRepository extends JpaRepository<Feed, Long>, FeedRepositoryCustom, QuerydslPredicateExecutor<Feed> {

}
