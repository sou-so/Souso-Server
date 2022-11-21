package kr.co.numble.numble.domain.comment.repository;

import kr.co.numble.numble.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
