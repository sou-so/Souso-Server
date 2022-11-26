package kr.co.souso.souso.domain.viewcount.domain.repository;

import kr.co.souso.souso.domain.viewcount.domain.FeedViewCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedViewCountRepository extends JpaRepository<FeedViewCount, Long> {

}
