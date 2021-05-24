import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestnaKlasaOOP
{


    @Test
    public void apiGET()
    {
        RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1/";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, "/employees");

        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
        System.out.println("status code:"+response.getStatusCode());
        System.out.println("Does Response contains 'Tiger Nixon'? :" + response.asString().contains("Tiger Nixon"));
        Assert.assertEquals("Status code is not 200",200,response.getStatusCode());
    }



    @Test
    public void apiPOST()
    {
        RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1/";
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "velja");
        requestParams.put("salary",  "50000");
        requestParams.put("age",  "43");
        System.out.println(requestParams.toString());

        RequestSpecification request = given();
        request.body(request.toString());
        request.contentType("application/json");
        try
        {
            Response response = request.post("/create");
            System.out.println(response.asString());
            Assert.assertEquals(200,response.getStatusCode());
            Assert.assertTrue(response.asString().contains("Successfully!  has been added."));
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
