package capston.kmouReserveApp.domain.reservation.service;

import capston.kmouReserveApp.domain.reservation.dto.ReservationDetails;
import capston.kmouReserveApp.domain.reservation.dto.ReservationRequest;

public interface ReservationService {
    ReservationDetails register(String uuid, Long roomId, ReservationRequest.RegisterReservation registerReservation);
}
