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

}
