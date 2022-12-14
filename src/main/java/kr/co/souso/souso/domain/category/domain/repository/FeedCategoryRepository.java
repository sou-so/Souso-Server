package kr.co.souso.souso.domain.category.domain.repository;

import kr.co.souso.souso.domain.category.domain.FeedCategory;
import kr.co.souso.souso.domain.category.domain.FeedCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedCategoryRepository extends JpaRepository<FeedCategory, FeedCategoryId>{

    FeedCategory findFeedCategoryByFeedId(Long feedId);

}
