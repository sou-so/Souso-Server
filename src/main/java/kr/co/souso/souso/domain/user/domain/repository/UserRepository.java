package kr.co.souso.souso.domain.user.domain.repository;

import kr.co.souso.souso.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByPhoneNumber(String phoneNumber);

}
