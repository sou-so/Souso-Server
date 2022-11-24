package kr.co.numble.numble.domain.category.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class CategoryVO {

    private final Long categoryId;
    private final String categoryName;

    @QueryProjection
    public CategoryVO(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
