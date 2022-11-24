package kr.co.numble.numble.domain.comment.domain.repository;

import kr.co.numble.numble.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
