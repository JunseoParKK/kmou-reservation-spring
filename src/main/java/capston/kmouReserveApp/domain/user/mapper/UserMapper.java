package capston.kmouReserveApp.domain.user.mapper;

import capston.kmouReserveApp.domain.user.dto.SignUpResponse;
import capston.kmouReserveApp.domain.user.dto.UserInfo;
import capston.kmouReserveApp.domain.user.entity.User;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public static UserInfo mapToDto(User savedUser){
        return UserInfo.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .phoneNum(savedUser.getPhoneNum())
                .uuid(savedUser.getUuid())
                .build();
    }

    public static SignUpResponse mapToDto(UserInfo userInfo){
        return SignUpResponse.builder()
                .id(userInfo.getId())
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .password(userInfo.getPassword())
                .phoneNum(userInfo.getPhoneNum())
                .uuid(userInfo.getUuid())
                .build();
    }
}
