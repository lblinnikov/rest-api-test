import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.resources;
import files.payLoad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class basics3 {

    Properties prop=new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\levs.blinnikovs\\IdeaProjects\\rest-api-test\\src\\files\\env.properties");
        prop.load(fis);

        //prop.get("HOST")
    }

    @Test
    public void addAndDeletePlace(){

        RestAssured.baseURI=prop.getProperty("HOST");

        Response response=given().
                queryParam("key",prop.getProperty("KEY")).
                body(payLoad.getPostData()).
                when().
                post(resources.placePostData()).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).
                extract().
                response();

                String responseString = response.asString();
                System.out.println(responseString);

                JsonPath js = new JsonPath(responseString); // convert responseString to Json

                // Grab place_id from response
                String placeid = js.get("place_id");
                System.out.println(placeid);

                // Task 3 place id to post request to delete place id
                given().
                        queryParam("key",prop.getProperty("KEY")).
                        body("{\n" +
                                "  \"place_id\": \""+placeid+"\"\n" +
                                "}").

                        when().
                        post(resources.placeDeleteData()).
                        then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));

    }
}
