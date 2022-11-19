package kr.co.numble.numble.domain.category.presentation.dto.response;

import kr.co.numble.numble.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
    private String categoryImageUrl;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.categoryName = category.getCategoryName();
        this.categoryImageUrl = category.getCategoryImageUrl();
    }
}
