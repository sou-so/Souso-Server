package kr.co.numble.numble.domain.category.service;

import kr.co.numble.numble.domain.category.presentation.dto.response.CategoryListResponse;
import kr.co.numble.numble.domain.category.presentation.dto.response.CategoryResponse;
import kr.co.numble.numble.domain.category.repository.CategoryRepository;
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
                .map(c -> new CategoryResponse(c.getId(), c.getCategoryName(), c.getCategoryImageUrl()))
                .collect(Collectors.toList());
        return new CategoryListResponse(categories.size(), categories);
    }
}
