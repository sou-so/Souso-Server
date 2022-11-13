package kr.co.numble.numble.domain.post.domain.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PostUploadRequest {

    @NotNull(message = "title은 Null을 허용하지 않습니다.")
    private String title;

    @NotNull(message = "content는 Null을 허용하지 않습니다.")
    private String content;
}
