package capston.kmouReserveApp.domain.reservation.dto;

import capston.kmouReserveApp.domain.reservation.entity.Reservation;
import capston.kmouReserveApp.domain.reservation.entity.Room;
import capston.kmouReserveApp.domain.user.entity.User;
import capston.kmouReserveApp.utils.TimeParsingUtils;
import lombok.*;

public class ReservationRequest {

    @Getter @Setter
    @ToString
    @NoArgsConstructor
    public static class RegisterReservation{
        private String uuid;
        private String startTime;
        private String endTime;
        private String purpose;
        private int memberNum;

        @Builder
        public RegisterReservation(String uuid, String startTime, String endTime, String purpose, int memberNum) {
            this.uuid = uuid;
            this.startTime = startTime;
            this.endTime = endTime;
            this.purpose = purpose;
            this.memberNum = memberNum;
        }

        public Reservation mapToEntity(User user, Room room){
            return Reservation.builder()
                    .startTime(TimeParsingUtils.formatterLocalDateTime(startTime))
                    .endTime(TimeParsingUtils.formatterLocalDateTime(endTime))
                    .user(user)
                    .room(room)
                    .purpose(purpose)
                    .memberNum(memberNum)
                    .build();
        }
    }

   @Getter @Setter
   @ToString
   @NoArgsConstructor
   public static class UpdateReservation{
        private String uuid;
        private String startTime;
        private String endTime;
        private String purpose;
        private int memberNUm;

        @Builder
       public UpdateReservation(String uuid, String startTime, String endTime, String purpose, int memberNUm) {
           this.uuid = uuid;
           this.startTime = startTime;
           this.endTime = endTime;
           this.purpose = purpose;
           this.memberNUm = memberNUm;
       }
   }
}
