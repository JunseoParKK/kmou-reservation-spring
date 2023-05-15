package capston.kmouReserveApp.domain.reservation.dto;

import capston.kmouReserveApp.domain.reservation.entity.Reservation;
import capston.kmouReserveApp.utils.TimeParsingUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationDetails {
    private String reservationToken;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    private String uuid;
    private String userEmail;

    private Long roomId;
    private String roomName;

    private String purpose;
    private int memberNum;

    @Builder
    public ReservationDetails(String reservationToken, String startTime, String endTime, String uuid, String userEmail, Long roomId, String roomName, String purpose, int memberNum) {
        this.reservationToken = reservationToken;
        this.startTime = startTime;
        this.endTime = endTime;
        this.uuid = uuid;
        this.userEmail = userEmail;
        this.roomId = roomId;
        this.roomName = roomName;
        this.purpose = purpose;
        this.memberNum = memberNum;
    }

    public static ReservationDetails mapToDto(Reservation reservation){
        return ReservationDetails.builder()
                .reservationToken(reservation.getReservationToken())
                .startTime(TimeParsingUtils.formatterString(reservation.getStartTime()))
                .endTime(TimeParsingUtils.formatterString((reservation.getEndTime())))
                .uuid(reservation.getUser().getUuid())
                .userEmail(reservation.getUser().getEmail())
                .roomId(reservation.getRoom().getId())
                .roomName(reservation.getRoom().getName())
                .purpose(reservation.getPurpose())
                .memberNum(reservation.getMemberNum())
                .build();
    }
}
