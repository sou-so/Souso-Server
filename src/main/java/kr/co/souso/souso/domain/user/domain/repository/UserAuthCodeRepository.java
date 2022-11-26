package kr.co.souso.souso.domain.user.domain.repository;

import kr.co.souso.souso.domain.user.domain.UserAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthCodeRepository extends JpaRepository<UserAuthCode, String> {

}
