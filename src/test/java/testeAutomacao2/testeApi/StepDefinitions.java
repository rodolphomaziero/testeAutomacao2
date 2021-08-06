package testeAutomacao2.testeApi;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import testeAutomacao2.utilites.Utilities;
;


public class StepDefinitions {

	static Utilities utilities ;
	
    private static ResponseOptions<Response> response;

	@Before
	public void TestSetup(){
		utilities = new Utilities();
	}
	
	@Given("realizo um {string} para a url {string} com parametros")
	public void realizo_um_get_para_a_url(String metodo, String url, DataTable  table) {
		switch (metodo) {
		case "GET":
			if(!table.cell(0, 0).equalsIgnoreCase("null")) {
				Map<String, String> params = new HashMap<String, String>();
				for(int i =0; i < table.width(); i++) {
					params.put( table.cell(0, i), table.cell(1, i));
				}
				
				response = Utilities.GetOpsWithPathParameter(url,params);
			}else {
				response = Utilities.GetOps(url);
			}
		break;
		case "POST":
			Map<String, String> params = new HashMap<String, String>();
			for(int i =0; i < table.width(); i++) {
				params.put( table.cell(0, i), table.cell(1, i));
			}
			response = Utilities.PostOpsWithBody(url, params);
		break;
		}
		 
	}
	
	@Then("recebo uma lista de empregados com status code {int}")
	public void recebo_uma_lista_de_empregado_com_status_code(int status) {
//		System.out.println(response.statusCode());
//		System.out.println(response.getBody().prettyPrint());
		try {
			Assert.assertEquals(status, response.getStatusCode());
			Assert.assertFalse(response.getBody().jsonPath().getList("data").isEmpty());
		}catch (Exception e) {
//			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Then("recebo status code {int}")
	public void recebo_status_code(int status) {	
//		System.out.println(response.statusCode());
//		System.out.println(response.getBody().prettyPrint());
		try {
			Assert.assertEquals(status, response.getStatusCode());
		}catch (Exception e) {
//			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Then("recebo um empregado com id {int} e com status code {int}")
	public void recebo_um_empregado_com_id_e_com_status_code(int idObject, int status) {
//		System.out.println(response.statusCode());
//		System.out.println(response.getBody().prettyPrint());
		try {
			Assert.assertEquals(status, response.getStatusCode());
			Assert.assertEquals(idObject,  response.getBody().jsonPath().getMap("data").get("id"));
		}catch (Exception e) {
//			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@Then("recebo um empregado nulo e com status code {int}")
	public void recebo_um_empregado_nulo_e_com_status_code(int status) {		
//		System.out.println(response.statusCode());
//		System.out.println(response.getBody().prettyPrint());
		try {
			Assert.assertEquals(status, response.getStatusCode());
			Assert.assertNull(response.getBody().jsonPath().get("data"));
		}catch (Exception e) {
//			e.printStackTrace();
			Assert.fail();
		}
	}
}
