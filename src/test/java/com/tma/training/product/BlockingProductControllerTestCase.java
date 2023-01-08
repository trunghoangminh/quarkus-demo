package com.tma.training.product;

import com.tma.training.product.entity.Product;
import com.tma.training.product.rest.CreateProduct;
import com.tma.training.product.rest.GetProduct;
import com.tma.training.product.rest.ListProduct;
import com.tma.training.product.rest.UpdateProduct;
import com.tma.training.product.util.JsonParser;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestHTTPEndpoint(BlockingProductController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class BlockingProductControllerTestCase {

    private String productId = null;

    @BeforeEach
    public void init() {
        Product laptop = new Product();
        laptop.setName("Macbook M1");
        laptop.setDescription("Laptop with Chip Apple Silicon");
        laptop.setManufacturer("Apple");
        laptop.setPrice(new BigDecimal(41_000_00));
        laptop.persist();
        productId = laptop.getId().toHexString();

        Product ipad = new Product();
        ipad.setName("IPad M1");
        ipad.setDescription("IPad with Chip Apple Silicon");
        ipad.setManufacturer("Apple");
        ipad.setPrice(new BigDecimal(20_000_00));
        ipad.persist();

        Product iphone = new Product();
        iphone.setName("Iphone 14");
        iphone.setDescription("Support dynamic island");
        iphone.setManufacturer("Apple");
        iphone.setPrice(new BigDecimal(34_000_00));
        iphone.persist();
    }

    /**
     * Test API GET | /api/v1/product/blocking
     */
    @Test
    public void testGetAllProduct() {
        JsonPath jsonPath = given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("code", is("0000")).extract().jsonPath();
        List<ListProduct.ResponseVO> responses = JsonParser.convert(jsonPath.getJsonObject("data"), List.class);
        assertNotNull(responses);
        assertEquals(3, responses.size());
    }

    /**
     * Test API GET | /api/v1/product/non-blocking/{productId}
     */
    @Test
    public void testGetProduct() {
        JsonPath jsonPath = given()
                .when()
                .get("/{productId}", productId)
                .then()
                .statusCode(200)
                .body("code", is("0000")).extract().jsonPath();
        GetProduct.ResponseVO response = JsonParser.convert(jsonPath.getJsonObject("data"), GetProduct.ResponseVO.class);
        assertNotNull(response);
        assertEquals("Macbook M1", response.getName());
        assertEquals("Laptop with Chip Apple Silicon", response.getDescription());
        assertEquals("Apple", response.getManufacturer());
        assertEquals(new BigDecimal(41_000_00), response.getPrice());
    }

    /**
     * Test API POST | /api/v1/product/blocking
     */
    @Test
    public void testCreateProduct() {
        String name = "Macbook M2";
        String description = "Laptop with Chip Apple Silicon";
        String manufacturer = "Apple";
        BigDecimal price = new BigDecimal(50_000_00);

        CreateProduct.RequestVO body = new CreateProduct.RequestVO();
        body.setName(name);
        body.setDescription(description);
        body.setManufacturer(manufacturer);
        body.setPrice(price);

        JsonPath jsonPath = given()
                .when()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(body)
                .post()
                .then()
                .statusCode(200)
                .body("code", is("0000")).extract().jsonPath();
        CreateProduct.ResponseVO response = JsonParser.convert(jsonPath.getJsonObject("data"), CreateProduct.ResponseVO.class);

        assertNotNull(response);
        assertEquals(name, response.getName());
        assertEquals(description, response.getDescription());
        assertEquals(manufacturer, response.getManufacturer());
        assertEquals(price, response.getPrice());
    }

    /**
     * Test API PATCH | /api/v1/product/blocking/{productId}
     */
    @Test
    public void testUpdateProduct() {
        String name = "Macbook M2 Updated";
        String description = "Laptop with Chip Apple Silicon Updated";
        String manufacturer = "Apple Updated";
        BigDecimal price = new BigDecimal(60_000_00);

        CreateProduct.RequestVO body = new CreateProduct.RequestVO();
        body.setName(name);
        body.setDescription(description);
        body.setManufacturer(manufacturer);
        body.setPrice(price);

        JsonPath jsonPath = given()
                .when()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(body)
                .patch("/{productId}", productId)
                .then()
                .statusCode(200)
                .body("code", is("0000")).extract().jsonPath();
        UpdateProduct.ResponseVO response = JsonParser.convert(jsonPath.getJsonObject("data"), UpdateProduct.ResponseVO.class);

        assertNotNull(response);
        assertEquals(name, response.getName());
        assertEquals(description, response.getDescription());
        assertEquals(manufacturer, response.getManufacturer());
        assertEquals(price, response.getPrice());
    }

    /**
     * Test API DELETE | /api/v1/product/blocking/{productId}
     */
    @Test
    public void testDeleteProduct() {
        JsonPath jsonPath = given()
                .when()
                .delete("/{productId}", productId)
                .then()
                .statusCode(200)
                .body("code", is("0000")).extract().jsonPath();
        String response = JsonParser.convert(jsonPath.getJsonObject("data"), String.class);
        assertNotNull(response);
        assertNotNull("Success", response);
    }

    @AfterEach
    public void tearDown() {
        Product.deleteAll();
    }
}
