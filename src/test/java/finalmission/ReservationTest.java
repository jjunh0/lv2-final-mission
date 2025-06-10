package finalmission;

import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReservationTest {

    @Test
    @DisplayName("예약 가능 시간을 조회한다")
    void getAvailableTime() {
        RestAssured.given()
            .when().get("/reservations?meetingroom=1&date=2025-06-10")
            .then()
            .statusCode(200)
            .body("size()", is(9));
    }

}
