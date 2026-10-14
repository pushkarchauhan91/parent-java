package com.company.resource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cache.CacheManager;

import com.company.entity.Product;
import com.company.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

// Runs the real app (Jetty + Hazelcast cache) against in-memory H2 instead of MySQL
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
		"spring.datasource.url=jdbc:h2:mem:productdb",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class ProductRestControllerTest {

	private final HttpClient http = HttpClient.newHttpClient();

	@LocalServerPort
	private int port;

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CacheManager cacheManager;

	@Test
	void createThenGetProduct() throws Exception {
		HttpResponse<String> created = send(HttpRequest.newBuilder(uri("/products/"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(
						"{\"name\":\"Laptop\",\"description\":\"Dell\",\"price\":75000}")));

		assertEquals(201, created.statusCode());
		Product saved = repository.findAll().stream()
				.filter(p -> p.getName().equals("Laptop")).findFirst().orElseThrow();

		HttpResponse<String> fetched = send(HttpRequest.newBuilder(uri("/products/" + saved.getId())).GET());

		assertEquals(200, fetched.statusCode());
		assertTrue(fetched.body().contains("\"name\":\"Laptop\""));
	}

	@Test
	void getProductIsCached() throws Exception {
		Product product = new Product();
		product.setName("Mouse");
		product.setPrice(1200);
		int id = repository.save(product).getId();

		send(HttpRequest.newBuilder(uri("/products/" + id)).GET());

		assertNotNull(cacheManager.getCache("product-cache").get(id));
	}

	@Test
	void getMissingProductReturns404() throws Exception {
		HttpResponse<String> response = send(HttpRequest.newBuilder(uri("/products/999999")).GET());

		assertEquals(404, response.statusCode());
	}

	@Test
	void customHealthIndicatorReportsUp() throws Exception {
		HttpResponse<String> response = send(HttpRequest.newBuilder(uri("/actuator/health")).GET());

		assertEquals(200, response.statusCode());
		assertTrue(response.body().contains("\"status\":\"UP\""));
	}

	private URI uri(String path) {
		return URI.create("http://localhost:" + port + "/productapi" + path);
	}

	private HttpResponse<String> send(HttpRequest.Builder request) throws Exception {
		return http.send(request.build(), HttpResponse.BodyHandlers.ofString());
	}
}
