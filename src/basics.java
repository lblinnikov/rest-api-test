import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import files.resources;
import files.payLoad;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class basics {

    Properties prop=new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\levs.blinnikovs\\IdeaProjects\\rest-api-test\\src\\files\\env.properties");
        prop.load(fis);

        //prop.get("HOST")
    }

    @Test
    public void Test() {
        // TODO Auto-generated method stub

        //BaseURL or host
        RestAssured.baseURI=prop.getProperty("HOST");

        given().  // in given(). block - what do we do to hit a request
                param("location", "56.9711614,23.8489883").
                param("radius","500").
                queryParam("key",prop.getProperty("KEY")).log().all().
                when().
                get(resources.placeGetData()).
                // assertions go here
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].name",equalTo("Jūrmala")).and().
                body("results[0].place_id",equalTo("ChIJ0wSh6Afd7kYR3xZsbFh-U3g")).and().
                header("Server","pablo");

                //header("dasda","adasd").
                //cookie("asdasd","asdasd23")

    }
}
