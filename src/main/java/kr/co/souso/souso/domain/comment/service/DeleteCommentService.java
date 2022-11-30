package kr.co.souso.souso.domain.comment.service;

import kr.co.souso.souso.domain.comment.domain.Comment;
import kr.co.souso.souso.domain.comment.domain.repository.CommentRepository;
import kr.co.souso.souso.domain.comment.exception.CommentNotFoundException;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.exception.NotValidUserException;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCommentService {

    private final UserFacade userFacade;
    private final CommentRepository commentRepository;

    @Transactional
    public void execute(Long commentId) {

        User user = userFacade.getCurrentUser();

        Comment comment = commentRepository.findById(commentId).
                orElseThrow(() -> CommentNotFoundException.EXCEPTION);

        if (!user.getId().equals(comment.getUser().getId())) {
            throw NotValidUserException.EXCEPTION;
        }

        user.subComment();
        comment.getFeed().subComment();
        commentRepository.delete(comment);
    }
}
