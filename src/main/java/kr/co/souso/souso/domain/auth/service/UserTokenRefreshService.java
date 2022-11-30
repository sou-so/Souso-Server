package kr.co.souso.souso.domain.auth.service;

import kr.co.souso.souso.domain.auth.domain.RefreshToken;
import kr.co.souso.souso.domain.auth.domain.repository.RefreshTokenRepository;
import kr.co.souso.souso.domain.auth.exception.RefreshTokenNotFoundException;
import kr.co.souso.souso.domain.auth.presentation.dto.response.UserTokenRefreshResponse;
import kr.co.souso.souso.global.property.jwt.JwtProperties;
import kr.co.souso.souso.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserTokenRefreshService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public UserTokenRefreshResponse execute(String refreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByToken(jwtTokenProvider.parseToken(refreshToken))
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);

        String newRefreshToken = jwtTokenProvider.generateRefreshToken(redisRefreshToken.getEmail());
        redisRefreshToken.updateToken(newRefreshToken, jwtProperties.getRefreshExp());

        String accessToken = jwtTokenProvider.generateAccessToken(redisRefreshToken.getEmail());
        return UserTokenRefreshResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtTokenProvider.getExpiredTime())
                .build();
    }
}
