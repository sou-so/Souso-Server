package kr.co.numble.numble.domain.category.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

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
