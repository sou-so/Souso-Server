package kr.co.numble.numble.domain.user.service;

import kr.co.numble.numble.domain.user.domain.UserAuthCode;
import kr.co.numble.numble.domain.user.domain.repository.UserAuthCodeRepository;
import kr.co.numble.numble.domain.user.presentation.dto.request.UserAuthCodeRequest;
import kr.co.numble.numble.global.utils.code.RandomCodeUtil;
import kr.co.numble.numble.infrastructure.sms.coolsms.CoolSmsService;
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
