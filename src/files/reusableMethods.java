package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class reusableMethods {

    public static XmlPath rawToXML(Response r) {
        String respon=r.asString();
        XmlPath x=new XmlPath(respon);
        return x;
    }

    public static JsonPath rawToJson(Response r) {
        String respon=r.asString();
        JsonPath x=new JsonPath(respon);
        return x;
    }

    public static String getSessionKey()
    {
        RestAssured.baseURI="http://localhost:8080";
        Response res=given().
                header("Content-Type", "application/json").
                body("{ \"username\": \"lblinnikov\", \"password\": \"Retailer01\" }").
                when().
                post("/rest/auth/1/session").
                then().
                statusCode(200).
                extract().
                response(); // we get response in json, we need to transform to JSON

        JsonPath js=reusableMethods.rawToJson(res);

        // backup plan - also works
        // String sessionid=js.get("session.value");
        // return sessionid;

        return js.get("session.value"); // smarter way

    }
}
