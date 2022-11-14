package kr.co.numble.numble.domain.category.repository;

import kr.co.numble.numble.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
