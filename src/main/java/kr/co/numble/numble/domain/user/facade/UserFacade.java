package kr.co.numble.numble.domain.user.facade;

import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.domain.repository.UserRepository;
import kr.co.numble.numble.domain.user.exception.AlreadyNicknameExistException;
import kr.co.numble.numble.domain.user.exception.AlreadyUserExistException;
import kr.co.numble.numble.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void checkUserExists(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw AlreadyUserExistException.EXCEPTION;
        }
    }

    public void checkNicknameExists(String nickname) {
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw AlreadyNicknameExistException.EXCEPTION;
        }
    }
}
