package kr.co.souso.souso.domain.comment.domain.repository;

import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentDetailsVO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentDetailsVO> queryReplyDetailsList(Long parentCommentId, Long userId, Long feedId);

    Slice<CommentDetailsVO> queryCommentPages(Long userId, Long feedId, Integer pageId, Pageable pageable);

}
