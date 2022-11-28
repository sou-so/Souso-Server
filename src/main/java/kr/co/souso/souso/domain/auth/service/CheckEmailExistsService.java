package kr.co.souso.souso.domain.auth.service;

import kr.co.souso.souso.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckEmailExistsService {

    private final UserFacade userFacade;

    public void execute(String email) {
        userFacade.checkEmailExists(email);
    }
}
