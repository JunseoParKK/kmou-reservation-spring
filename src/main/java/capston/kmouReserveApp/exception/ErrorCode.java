package capston.kmouReserveApp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_FOUND_ENTITY("존재하지 않는 엔티티입니다."),
    DUPLICATED_ENTITY("이미 존재하는 엔티티입니다."),
    INVALID_REQUEST("올바른 요청 값이 아닙니다.."),
    NOT_FOUND_USER("존재하지 않는 사용자입니다.");

    private final String errorMsg;
}
