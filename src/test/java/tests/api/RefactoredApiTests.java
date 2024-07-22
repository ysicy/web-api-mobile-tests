package tests.api;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import io.restassured.http.ContentType;
import models.fakeapiuser.Address;
import models.fakeapiuser.Geolocation;
import models.fakeapiuser.Name;
import models.fakeapiuser.UserRoot;

import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@Tag("api")
public class RefactoredApiTests {

    @BeforeAll
    public static void SetUp(){
        RestAssured.baseURI = "https://fakestoreapi.com";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    }

    @Test
    public void getAllUsersTest(){
        given().get("/users")
                .then().
                statusCode(200);
    }

    @Test
    public void getSingleUserTest(){
        int userId = 7;
        UserRoot response =  given()
                .pathParam("userId", userId)
                .get("/users/{userId}")
                .then()
                .statusCode(200)
                .extract().as(UserRoot.class);

        Assertions.assertEquals(userId, response.getId());
        Assertions.assertTrue(response.getAddress().getZipcode().matches("\\d{5}-\\d{4}"));

    }

    @Test
    public void getAllUserWithLimit(){

        int limitSize = 3;
        List<UserRoot> users =  given()
                .queryParam("limit", limitSize)
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<UserRoot>>(){});

        Assertions.assertEquals(3, users.size());
    }

    @Test
    public void getAllUsersSortByDesc(){
        String sortType = "desc";
        List<UserRoot> userSorted =  given().queryParam("sort", sortType)
                .get("/users")
                .then()
                .extract().as(new TypeRef<List<UserRoot>>(){});

        List<UserRoot> userNotSorted = given()
                .get("/users")
                .then()
                .extract().as(new TypeRef<List<UserRoot>>(){});


        List<Integer> sortedResponseIds = userSorted
                .stream()
                .map(UserRoot::getId)
                .collect(Collectors.toList());

        List<Integer> sortedByCode = userNotSorted
                .stream()
                .map(UserRoot::getId)
                .sorted(Comparator.reverseOrder()).toList();

        Assertions.assertNotEquals(userSorted,userNotSorted);
        Assertions.assertEquals(sortedByCode,sortedResponseIds);
    }

    @Test
    public void addNewUserTest() {
        UserRoot user = getTestUser();

        Integer userId = given().body(user)
                .post("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("id");
        Assertions.assertNotNull(userId);
    }
    @Disabled
    @Test
    public void updateUserTest(){
        UserRoot user = getTestUser();
        String oldPassword = user.getPassword();
        user.setPassword("passik123");

        UserRoot updateUser = given()
                .body(user)
                .put("/users/" + user.getId())
                .then().extract().as(UserRoot.class);
        Assertions.assertNotEquals(updateUser.getPassword(),oldPassword);
    }
    private UserRoot getTestUser(){

            Random random = new Random();
            Name name = new Name("Alex", "Ecko");
            Geolocation geolocation = new Geolocation("40.12456","20.5419");

            Address address = Address.builder()
                    .city("Moscow")
                    .number(random.nextInt(100))
                    .zipcode("54321-1234")
                    .street("Prospekt Mira")
                    .geolocation(geolocation).build();

            return UserRoot.builder().name(name)
                    .phone("79812223332")
                    .email("fakeapi@gmail.com")
                    .username("alexkepkin")
                    .password("coolpassword")
                    .address(address).build();
        }
    @Test
    public void deleteUserTest(){
        given().delete("/users/11")
                .then().log().all();
    }
    @Test
    public void authUserTest(){
        Map<String, String> userAuth = new HashMap<>();
        userAuth.put("username", "jimmie_k");
        userAuth.put("password", "klein*#%*");

        given().contentType(ContentType.JSON)
                .body(userAuth)
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("token",notNullValue());
    }
    @Tag("api")
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
    @Tag("api")
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


