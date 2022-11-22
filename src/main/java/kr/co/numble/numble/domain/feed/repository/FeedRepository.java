package kr.co.numble.numble.domain.feed.repository;

import kr.co.numble.numble.domain.feed.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FeedRepository extends JpaRepository<Feed, Long>, FeedRepositoryCustom, QuerydslPredicateExecutor<Feed> {

}
