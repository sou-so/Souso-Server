package kr.co.numble.numble.domain.post.domain.presentation;

import kr.co.numble.numble.domain.post.domain.presentation.dto.request.PostUploadRequest;
import kr.co.numble.numble.domain.post.domain.service.PostUploadService;
import kr.co.numble.numble.global.response.GlobalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostController {

    private final PostUploadService postUploadService;

    @PostMapping
    public GlobalResponse<?> upload(@RequestBody @Valid PostUploadRequest postUploadRequest) {
        postUploadService.upload(postUploadRequest);
        return GlobalResponse.success("게시글 업로드 완료");
    }

}
