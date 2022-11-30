package kr.co.souso.souso.domain.comment.domain.repository;

import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentConditionVO;
import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentDetailsVO;
import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentFeedIdVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentDetailsVO> queryReplyDetailsList(Long parentCommentId, Long userId, Long feedId);

    Slice<CommentDetailsVO> queryCommentPagesByOffset(CommentConditionVO commentConditionVO, Pageable pageable);

    Slice<CommentFeedIdVO> queryCommentFeedIdPagesByOffset(CommentConditionVO commentConditionVO, Pageable pageable);

}
