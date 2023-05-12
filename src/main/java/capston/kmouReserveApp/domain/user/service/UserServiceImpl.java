package capston.kmouReserveApp.domain.user.service;

import capston.kmouReserveApp.domain.user.dto.SignUpRequest;
import capston.kmouReserveApp.domain.user.dto.UserInfo;
import capston.kmouReserveApp.domain.user.entity.User;
import capston.kmouReserveApp.domain.user.mapper.UserMapper;
import capston.kmouReserveApp.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserInfo create(SignUpRequest signUpRequest) {
        User savedUser = userRepository.save(User.of(signUpRequest));
        log.info("create savedUser: {}",savedUser);

        return userMapper.mapToDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo getUser(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException());
        log.info("getById findUser: {}",findUser);

        return userMapper.mapToDto(findUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfo getByUuid(String uuid) {
        User findUser = userRepository.findByUuid(uuid)
                .orElseThrow(()->new EntityNotFoundException());
        log.info("getByUuid findUser: {}",findUser);

        return userMapper.mapToDto(findUser);
    }
}
