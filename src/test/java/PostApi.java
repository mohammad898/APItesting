import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostApi {


    @Test
    public void postmethod(){

        RestAssured.baseURI ="http://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();
        // JSONObject is a class that represents a Simple JSON.
// We can add Key - Value pairs using the put method
        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Virender");
        requestParams.put("LastName", "Singh");

        requestParams.put("UserName", "simpleuser001");
        requestParams.put("Password", "password1");
        requestParams.put("Email",  "someuser@gmail.com");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

// Add the Json to the body of the request
        request.body(requestParams.toJSONString());

// Post the request and check the response
        Response response = request.post("/register");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, "201");
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");

    }

    @Test
    public void AuthenticationBasics()
    {
        RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
        RequestSpecification request = RestAssured.given();

        Response response = request.get();
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Status message " + response.body().asString());
    }
}
