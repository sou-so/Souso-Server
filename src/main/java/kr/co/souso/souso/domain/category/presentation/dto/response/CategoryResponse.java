package kr.co.souso.souso.domain.category.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import kr.co.souso.souso.domain.category.domain.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResponse {

    @ApiModelProperty(value = "카테고리 id", example = "10")
    private final Long categoryId;
    @ApiModelProperty(value = "카테고리 이름", example = "동네생활")
    private final String categoryName;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.categoryName = category.getCategoryName();
    }

    @Builder
    public CategoryResponse(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
