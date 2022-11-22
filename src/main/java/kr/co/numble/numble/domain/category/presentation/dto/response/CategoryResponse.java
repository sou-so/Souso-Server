package kr.co.numble.numble.domain.category.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import kr.co.numble.numble.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class CategoryResponse {

    @ApiModelProperty(value = "카테고리 id", example = "10")
    private Long categoryId;
    @ApiModelProperty(value = "카테고리 이름", example = "동네생활")
    private String categoryName;

    public CategoryResponse(Category category) {
        this.categoryId = category.getId();
        this.categoryName = category.getCategoryName();
    }
}
