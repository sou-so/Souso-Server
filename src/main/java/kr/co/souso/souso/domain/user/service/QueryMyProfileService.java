package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.bookmark.domain.repository.FeedBookmarkRepository;
import kr.co.souso.souso.domain.comment.domain.repository.CommentRepository;
import kr.co.souso.souso.domain.meeting.domain.repository.MeetingRepository;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryMyProfileService {

    private final FeedBookmarkRepository feedBookmarkRepository;
    private final MeetingRepository meetingRepository;
    private final CommentRepository commentRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyProfileResponse execute() {
        User user = userFacade.getCurrentUser();

        return QueryMyProfileResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .birth(user.getBirth())
                .profileImageUrl(user.getProfileImageUrl())
                .location(user.getLocation())
                .feedCount(user.getFeedCount())
                .bookmarkCount(feedBookmarkRepository.countByUser(user))
                .meetingCount(meetingRepository.countByUser(user))
                .commentCount(commentRepository.countByUser(user))
                .build();
    }
}
