package kr.co.souso.souso.global.security.auth;

import kr.co.souso.souso.domain.user.domain.repository.UserRepository;
import kr.co.souso.souso.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public AuthDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(AuthDetails::new)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
