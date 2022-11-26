package kr.co.souso.souso.domain.category.service;

import kr.co.souso.souso.domain.category.presentation.dto.response.CategoryListResponse;
import kr.co.souso.souso.domain.category.presentation.dto.response.CategoryResponse;
import kr.co.souso.souso.domain.category.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryQueryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryListResponse execute() {
        List<CategoryResponse> categories = categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());
        return new CategoryListResponse(categories.size(), categories);
    }
}
