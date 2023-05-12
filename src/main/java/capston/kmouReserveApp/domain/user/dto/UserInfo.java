package capston.kmouReserveApp.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserInfo {
    private UUID id;
    private String name;
    private String phoneNum;

    private String email;
    private String password;

    @Builder
    public UserInfo(UUID id, String name, String phoneNum, String email, String password) {
        this.id = id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.password = password;
    }
}
