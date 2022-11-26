package kr.co.souso.souso.domain.category.facade;

import kr.co.souso.souso.domain.category.domain.Category;
import kr.co.souso.souso.domain.category.exception.CategoryNotFoundException;
import kr.co.souso.souso.domain.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryFacade {

    private final CategoryRepository categoryRepository;

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }
}
