import io.restassured.RestAssured;
import org.json.JSONObject;
import org.junit.After;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;


public class ShopTest {

    @BeforeMethod
    public void postBeforeTest() {
        JSONObject body = new JSONObject();
        body.put("id", 11);
        body.put("name", "Kisa");
        List<String> photoUrls = List.of("https://cdn-irec.r-99.com/sites/default/files/imagecache/300o/product-images/36512/8863363.jpg");
        body.put("photoUrls", photoUrls);
        RestAssured.given().header("Content-Type", "application/json").body(body.toString())
                .post("https://petstore.swagger.io/v2/pet/").then().assertThat().statusCode(200);
    }

    @Test
    public void postTest() {
        JSONObject body = new JSONObject();
        body.put("id", 11);
        body.put("name", "Kisa");
        List<String> photoUrls = List.of("https://cdn-irec.r-99.com/sites/default/files/imagecache/300o/product-images/36512/8863363.jpg");
        body.put("photoUrls", photoUrls);

        RestAssured.given().header("Content-Type", "application/json").body(body.toString())
                .post("https://petstore.swagger.io/v2/pet/").then().assertThat()
                .body(
                        "id", equalTo(11),
                        "name", equalTo("Kisa"),
                        "photoUrls", equalTo(photoUrls));
    }

    @Test
    public void putTest() {
        JSONObject body = new JSONObject();
        body.put("id", 11);
        body.put("name", "cat");
        List<String> photoUrls = List.of("https://cdn-irec.r-99.com/sites/default/files/imagecache/300o/product-images/36512/8863363.jpg");
        body.put("photoUrls", photoUrls);

        RestAssured.given().header("Content-Type", "application/json").body(body.toString())
                .put("https://petstore.swagger.io/v2/pet/").then().assertThat().statusCode(200).body(
                        "id", equalTo(11),
                        "name", equalTo("cat"),
                        "photoUrls", equalTo(photoUrls));

    }

    @Test
    public void GetTest() {
        List<String> photoUrls = List.of("https://cdn-irec.r-99.com/sites/default/files/imagecache/300o/product-images/36512/8863363.jpg");

        RestAssured.
                when().get(" https://petstore.swagger.io/v2/pet/11").
                then().assertThat().statusCode(200).
                body("id", equalTo(11),
                        "name", equalTo("Kisa"),
                        "photoUrls", equalTo(photoUrls));
    }

    @Test
    public void GetInvalidIdTest() {
        RestAssured.
                when().get(" https://petstore.swagger.io/v2/pet/abcd").
                then().assertThat().statusCode(404).
                body("type", equalTo("unknown"),
                        "message", equalTo("java.lang.NumberFormatException: For input string: \"abcd\""));
    }

    @Test
    public void GetIncorrectIdTest() {
        RestAssured.
                when().get(" https://petstore.swagger.io/v2/pet/55555555555555555555555555555").
                then().assertThat().statusCode(404).
                body("type", equalTo("unknown"),
                        "message", equalTo("java.lang.NumberFormatException: For input string: \"55555555555555555555555555555\""));
    }
    @After
    public void afterTest() {
        RestAssured.reset();
    }

}
