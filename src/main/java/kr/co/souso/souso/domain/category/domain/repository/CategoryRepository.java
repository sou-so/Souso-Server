package kr.co.souso.souso.domain.category.domain.repository;

import kr.co.souso.souso.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
