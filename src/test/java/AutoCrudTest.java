import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import net.bytebuddy.build.Plugin;
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
                .body(UserFactory.loginUser())
                .when()
                .post("/auth/signin")
                .then()
                .log()
                .all()
                .statusCode(SC_OK);


        given()
                .when()
                .get("/users/profile")
                .then()
                .log()
                .all()
                .statusCode(SC_OK);


        given()
                .header("Content-Type", "application/json")
                .body(UserFactory.updateUserProfile())
                .when()
                .post("/users/profile")
                .then()
                .log()
                .all()
                .statusCode(SC_OK);


    }



//    @Test
//    public void Login() {
//        given()
//                .header("Content-Type", "application/json")
//                .body(UserFactory.loginUser())
//                .when()
//                .post("/auth/signin")
//                .then()
//                .log()
//                .all()
//                .statusCode(SC_OK);
//    }


//    @Test
//    public void GetProfileData() {
//
//        given()
//                .when()
//                .get("/users/profile")
//                .then()
//                .log()
//                .all()
//                .statusCode(SC_OK);
//    }


//    @Test
//    public void UpdateProfileInfo() {
//        given()
//                .header("Content-Type", "application/json")
//                .body(UserFactory.updateUserProfile())
//                .when()
//                .post("/users/profile")
//                .then()
//                .log()
//                .all()
//                .statusCode(SC_OK);
//
//        }

    }
