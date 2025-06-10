package finalmission.reservation;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationTimeRepository reservationTimeRepository;
    private final ReservationRepository reservationRepository;

    public List<MeetingRoomAvailableTimeResponse> getAvailableTimes(Long meetingRoomId,
        LocalDate date) {
        List<ReservationTime> reservationTimes = reservationTimeRepository.findAll();
        List<Reservation> reservations = reservationRepository.findByMeetingRoomIdAndDate(
            meetingRoomId, date);
        return reservationTimes.stream()
            .map(reservationTime ->
                new MeetingRoomAvailableTimeResponse(
                    reservationTime.getId(),
                    reservationTime.getStartAt(),
                    hasReservationInTime(reservationTime, reservations)
                )).toList();
    }

    private static boolean hasReservationInTime(ReservationTime reservationTime,
        List<Reservation> reservations) {
        return reservations.stream()
            .anyMatch(reservation ->
                reservation.isReservationTime(reservationTime));
    }
}
