package finalmission.reservation;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByMeetingRoomIdAndDate(Long meetingRoomId, LocalDate date);

    boolean existsByMeetingRoomIdAndDateAndTimeId(Long meetingRoomId, LocalDate date, Long timeId);
}
