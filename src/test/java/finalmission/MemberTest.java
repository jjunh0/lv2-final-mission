package finalmission;

import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MemberTest {

    @Test
    @DisplayName("내 예약 목록을 확인할 수 있다")
    void getMyReservations() {
        RestAssured.given()
            .cookies("token", TestFixture.login())
            .when()
            .get("/member/reservations")
            .then().log().all()
            .statusCode(200)
            .body("size()", is(3));
    }
}
