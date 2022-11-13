package kr.co.numble.numble.domain.post.domain.service;

import kr.co.numble.numble.domain.post.domain.Post;
import kr.co.numble.numble.domain.post.domain.presentation.dto.request.PostUploadRequest;
import kr.co.numble.numble.domain.post.domain.repository.PostRepository;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostUploadService {

    private final PostRepository postRepository;
    private final UserFacade userFacade;

    @Transactional
    public void upload(PostUploadRequest postUploadRequest) {
        postRepository.save(Post.builder()
                .title(postUploadRequest.getTitle())
                .content(postUploadRequest.getContent())
                .user(userFacade.getCurrentUser())
                .build());
    }
}
