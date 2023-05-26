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
        private int index;
        private boolean isPossible = true;

        @Builder
        public ReservationCheckResponse(int index, boolean isPossible) {
            this.index = index;
            this.isPossible = isPossible;
        }
    }
}
