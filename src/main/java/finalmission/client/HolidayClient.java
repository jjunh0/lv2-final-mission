package finalmission.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HolidayClient {

    private final RestClient holidayRestClient;
    private final String apiKey;

    public HolidayClient(
        @Qualifier("holidayRestClient") RestClient holidayRestClient,
        @Value("${holiday.key}") String apiKey
    ) {
        this.holidayRestClient = holidayRestClient;
        this.apiKey = apiKey;
    }

    public boolean isHoliday(int year, int month, int date) {
        ResponseEntity<String> entity = holidayRestClient.get()
            .uri(
                uriBuilder -> uriBuilder.queryParam("serviceKey", apiKey)
                    .queryParam("solYear", year)
                    .queryParam("solMonth", month)
                    .queryParam("_type", "json")
                    .build()
            ).retrieve().toEntity(String.class);
        return false;
    }
}
