package finalmission.meetingroom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    @PostMapping("/meetingrooms")
    public ResponseEntity<MeetingRoomResponse> create() {
        MeetingRoomResponse response = meetingRoomService.create();
        return ResponseEntity.ok().body(response);
    }
}
