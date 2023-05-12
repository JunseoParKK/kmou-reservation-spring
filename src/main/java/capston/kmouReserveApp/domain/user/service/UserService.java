package capston.kmouReserveApp.domain.user.service;

import capston.kmouReserveApp.domain.user.dto.SignUpRequest;
import capston.kmouReserveApp.domain.user.dto.UserInfo;

public interface UserService {
    UserInfo create(SignUpRequest signUpRequest);
    UserInfo getUser(Long userId);
    UserInfo getByUuid(String uuid);
}
