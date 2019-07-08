package definitions;

import com.jayway.jsonpath.JsonPath;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import pojos.Client;
import pojos.Invoice;
import pojos.Item;
import pojos.World;

public class GeneralStepDefinitions {
    private World world;

    public GeneralStepDefinitions(World world) {
        this.world = world;
    }


    @Before
    public void configureDefaults() {
        RestAssured.baseURI = "https://st2016.inv.bg";
        RestAssured.basePath = "/RESTapi";
        world.requestSpecification = RestAssured.given().auth().preemptive().basic("karamfilovs@gmail.com", "123456").log().all();
    }

    @And("I execute GET request at URL: {string}")
    public void iExecuteGETRequestAt(String uri) {
        world.response = world.requestSpecification.get(uri);
        world.response.prettyPrint();
    }

    @Then("status code should be {int}")
    public void statusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode, world.response.getStatusCode());
    }

    @When("I set item properties firm_name: {string} price_for_quantity: {int} quantity_unit: {int}")
    public void iSetItemPropertiesNamePriceQuantity(String name, int price, int quantity) {
        world.item = Item.builder().build();
    }

    @And("I execute POST request at URL: {string}")
    public void iExecutePOSTRequestAt(String uri) {
        world.response = world.requestSpecification.post(uri);
        world.response.prettyPrint();
    }

    @Given("I set content type to Json")
    public void iSetContentTypeToJson() {
        world.requestSpecification.contentType(ContentType.JSON);
    }

    @When("I start building new Item")
    public void iStartBuildingNewItem() {
        world.item = Item.builder().build();
    }


    @When("I start building new Client")
    public void iStartBuildingNewClient() {
        world.client = Client.builder().build();
    }

    @And("I set item firm_name {string}")
    public void iSetItemName(String name) {
        world.item.setName(name);
    }

    @And("I set client firm_name {string}")
    public void iSetClientName(String name) {
        world.client.setFirm_name(name);
    }

    @And("I set item price_for_quantity {string}")
    public void iSetItemPrice(String price) {
        world.item.setPrice_for_quantity(price);
    }

    @And("I set item quantity_unit {string}")
    public void iSetItemQuantity(String quantity) {
        world.item.setQuantity_unit(quantity);

    }

    @And("I set the current item as body")
    public void iSetTheCurrentItemAsBody() {
        world.requestSpecification.body(world.item);
    }

    @And("I set the current client as body")
    public void iSetTheCurrentClientAsBody() {
        world.requestSpecification.body(world.client);
    }

    @When("I store the id from the last response at path {string}")
    public void iStoreTheIdFromTheLastResponseAtPath(String path) {
        world.id = JsonPath.read(world.response.getBody().asString(), path).toString();
    }

    @And("I get the last created entity at path {string}")
    public void iGetTheLastCreatedEntity(String path) {
        world.requestSpecification.get(path + "/" + world.id);
    }


    @When("I delete the last created entity at path {string}")
    public void iDeleteTheLastCreatedEntityAtPath(String path) {
        world.response = world.requestSpecification.delete(path + "/" + world.id);
        world.response.prettyPrint();
    }

    @And("I set client city {string}")
    public void iSetClientCity(String city) {
        world.client.setFirm_town(city);

    }

    @And("I set client address {string}")
    public void iSetClientAddress(String address) {
        world.client.setFirm_addr(address);
    }

    @And("I set client vat registration to {string}")
    public void iSetClientVatRegistrationTo(String isRegistered) {
        world.client.setFirm_is_reg_vat(isRegistered);
    }

    @When("I start building new Invoice")
    public void iStartBuildingNewInvoice() {
        world.invoice = Invoice.builder().build();
    }


    @And("I set item name {string}")
    public void iSetItemTheName(String name) {
        world.item.setName(name);
    }

    @And("I set client mol to {string}")
    public void iSetClientMolTo(String mol) {
        world.client.setFirm_mol(mol);
    }

    @And("value found at path {string} should be {string}")
    public void valueFoundAtPathShouldBe(String path, String value) {
        world.value = JsonPath.read(world.response.getBody().asString(), path).toString();
        Assert.assertEquals(value,world.value);
    }
}
