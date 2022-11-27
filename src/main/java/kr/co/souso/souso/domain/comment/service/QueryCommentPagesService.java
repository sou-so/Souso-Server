package kr.co.souso.souso.domain.comment.service;

import kr.co.souso.souso.domain.comment.domain.repository.CommentRepository;
import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentDetailsVO;
import kr.co.souso.souso.domain.comment.presentation.dto.response.CommentResponse;
import kr.co.souso.souso.domain.comment.presentation.dto.response.QueryCommentResponse;
import kr.co.souso.souso.domain.comment.presentation.dto.response.QueryReplyResponse;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.domain.repository.vo.AuthorVO;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.AuthorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryCommentPagesService {

    private static final int DEFAULT_PAGE_SIZE = 10;

    private final CommentRepository commentRepository;
    private final UserFacade userFacade;

    public CommentResponse execute(Integer pageId, Long feedId) {
        User user = userFacade.getCurrentUser();

        List<QueryCommentResponse> queryCommentResponseList = new ArrayList<>();
        Slice<CommentDetailsVO> commentList = commentRepository.queryCommentPages(user.getId(), feedId, pageId, PageRequest.of(0, DEFAULT_PAGE_SIZE));

        for (CommentDetailsVO commentDetailsVO : commentList) {
            List<CommentDetailsVO> replyDetailsList = commentRepository.queryReplyDetailsList(commentDetailsVO.getParentCommentId(), user.getId(), feedId);
            queryCommentResponseList.add(
                    buildCommentResponse(commentDetailsVO, buildReplyResponse(replyDetailsList))
            );
        }
        return new CommentResponse(queryCommentResponseList, commentList.hasNext());
    }

    private QueryCommentResponse buildCommentResponse(CommentDetailsVO commentDetailsVO, List<QueryReplyResponse> queryReplyResponse) {
        return QueryCommentResponse.builder()
                .author(buildAuthorResponse(commentDetailsVO.getAuthor()))
                .commentId(commentDetailsVO.getCommentId())
                .isFeedOwner(commentDetailsVO.getIsFeedOwner())
                .isCommentOwner(commentDetailsVO.getIsCommentOwner())
                .commentId(commentDetailsVO.getCommentId())
                .content(commentDetailsVO.getContent())
                .createdAt(commentDetailsVO.getCreatedAt())
                .reply(queryReplyResponse)
                .build();
    }


    private AuthorResponse buildAuthorResponse(AuthorVO authorVO) {
        return AuthorResponse.builder()
                .userId(authorVO.getUserId())
                .nickname(authorVO.getNickname())
                .birth(authorVO.getBirth())
                .profileImageUrl(authorVO.getProfileImageUrl())
                .build();
    }

    private List<QueryReplyResponse> buildReplyResponse(List<CommentDetailsVO> commentDetailsVOList) {
        List<QueryReplyResponse> queryReplyResponseList = new ArrayList<>();
        for (CommentDetailsVO commentDetailsVO : commentDetailsVOList) {
            queryReplyResponseList.add(
                    QueryReplyResponse.builder()
                            .author(buildAuthorResponse(commentDetailsVO.getAuthor()))
                            .commentId(commentDetailsVO.getCommentId())
                            .isFeedOwner(commentDetailsVO.getIsFeedOwner())
                            .isCommentOwner(commentDetailsVO.getIsCommentOwner())
                            .content(commentDetailsVO.getContent())
                            .createdAt(commentDetailsVO.getCreatedAt())
                            .build()
            );
        }
        return queryReplyResponseList;
    }
}
