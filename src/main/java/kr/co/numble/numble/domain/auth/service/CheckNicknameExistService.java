package kr.co.numble.numble.domain.auth.service;

import kr.co.numble.numble.domain.user.facade.UserFacade;
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
