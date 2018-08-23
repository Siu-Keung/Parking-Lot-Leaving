package com.oocl.parkingLotLeaving.api;

import com.oocl.parkingLotLeaving.entity.Leaving;
import com.oocl.parkingLotLeaving.entity.LeavingRequestStatus;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * @author Dylan Wei
 * @date 2018-08-23 18:22
 */
public class AbsenceApiTest {
    private static RequestSpecification requestSpec;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @BeforeAll
    public static void createRequestSpecification() {
        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://127.0.0.1").
                setPort(1234).
                setBasePath("/absence").
                setContentType(ContentType.JSON).
                build();
    }

    @Test
    public void should_return_list_given_find_all_leaving_request(){
        given()
                .spec(requestSpec)
                .when().get()
                .then().statusCode(HttpStatus.SC_OK)
                .and()
                .body("datas.size", is(3))
                .body("datas.collect {it.id}", hasItems(1, 2, 3));

    }

    @Test
    public void should_get_204_given_valid_leaving_request() throws ParseException {
        Leaving leaving = new Leaving();
        leaving.setStartDate(format.parse("2018-08-24 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");
        given()
                .spec(requestSpec)
                .body(leaving).log().all(true)
                .when().post()
                .then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void should_get_403_given_invalid_leaving_request() throws ParseException {
        Leaving leaving = new Leaving();
        leaving.setStartDate(format.parse("2018-08-28 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");
        given()
                .spec(requestSpec)
                .body(leaving).log().all(true)
                .when().post()
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void should_get_leaving_request_details_given_valid_id(){
        Leaving leaving = with().spec(requestSpec).get("/1").as(Leaving.class);

        assertThat(leaving.getId(), is(1L));
        assertThat(leaving.getReason(), is("生病了"));
        assertThat(leaving.getStatus(), is(LeavingRequestStatus.PENDING));
    }

    @Test
    public void should_get_404_given_invalid_id(){
        given().spec(requestSpec)
                .when().get("/9999999999")
                .then().statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .body(is("资源不存在"));
    }

}
