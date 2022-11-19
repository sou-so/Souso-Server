package kr.co.numble.numble.domain.feed.presentation.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UpdateFeedRequest {

    @ApiModelProperty(value = "게시글 제목", example = "제목")
    @Size(min = 3, max = 30, message = "title은 3글자 이상 30글자 이하여야합니다.")
    @NotBlank(message = "title은 Null, 오직 공백만을 허용하지 않습니다.")
    private String title;

    @ApiModelProperty(value = "게시글 내용", example = "내용")
    @NotNull(message = "content는 Null을 허용하지 않습니다.")
    private String content;

    @ApiModelProperty(value = "카테고리 id", example = "10")
    @NotNull(message = "categoryId는 필수로 입력해야 합니다.")
    private Long categoryId;

}
