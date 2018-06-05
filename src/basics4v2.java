import files.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class basics4v2 {

    @Test
    public void postData() throws IOException {

        String postdata=GenerateStringFromResource("C:\\Users\\levs.blinnikovs\\Documents\\postdata.xml");
        RestAssured.baseURI="https://maps.googleapis.com";
        Response resp=given().
                queryParam("key","AIzaSyBFJ18DUxMoPEusSJ48nE_gwnckz4zrt4U").
                body(postdata).
                when().
                post("/maps/api/place/add/xml").
                then().assertThat().statusCode(200).and().contentType(ContentType.XML).
                extract().response();


                XmlPath x=reusableMethods.rawToXML(resp);
                System.out.println((String)x.get("PlaceAddResponse.place_id"));



        //Create a place and delete it
        //Create a place = response (place_id)
        //delete that Place = Request (place_id)


    }

    public static String GenerateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
