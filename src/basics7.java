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

public class basics7 {

    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException {
        FileInputStream fis=new FileInputStream("C:\\Users\\levs.blinnikovs\\IdeaProjects\\rest-api-test\\src\\files\\env.properties");
        prop.load(fis);
    }

    @Test
    public void JiraAPI()
    {

        //Create session
        RestAssured.baseURI="http://localhost:8080";
        Response res=given().header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID="+reusableMethods.getSessionKey()).
                body("{\n" +
                        "    \"body\": \"Comment added by TestNG RestAssured API.\",\n" +
                        "    \"visibility\": {\n" +
                        "        \"type\": \"role\",\n" +
                        "        \"value\": \"Administrators\"\n" +
                        "    }\n" +
                        "}").
                when().
                post("rest/api/2/issue/10010/comment").
                then().
                statusCode(201).
                extract().response();

                JsonPath js=reusableMethods.rawToJson(res);
                String body=js.get("body");
                System.out.println(body);

                String id=js.get("id"); //comment id
                System.out.println(id+" is comment id");
    }


}
