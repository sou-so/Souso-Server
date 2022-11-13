package kr.co.numble.numble.domain.post.domain.repository;

import kr.co.numble.numble.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
