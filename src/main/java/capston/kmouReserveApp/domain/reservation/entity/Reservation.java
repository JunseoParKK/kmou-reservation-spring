package capston.kmouReserveApp.domain.reservation.entity;

import capston.kmouReserveApp.domain.BaseTimeEntity;
import capston.kmouReserveApp.domain.user.entity.User;
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
    public Reservation(String reservationToken, LocalDateTime startTime, LocalDateTime endTime, User user, Room room, String purpose, int memberNum) {
        this.reservationToken = reservationToken;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.room = room;
        this.purpose = purpose;
        this.memberNum = memberNum;
    }
}
