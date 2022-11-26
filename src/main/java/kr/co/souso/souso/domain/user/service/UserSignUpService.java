package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.auth.presentation.dto.response.UserTokenResponse;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.domain.repository.UserRepository;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.request.UserSignUpRequest;
import kr.co.souso.souso.global.enums.UserRole;
import kr.co.souso.souso.global.property.jwt.JwtProperties;
import kr.co.souso.souso.global.security.jwt.JwtTokenProvider;
import kr.co.souso.souso.infrastructure.image.DefaultImage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class UserSignUpService {

    private final UserFacade userFacade;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public UserTokenResponse execute(UserSignUpRequest request) {
        userFacade.checkUserExists(request.getEmail());

        User user = userRepository.save(User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .role(UserRole.USER)
                .nickname(request.getNickname())
                .profileImageUrl(DefaultImage.USER_PROFILE_IMAGE)
                .birth(request.getBirth())
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        return UserTokenResponse.builder()
                .accessToken(accessToken)
                .expiredAt(ZonedDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
                .refreshToken(refreshToken)
                .build();
    }
}
