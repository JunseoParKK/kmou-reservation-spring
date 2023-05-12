package capston.kmouReserveApp.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String uuid;
    private String name;
    private String phoneNum;

    private String email;
    private String password;

    @Builder
    public UserInfo(Long id,String uuid, String name, String phoneNum, String email, String password) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
    }
}
