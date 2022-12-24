package kr.co.souso.souso.domain.comment.service;

import kr.co.souso.souso.domain.comment.domain.Comment;
import kr.co.souso.souso.domain.comment.presentation.dto.request.CreateCommentRequest;
import kr.co.souso.souso.domain.comment.domain.repository.CommentRepository;
import kr.co.souso.souso.domain.comment.presentation.dto.response.CreateCommentResponse;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCommentService {

    private final UserFacade userFacade;
    private final FeedRepository feedRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateCommentResponse execute(Long feedId, CreateCommentRequest request) {
        User user = userFacade.getCurrentUser();

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        Comment comment = Comment.builder()
                .feed(feed)
                .user(user)
                .content(request.getContent())
                .build();

        commentRepository.save(comment);

        return new CreateCommentResponse(comment.getId());
    }
}
