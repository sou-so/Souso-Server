package kr.co.souso.souso.domain.comment.domain.repository;

import kr.co.souso.souso.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom, QuerydslPredicateExecutor<Comment> {

    List<Comment> findCommentByFeedIdAndUserId(Long feedId, Long userId);

}
