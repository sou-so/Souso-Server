package kr.co.souso.souso.domain.feed.presentation.dto.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateFeedResponse {

    @ApiModelProperty(value = "게시글 ID")
    private Long feedId;

}
