package kr.co.numble.numble.domain.auth.service;

import kr.co.numble.numble.domain.auth.presentation.dto.request.CheckAuthCodeRequest;
import kr.co.numble.numble.domain.user.domain.UserAuthCode;
import kr.co.numble.numble.domain.user.domain.repository.UserAuthCodeRepository;
import kr.co.numble.numble.domain.user.exception.InvalidCodeException;
import kr.co.numble.numble.domain.user.exception.UserAuthCodeNotFoundException;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckAuthCodeExistsService {

    private final UserAuthCodeRepository userAuthCodeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserFacade userFacade;


    public void execute(CheckAuthCodeRequest request) {

        UserAuthCode authCode = userAuthCodeRepository.findById(request.getPhoneNumber())
                .orElseThrow(() -> UserAuthCodeNotFoundException.EXCEPTION);

        userFacade.checkUserPhoneNumber(request.getPhoneNumber());

        if (!passwordEncoder.matches(request.getAuthCode(), authCode.getCode())) {
            throw InvalidCodeException.EXCEPTION;
        }
    }
}
