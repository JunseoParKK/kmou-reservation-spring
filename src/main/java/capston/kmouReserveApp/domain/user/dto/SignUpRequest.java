package capston.kmouReserveApp.domain.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String phoneNum;

    public SignUpRequest(String name, String email, String password, String phoneNum) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNum = phoneNum;
    }
}
