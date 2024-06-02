import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.example.entityfactory.UserFactory;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import org.junit.jupiter.api.Test;


public class AutoCrudTest {
    @BeforeEach
    public void setup(){
        RestAssured.baseURI = "https://qauto2.forstudy.space/api";
    }


    @Test
    public void CreateNewUser() {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(UserFactory.createUser())
                .log()
                .all()
                .when()
                .post("/auth/signup")
                .then()
                .log()
                .all()
                .statusCode(201)
                .extract()
                .response();

        Cookie sessionCookie = response.getDetailedCookie("sid");


        given()
                .header("Content-Type", "application/json")
                .body(UserFactory.updateUserProfile())
                .cookie(sessionCookie)
                .when()
                .put("/users/profile")
                .then()
                .log()
                .all()
                .statusCode(SC_OK);


        given()
                .cookie(sessionCookie)
                .when()
                .get("/users/profile")
                .then()
                .log()
                .all()
                .statusCode(SC_OK);

    }

}
