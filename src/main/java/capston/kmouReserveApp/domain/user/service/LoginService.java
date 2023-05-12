package capston.kmouReserveApp.domain.user.service;

import capston.kmouReserveApp.domain.user.dto.SignUpRequest;
import capston.kmouReserveApp.domain.user.dto.SignUpResponse;

public interface LoginService {
    SignUpResponse signUp(SignUpRequest signUpRequest);
}
