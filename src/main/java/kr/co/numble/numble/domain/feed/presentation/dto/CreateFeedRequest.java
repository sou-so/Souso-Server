package kr.co.numble.numble.domain.feed.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class CreateFeedRequest {

    @Size(min = 3, max = 30, message = "title은 3글자 이상 30글자 이하여야합니다.")
    @NotBlank(message = "title은 Null, 오직 공백만을 허용하지 않습니다.")
    private String title;

    @NotNull(message = "content는 Null을 허용하지 않습니다.")
    private String content;

//    @NotBlank(message = "title은 Null, 오직 공백만을 허용하지 않습니다.")
    @JsonProperty("categoryId")
    private Long categoryId;

}
