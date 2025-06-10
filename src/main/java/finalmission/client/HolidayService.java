package finalmission.client;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HolidayService {

    private final HolidayClient holidayClient;


    public boolean isHoliday(LocalDate date) {
        return holidayClient.isHoliday(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());

    }
}
