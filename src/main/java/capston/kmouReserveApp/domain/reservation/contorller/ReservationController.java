package capston.kmouReserveApp.domain.reservation.contorller;

import capston.kmouReserveApp.auth.AuthUser;
import capston.kmouReserveApp.domain.reservation.dto.ReservationDetails;
import capston.kmouReserveApp.domain.reservation.dto.ReservationRequest;
import capston.kmouReserveApp.domain.reservation.service.ReservationService;
import capston.kmouReserveApp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService userService;

    /*
     * 예약 등록
     */
    @PostMapping("room/{roomId}/reservation")
    public ResponseEntity<ReservationDetails>register(
            @PathVariable("roomId") Long roomId,
            @RequestBody ReservationRequest.RegisterReservation registerReservation
            )throws Exception{
        String uuid = registerReservation.getUuid();
        log.info("reservation register, roomId: {}, request: {}",
                roomId, registerReservation);
        log.info("reservation register, uuid: {}",uuid);
        registerReservation.setUuid(uuid);

        return new ResponseEntity<>(reservationService.register(
                roomId,registerReservation), HttpStatus.CREATED
        );
    }

    /*
     * 등록한 예약 상세 보기
     */
    @GetMapping("/room/{roomId}/reservation/{reservationToken}")
    public ResponseEntity<ReservationDetails>getReservation(
            @PathVariable("roomId") Long roomId,
            @PathVariable("reservationToken") String reservationToken
    ){
        log.info("reservation getById, roomId: {}, reservationToken: {}",roomId,reservationToken);
        return ResponseEntity.ok(reservationService.getByRoomAndReservationToken(roomId,reservationToken));
    }
}
