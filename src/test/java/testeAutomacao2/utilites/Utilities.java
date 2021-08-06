package testeAutomacao2.utilites;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class Utilities {

	 public static RequestSpecification Request;

	    public Utilities(){
	        //Arrange
	        RequestSpecBuilder builder = new RequestSpecBuilder();
	        builder.setContentType(ContentType.JSON);
	        RequestSpecification requestSpec = builder.build();
	        Request = RestAssured.given().spec(requestSpec);
	    }

	    public static ResponseOptions<Response> GetOpsWithPathParameter(String url, Map<String, String> pathParams) {
	        Request.pathParams(pathParams);
	        try {
	            return Request.get(url, pathParams);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static ResponseOptions<Response> GetOps(String url){
	        try {
	            return Request.get(new URI(url));
	        } catch (URISyntaxException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	
	    public static ResponseOptions<Response> PostOpsWithBody(String url,Map<String, String> body)  {
	        Request.body(body);
	        try {
	        	return Request.post(new URI(url));
	        } catch (URISyntaxException e) {
	            e.printStackTrace();
	        }
	        return null;
	        
	    }


}
