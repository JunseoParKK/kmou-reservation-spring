package capston.kmouReserveApp.domain.user.service;

import capston.kmouReserveApp.domain.user.dto.SignUpRequest;
import capston.kmouReserveApp.domain.user.dto.UserInfo;
import capston.kmouReserveApp.domain.user.dto.UserRequest;

public interface UserService {
    UserInfo create(SignUpRequest signUpRequest);
    UserInfo getUser(Long userId);
    UserInfo getByUuid(String uuid);
    void updateUser(UserRequest.UpdateRequest updateRequest);
}
