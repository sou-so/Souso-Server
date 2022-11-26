package kr.co.souso.souso.domain.category.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryVO {

    private Long categoryId;
    private String categoryName;

    @QueryProjection
    public CategoryVO(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
