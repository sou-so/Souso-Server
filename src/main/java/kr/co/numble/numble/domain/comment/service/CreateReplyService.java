package kr.co.numble.numble.domain.comment.service;

import kr.co.numble.numble.domain.comment.domain.Comment;
import kr.co.numble.numble.domain.comment.exception.CommentNotFoundException;
import kr.co.numble.numble.domain.comment.presentation.dto.request.CreateReplyRequest;
import kr.co.numble.numble.domain.comment.domain.repository.CommentRepository;
import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.exception.FeedNotFoundException;
import kr.co.numble.numble.domain.feed.domain.repository.FeedRepository;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateReplyService {

    private final UserFacade userFacade;
    private final FeedRepository feedRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void execute(Long commentId, CreateReplyRequest request) {
        User user = userFacade.getCurrentUser();

        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        Feed feed = feedRepository.findById(parentComment.getFeed().getId())
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        Comment comment = Comment.builder()
                .feed(feed)
                .user(user)
                .content(request.getContent())
                .parentComment(parentComment)
                .build();

        commentRepository.save(comment);
    }
}
