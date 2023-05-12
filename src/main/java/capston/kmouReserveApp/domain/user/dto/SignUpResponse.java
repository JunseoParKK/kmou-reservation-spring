package capston.kmouReserveApp.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNum;
    private String uuid;

    @Builder
    public SignUpResponse(Long id, String name, String email, String password, String phoneNum, String uuid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
        this.uuid = uuid;
    }
}
