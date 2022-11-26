package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.domain.repository.UserRepository;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QueryMyProfileService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;

    public QueryMyProfileResponse execute() {
        User user = userFacade.getCurrentUser();

        return QueryMyProfileResponse.builder()
                .nickname(user.getNickname())
                .birth(user.getBirth())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
