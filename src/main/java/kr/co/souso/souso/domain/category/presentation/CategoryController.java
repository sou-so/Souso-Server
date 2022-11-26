package kr.co.souso.souso.domain.category.presentation;

import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.category.presentation.dto.response.CategoryListResponse;
import kr.co.souso.souso.domain.category.service.QueryCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final QueryCategoryService queryCategoryService;

    @ApiOperation(value = "총 카테고리 정보")
    @GetMapping
    public CategoryListResponse getAllCategory() {
        return queryCategoryService.execute();
    }
}
