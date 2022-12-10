package kr.co.souso.souso.domain.comment.service;

import kr.co.souso.souso.domain.comment.domain.Comment;
import kr.co.souso.souso.domain.comment.domain.repository.CommentRepository;
import kr.co.souso.souso.domain.comment.exception.CommentNotFoundException;
import kr.co.souso.souso.domain.comment.presentation.dto.request.UpdateCommentRequest;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.exception.NotValidUserException;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateCommentService {

    private final UserFacade userFacade;
    private final CommentRepository commentRepository;

    public void execute(Long commentId, UpdateCommentRequest request) {
        User user = userFacade.getCurrentUser();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        if(!Objects.equals(user.getId(), comment.getUser().getId())){
            throw NotValidUserException.EXCEPTION;
        }

        comment.updateComment(request.getContent());
        commentRepository.save(comment);
    }
}
