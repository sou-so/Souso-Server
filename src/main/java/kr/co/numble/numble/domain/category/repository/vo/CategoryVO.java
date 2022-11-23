package kr.co.numble.numble.domain.category.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import kr.co.numble.numble.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CategoryVO {

    @ApiModelProperty(value = "카테고리 id", example = "10")
    private final Long categoryId;
    @ApiModelProperty(value = "카테고리 이름", example = "동네생활")
    private final String categoryName;

    @QueryProjection
    public CategoryVO(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
