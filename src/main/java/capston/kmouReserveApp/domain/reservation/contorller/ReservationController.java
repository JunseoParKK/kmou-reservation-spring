package capston.kmouReserveApp.domain.reservation.contorller;

import capston.kmouReserveApp.auth.AuthUser;
import capston.kmouReserveApp.domain.reservation.dto.ReservationDetails;
import capston.kmouReserveApp.domain.reservation.dto.ReservationRequest;
import capston.kmouReserveApp.domain.reservation.service.ReservationService;
import capston.kmouReserveApp.domain.user.dto.UserRequest;
import capston.kmouReserveApp.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /*
     * 해당 룸 예약 조회
     */
    @GetMapping("/room/{roomId}/reservation")
    public ResponseEntity<List<ReservationDetails>>getByRoomId(
            @PathVariable("roomId") Long roomId
    ){
        log.info("reservation getByRoomId, roomId: {}",roomId);
        return ResponseEntity.ok(reservationService.getByRoomId(roomId));
    }

    /*
     * 해당 유저 예약 조회
     */
    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDetails>>getAllByUserUuid(
            @RequestBody UserRequest.UuidRequest userUuidRequest
            )throws Exception{
        String uuid = userUuidRequest.getUuid();
        log.info("reservation register, uuid: {}",uuid);
        return ResponseEntity.ok(reservationService.getAllByUserUuid(uuid));
    }

    /*
     * 예약 수정
     */
    @PutMapping("/room/{roomId}/reservation/{reservationToken}")
    public String updateReservation(
            @PathVariable("roomId") Long roomId,
            @PathVariable("reservationToken") String reservationToken,
            @RequestBody ReservationRequest.UpdateReservation updateReservation
    ){
        String uuid = updateReservation.getUuid();
        log.info("reservation updateByUuid, roomId: {}, reservationToken: {}",roomId,reservationToken);
        return reservationService.updateByToken(roomId,reservationToken,updateReservation);
    }

    /*
     * 예약 삭제
     */
    @DeleteMapping("/reservation/{reservationToken}")
    public ResponseEntity<String>deleteReservation(
            @PathVariable("reservationToken") String reservationToken,
            @RequestBody UserRequest.UuidRequest userRequest
    ){
        String uuid = userRequest.getUuid();
        log.info("reservation deleteById, reservationToken: {}",reservationToken);

        reservationService.deleteByToken(uuid,reservationToken);
        return new ResponseEntity<>("a reservation successfully deleted!",HttpStatus.OK);
    }
}
