package kr.co.souso.souso.domain.comment.domain.repository;

import kr.co.souso.souso.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
