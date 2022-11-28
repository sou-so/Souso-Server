package kr.co.souso.souso.domain.category.domain.repository;

import kr.co.souso.souso.domain.category.domain.repository.vo.FeedCategoryDetailsVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FeedCategoryRepositoryCustom {

    Slice<FeedCategoryDetailsVO> queryFeedCategoryPagesByCursor(Long userId, Long categoryId, Long cursorId, Pageable pageable);

}
