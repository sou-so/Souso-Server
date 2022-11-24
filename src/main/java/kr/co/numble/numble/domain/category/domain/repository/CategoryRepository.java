package kr.co.numble.numble.domain.category.domain.repository;

import kr.co.numble.numble.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
