package tests.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.fakeapiuser.Address;
import models.fakeapiuser.Geolocation;
import models.fakeapiuser.Name;
import models.fakeapiuser.UserRoot;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;

@Tag("API")
public class ApiTests {

    @Test
    public void getAllUsersTest(){
        given().get("https://fakestoreapi.com/users")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    public void getSingleUserTest(){
        int userId = 7;
        given().pathParam("userId", userId)
                .get("https://fakestoreapi.com/users/{userId}")
                .then().log().all()
                .body("id", equalTo(userId))
                .body("address.zipcode", Matchers.matchesPattern("\\d{5}-\\d{4}"))
                .statusCode(200);
    }

    @Test
    public void getAllUserWithLimit(){

        int limitSize = 3;
        given().queryParam("limit", limitSize)
                .get("https://fakestoreapi.com/users")
                .then().log().all()
                .statusCode(200)
                .body("",hasSize(limitSize));
    }

    @Test
    public void getAllUsersSortByDesc(){
        String sortType = "desc";
        Response sortedResponse =  given().queryParam("sort", sortType)
                .get("https://fakestoreapi.com/users")
                .then().log().all()
                .extract().response();

        Response notSortedResponse = given()
                .get("https://fakestoreapi.com/users")
                .then().log().all().extract().response();


        List<Integer> sortedResponseIds = sortedResponse.jsonPath().getList("id");
        List<Integer> NotSortedResponseIds = notSortedResponse.jsonPath().getList("id");

        Assertions.assertNotEquals(sortedResponseIds,NotSortedResponseIds);
    }


    @Test
    public void addNewUserTest(){
        Name name = new Name("Alex", "Ecko");
        Geolocation geolocation = new Geolocation("40.12456","20.5419");
        Address addres = Address.builder()
                .city("Moscow")
                .number(100)
                .zipcode("54321-1234")
                .street("Prospekt Mira")
                .geolocation(geolocation).build();

        UserRoot bodyRequest = UserRoot.builder().name(name)
                .phone("79812223332")
                .email("fakeapi@gmail.com")
                .username("alexkepkin")
                .password("coolpassword")
                .address(addres).build();

        given().body(bodyRequest)
                .post("https://fakestoreapi.com/users")
                .then().log().all()
                .statusCode(200)
                .body("id",notNullValue());

    }

    @Test
    public void deleteUserTest(){
        given().delete("https://fakestoreapi.com/users/11")
                .then().log().all();
    }

    @Test
    public void authUserTest(){
        Map<String, String> userAuth = new HashMap<>();
        userAuth.put("username", "jimmie_k");
        userAuth.put("password", "klein*#%*");

        given().contentType(ContentType.JSON)
                .body(userAuth)
                .post("https://fakestoreapi.com/auth/login")
                .then().log().all()
                .statusCode(200)
                .body("token",notNullValue());
    }

    @Test
    public void authReqresUserTest(){
        Map<String, String> createUser = new HashMap<>();
        createUser.put("email", "eve.holt@reqres.in");
        createUser.put("password", "pistol");
        given().contentType(ContentType.JSON)
                .body(createUser)
                .post("https://reqres.in/api/register")
                .then().log().all()
                .statusCode(200)
                .body("id", notNullValue());
    }
    @Test
    public void unsuchReqresRegisterTest(){
        Map<String, String> createUser = new HashMap<>();
        createUser.put("email", "eve.holt@reqres.in");
        given().contentType(ContentType.JSON)
                .body(createUser)
                .post("https://reqres.in/api/register")
                .then().log().all()
                .statusCode(400);
    }

}
