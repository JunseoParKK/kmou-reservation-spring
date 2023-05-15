package capston.kmouReserveApp.domain.reservation.service;

import capston.kmouReserveApp.domain.reservation.dto.ReservationDetails;
import capston.kmouReserveApp.domain.reservation.dto.ReservationRequest;

import java.util.List;

public interface ReservationService {
    ReservationDetails register(Long roomId, ReservationRequest.RegisterReservation registerReservation);

    ReservationDetails getByRoomAndReservationToken(Long roomId, String reservationToken);

    List<ReservationDetails> getByRoomId(Long roomId);
}
