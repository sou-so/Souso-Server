package kr.co.numble.numble.domain.user.service;

import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.domain.repository.UserRepository;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserWithdrawalService {

    private UserFacade userFacade;
    private UserRepository userRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        userRepository.delete(user);

    }
}
