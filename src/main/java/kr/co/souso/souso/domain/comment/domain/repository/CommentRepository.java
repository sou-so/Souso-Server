package kr.co.souso.souso.domain.comment.domain.repository;

import kr.co.souso.souso.domain.comment.domain.Comment;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom, QuerydslPredicateExecutor<Comment> {

    List<Comment> findCommentByFeedIdAndUserId(Long feedId, Long userId);

    Long countByUser(User user);
}
