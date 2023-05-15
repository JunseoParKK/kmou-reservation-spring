package capston.kmouReserveApp.domain.reservation.entity;

import antlr.Token;
import capston.kmouReserveApp.domain.BaseTimeEntity;
import capston.kmouReserveApp.domain.reservation.dto.ReservationRequest;
import capston.kmouReserveApp.domain.user.entity.User;
import capston.kmouReserveApp.utils.TimeParsingUtils;
import capston.kmouReserveApp.utils.TokenGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation")
public class Reservation extends BaseTimeEntity {

    private static final String RESERVATION_PREFIX = "reserv_";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "reservation_token",unique = true)
    private String reservationToken;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private String purpose;

    @Column(name = "member_num")
    private int memberNum;

    @Builder
    public Reservation(LocalDateTime startTime, LocalDateTime endTime, User user, Room room, String purpose, int memberNum) {
        this.reservationToken = TokenGenerator.randomCharacterWithPrefix(RESERVATION_PREFIX);
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.room = room;
        this.purpose = purpose;
        this.memberNum = memberNum;
    }

    public void update(ReservationRequest.UpdateReservation updateReservation) {
        this.startTime= TimeParsingUtils.formatterLocalDateTime(updateReservation.getStartTime());
        this.endTime=TimeParsingUtils.formatterLocalDateTime(updateReservation.getEndTime());
        this.purpose= updateReservation.getPurpose();
        this.memberNum= updateReservation.getMemberNum();
    }
}
