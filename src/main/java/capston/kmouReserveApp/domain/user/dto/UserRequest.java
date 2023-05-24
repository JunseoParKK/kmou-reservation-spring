package capston.kmouReserveApp.domain.user.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class UserRequest {

    @Getter
    @ToString
    @NoArgsConstructor
    public static class UuidRequest{
        private String uuid;

        public UuidRequest(String uuid) {
            this.uuid = uuid;
        }
    }

    @Getter
    @ToString
    @NoArgsConstructor
    public static class UpdateRequest{
        private String uuid;
        private String email;
        private String name;
        private String phoneNum;

        public UpdateRequest(String uuid, String email, String name, String phoneNum) {
            this.uuid = uuid;
            this.email = email;
            this.name = name;
            this.phoneNum = phoneNum;
        }
    }

}
