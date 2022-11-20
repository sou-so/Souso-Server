package kr.co.numble.numble.domain.user.domain.repository;

import kr.co.numble.numble.domain.user.domain.UserAuthCode;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthCodeRepository extends CrudRepository<UserAuthCode, String> {

}
