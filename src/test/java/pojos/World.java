package pojos;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class World {
    public RequestSpecification requestSpecification;
    public Response response;
    public Item item;
    public Client client;
    public String id;
}
