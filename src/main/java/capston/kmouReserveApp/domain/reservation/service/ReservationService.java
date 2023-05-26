package capston.kmouReserveApp.domain.reservation.service;

import capston.kmouReserveApp.domain.reservation.dto.ReservationCheck;
import capston.kmouReserveApp.domain.reservation.dto.ReservationDetails;
import capston.kmouReserveApp.domain.reservation.dto.ReservationRequest;

import java.util.List;

public interface ReservationService {
    ReservationDetails register(Long roomId, ReservationRequest.RegisterReservation registerReservation);

    ReservationDetails getByRoomAndReservationToken(Long roomId, String reservationToken);

    List<ReservationDetails> getByRoomId(Long roomId);

    List<ReservationDetails> getAllByUserUuid(String uuid);

    String updateByToken(Long roomId, String reservationToken, ReservationRequest.UpdateReservation updateReservation);

    void deleteByToken(String uuid, String reservationToken);

    List<ReservationCheck.ReservationCheckResponse> getByRoomAndDate(Long roomId, String date);
}
