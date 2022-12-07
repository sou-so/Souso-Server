package kr.co.souso.souso.domain.comment.service;

import kr.co.souso.souso.domain.comment.domain.repository.CommentRepository;
import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentConditionVO;
import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentDetailsVO;
import kr.co.souso.souso.domain.comment.presentation.dto.response.QueryCommentPagesResponse;
import kr.co.souso.souso.domain.comment.presentation.dto.response.QueryCommentDetailsResponse;
import kr.co.souso.souso.domain.comment.presentation.dto.response.QueryReplyDetailsResponse;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.domain.repository.vo.AuthorVO;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.AuthorResponse;
import kr.co.souso.souso.global.utils.code.PagingSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryCommentPagesService {

    private final CommentRepository commentRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryCommentPagesResponse execute(Integer pageId, Long feedId) {

        User user = userFacade.getCurrentUser();

        List<QueryCommentDetailsResponse> queryCommentDetailsResponseList = new ArrayList<>();

        Slice<CommentDetailsVO> commentList = commentRepository.queryCommentPagesByOffset(CommentConditionVO.builder()
                        .userId(user.getId())
                        .feedId(feedId)
                        .pageId(pageId)
                        .build(),
                PagingSupportUtil.applyPageSize());

        for (CommentDetailsVO commentDetailsVO : commentList) {
            List<CommentDetailsVO> replyDetailsList = commentRepository.queryReplyDetailsList(commentDetailsVO.getParentCommentId(), user.getId(), feedId);
            queryCommentDetailsResponseList.add(
                    buildCommentResponse(commentDetailsVO, buildReplyResponse(replyDetailsList))
            );
        }
        return new QueryCommentPagesResponse(queryCommentDetailsResponseList, commentList.hasNext(), pageId);
    }

    private QueryCommentDetailsResponse buildCommentResponse(CommentDetailsVO commentDetailsVO, List<QueryReplyDetailsResponse> queryReplyDetailsResponse) {
        return QueryCommentDetailsResponse.builder()
                .author(buildAuthorResponse(commentDetailsVO.getAuthor()))
                .commentId(commentDetailsVO.getCommentId())
                .isFeedOwner(commentDetailsVO.getIsFeedOwner())
                .isCommentOwner(commentDetailsVO.getIsCommentOwner())
                .commentId(commentDetailsVO.getCommentId())
                .content(commentDetailsVO.getContent())
                .createdAt(commentDetailsVO.getCreatedAt())
                .reply(queryReplyDetailsResponse)
                .build();
    }


    private AuthorResponse buildAuthorResponse(AuthorVO authorVO) {
        return AuthorResponse.builder()
                .userId(authorVO.getUserId())
                .nickname(authorVO.getNickname())
                .birth(authorVO.getBirth())
                .profileImageUrl(authorVO.getProfileImageUrl())
                .location(authorVO.getLocation())
                .build();
    }

    private List<QueryReplyDetailsResponse> buildReplyResponse(List<CommentDetailsVO> commentDetailsVOList) {
        List<QueryReplyDetailsResponse> queryReplyDetailsResponseList = new ArrayList<>();
        for (CommentDetailsVO commentDetailsVO : commentDetailsVOList) {
            queryReplyDetailsResponseList.add(
                    QueryReplyDetailsResponse.builder()
                            .author(buildAuthorResponse(commentDetailsVO.getAuthor()))
                            .commentId(commentDetailsVO.getCommentId())
                            .isFeedOwner(commentDetailsVO.getIsFeedOwner())
                            .isCommentOwner(commentDetailsVO.getIsCommentOwner())
                            .content(commentDetailsVO.getContent())
                            .createdAt(commentDetailsVO.getCreatedAt())
                            .build()
            );
        }
        return queryReplyDetailsResponseList;
    }
}
