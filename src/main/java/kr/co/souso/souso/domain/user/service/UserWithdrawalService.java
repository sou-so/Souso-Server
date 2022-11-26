package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.domain.repository.UserRepository;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserWithdrawalService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        userRepository.delete(user);
    }
}
