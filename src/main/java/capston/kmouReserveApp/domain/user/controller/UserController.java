package capston.kmouReserveApp.domain.user.controller;

import capston.kmouReserveApp.domain.user.dto.SignUpRequest;
import capston.kmouReserveApp.domain.user.dto.SignUpResponse;
import capston.kmouReserveApp.domain.user.dto.UserInfo;
import capston.kmouReserveApp.domain.user.dto.UserRequest;
import capston.kmouReserveApp.domain.user.service.LoginService;
import capston.kmouReserveApp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @Valid @RequestBody SignUpRequest signUpRequest
            ){
        log.info("users signup, signupDto: {}",signUpRequest);
        SignUpResponse signUpResponse = loginService.signUp(signUpRequest);

        return new ResponseEntity<>(signUpResponse, HttpStatus.CREATED);
    }

    // 사용자 정보 조회
    @GetMapping("")
    public ResponseEntity<?> getUser(
            @RequestBody UserRequest.UuidRequest uuidRequest
            ){
        String uuid = uuidRequest.getUuid();
        UserInfo userInfo = userService.getByUuid(uuid);
        log.info("userInfo: {}",userInfo);

        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @PutMapping("/update")
    public void updateUser(
        @RequestBody UserRequest.UpdateRequest updateRequest
    ){
        String uuid = updateRequest.getUuid();
        userService.updateUser(updateRequest);
    }
}
