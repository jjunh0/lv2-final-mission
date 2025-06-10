package finalmission.reservation;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservations")
    public ResponseEntity<List<MeetingRoomAvailableTimeResponse>> getAvailableTime(
        @RequestParam("meetingroom") Long meetingRoomId,
        @RequestParam("date") LocalDate date
    ) {
        List<MeetingRoomAvailableTimeResponse> responses = reservationService.getAvailableTimes(meetingRoomId, date);
        return ResponseEntity.ok().body(responses);
    }
}
