package capston.kmouReserveApp.domain.user.mapper;

import capston.kmouReserveApp.domain.user.dto.UserInfo;
import capston.kmouReserveApp.domain.user.entity.User;

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
}
