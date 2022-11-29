package kr.co.souso.souso.domain.category.presentation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.category.presentation.dto.response.CategoryListResponse;
import kr.co.souso.souso.domain.category.presentation.dto.response.QueryFeedCategoryPagesResponse;
import kr.co.souso.souso.domain.category.service.QueryCategoryService;
import kr.co.souso.souso.domain.category.service.QueryFeedCategoryPagesService;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.feed.presentation.dto.response.QueryFeedDetailsResponse;
import kr.co.souso.souso.domain.feed.service.QueryFeedDetailsService;
import kr.co.souso.souso.domain.feed.service.QueryFeedPagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final QueryCategoryService queryCategoryService;
    private final QueryFeedCategoryPagesService queryFeedCategoryPagesService;

    @ApiOperation(value = "총 카테고리 정보")
    @GetMapping
    public CategoryListResponse getAllCategory() {
        return queryCategoryService.execute();
    }

    @ApiOperation(value = "카테고리 게시글 정보")
    @GetMapping("/{category-id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cursorId", value = "마지막으로 불러온 가장 작은 게시글ID, 처음은 0", required = true, dataType = "string", paramType = "query", defaultValue = "0"),
    })
    public QueryFeedCategoryPagesResponse getCategoryFeeds(@RequestParam(defaultValue = "0") Long cursorId, @PathVariable("category-id") Long categoryId) {
        return queryFeedCategoryPagesService.execute(categoryId, cursorId);
    }
}
