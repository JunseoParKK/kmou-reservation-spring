package capston.kmouReserveApp.domain.reservation.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class ReservationCheck {
    @Getter @Setter
    @NoArgsConstructor
    @ToString
    public static class ReservationCheckRequest {
        private String date;

        @Builder
        public ReservationCheckRequest(String date) {
            this.date = date;
        }
    }

    @Getter @Setter
    @NoArgsConstructor
    @ToString
    public static class ReservationCheckResponse{
        private String time;
        private boolean isReserved = false;

        @Builder
        public ReservationCheckResponse(String time, boolean isReserved) {
            this.time = time;
            this.isReserved = isReserved;
        }
    }
}
