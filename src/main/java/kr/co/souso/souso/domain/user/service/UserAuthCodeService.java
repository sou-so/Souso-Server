package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.user.domain.UserAuthCode;
import kr.co.souso.souso.domain.user.domain.repository.UserAuthCodeRepository;
import kr.co.souso.souso.domain.user.presentation.dto.request.UserAuthCodeRequest;
import kr.co.souso.souso.global.utils.code.RandomCodeUtil;
import kr.co.souso.souso.infrastructure.sms.coolsms.CoolSmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@EnableAsync
public class UserAuthCodeService {

    private final CoolSmsService coolSmsService;
    private final UserAuthCodeRepository userAuthCodeRepository;
    private final PasswordEncoder passwordEncoder;

    @Async
    @Transactional
    public void execute(UserAuthCodeRequest request) {
        String phoneNumber = request.getPhoneNumber();
        String code = RandomCodeUtil.generateRandomCode(4);

        coolSmsService.sendCode(phoneNumber, code);

        userAuthCodeRepository.save(UserAuthCode.builder()
                .phoneNumber(phoneNumber)
                .code(passwordEncoder.encode(code))
                .build());
    }
}
