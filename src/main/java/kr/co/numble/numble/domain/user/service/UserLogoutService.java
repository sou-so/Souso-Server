package kr.co.numble.numble.domain.user.service;

import kr.co.numble.numble.domain.auth.domain.RefreshToken;
import kr.co.numble.numble.domain.auth.domain.repository.RefreshTokenRepository;
import kr.co.numble.numble.domain.auth.exception.RefreshTokenNotFoundException;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserLogoutService {

    private final UserFacade userFacade;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void execute() {
        User user = userFacade.getCurrentUser();

        RefreshToken refreshToken = refreshTokenRepository.findById(user.getEmail())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        refreshTokenRepository.delete(refreshToken);
    }
}
