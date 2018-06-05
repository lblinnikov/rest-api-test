import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import files.reusableMethods;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class basics6 {

    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\levs.blinnikovs\\IdeaProjects\\rest-api-test\\src\\files\\env.properties");
        prop.load(fis);
    }

    @Test
    public void JiraAPI() // assignment - put it to reusableMethods!
    {

        //Create session
        RestAssured.baseURI="http://localhost:8080";
        Response res=given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID="+reusableMethods.getSessionKey()).
                body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       { \n" +
                        "          \"key\": \"RES\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Debitcard defect\",\n" +
                        "       \"description\": \"Creating of 2nd bug using the REST API\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}").
                when().
                post("rest/api/2/issue").
                then().
                statusCode(201).
                extract().response();

                JsonPath js=reusableMethods.rawToJson(res);
                String id=js.get("id");
                System.out.println(id);
                //return id; // because used to dynamically populate id of issue value in basics7
    }


}
