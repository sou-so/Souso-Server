package kr.co.souso.souso.domain.category.presentation.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryListResponse {

    @ApiModelProperty(value = "총 카테고리 개수", example = "10")
    private final Integer totalCount;

    @ApiModelProperty(value = "카테고리 정보")
    private final List<CategoryResponse> categoryList;

}
