package kr.co.numble.numble.domain.category.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class CategoryListResponse {
    
    private final Integer totalCount;
    private final List<CategoryResponse> categoryList;

}
