package kr.co.souso.souso.domain.auth.service;

import kr.co.souso.souso.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckNicknameExistService {

    private final UserFacade userFacade;

    public void execute(String nickname) {
        userFacade.checkNicknameExists(nickname);
    }

}
