package kr.co.numble.numble.domain.category.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class CategoryResponse {
    private Long categoryId;
    private String categoryName;
    private String categoryImageUrl;
}
