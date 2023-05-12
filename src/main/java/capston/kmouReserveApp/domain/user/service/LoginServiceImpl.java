package capston.kmouReserveApp.domain.user.service;

import capston.kmouReserveApp.domain.user.dto.SignUpRequest;
import capston.kmouReserveApp.domain.user.dto.SignUpResponse;
import capston.kmouReserveApp.domain.user.dto.UserInfo;
import capston.kmouReserveApp.domain.user.mapper.UserMapper;
import capston.kmouReserveApp.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        UserInfo userInfo = userService.create(signUpRequest);
        log.info("signUp userInfo: {}",userInfo);

        return userMapper.mapToDto(userInfo);
    }
}
