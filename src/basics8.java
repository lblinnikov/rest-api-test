import files.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class basics8 {

    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\levs.blinnikovs\\IdeaProjects\\rest-api-test\\src\\files\\env.properties");
        prop.load(fis);
    }

    @Test
    public void JiraAPI() {

        //Create session
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + reusableMethods.getSessionKey()).
                body("{\n" +
                        "    \"body\": \"Comment from REST API. Updated.\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").
                when().
                put("rest/api/2/issue/10010/comment/10004").
                then().
                statusCode(200).
                extract().response();

                JsonPath js = reusableMethods.rawToJson(res);


    }
}