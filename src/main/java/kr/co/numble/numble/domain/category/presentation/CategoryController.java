package kr.co.numble.numble.domain.category.presentation;

import kr.co.numble.numble.domain.category.presentation.dto.response.CategoryListResponse;
import kr.co.numble.numble.domain.category.service.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryQueryService categoryQueryService;

    @GetMapping
    public CategoryListResponse getAllCategory() {
        return categoryQueryService.execute();
    }

}
