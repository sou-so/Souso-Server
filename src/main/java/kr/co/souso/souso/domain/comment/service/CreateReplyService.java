package kr.co.souso.souso.domain.comment.service;

import kr.co.souso.souso.domain.comment.domain.Comment;
import kr.co.souso.souso.domain.comment.exception.CommentNotFoundException;
import kr.co.souso.souso.domain.comment.exception.InvalidCommentException;
import kr.co.souso.souso.domain.comment.presentation.dto.request.CreateReplyRequest;
import kr.co.souso.souso.domain.comment.domain.repository.CommentRepository;
import kr.co.souso.souso.domain.comment.presentation.dto.response.CreateReplyResponse;
import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.feed.exception.FeedNotFoundException;
import kr.co.souso.souso.domain.feed.domain.repository.FeedRepository;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateReplyService {

    private final UserFacade userFacade;
    private final FeedRepository feedRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CreateReplyResponse execute(Long commentId, CreateReplyRequest request) {
        User user = userFacade.getCurrentUser();

        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        if (!Objects.equals(parentComment.getId(), parentComment.getParentComment().getId())) {
            throw InvalidCommentException.EXCEPTION;
        }

        Feed feed = feedRepository.findById(parentComment.getFeed().getId())
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        Comment comment = Comment.builder()
                .feed(feed)
                .user(user)
                .content(request.getContent())
                .parentComment(parentComment)
                .build();

        commentRepository.save(comment);
        feed.addComment();
        feedRepository.save(feed);
        return new CreateReplyResponse(comment.getId());
    }
}
