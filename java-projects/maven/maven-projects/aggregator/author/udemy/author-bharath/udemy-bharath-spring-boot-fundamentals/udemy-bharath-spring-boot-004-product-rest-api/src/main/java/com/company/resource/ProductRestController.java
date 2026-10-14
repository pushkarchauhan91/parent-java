package com.company.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.company.entity.Product;
import com.company.repository.ProductRepository;

@RestController
public class ProductRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRestController.class);

	private final ProductRepository repository;

	public ProductRestController(ProductRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/products/")
	public List<Product> getProducts() {
		return repository.findAll();
	}

	@GetMapping("/products/{id}")
	@Transactional(readOnly = true)
	@Cacheable("product-cache")
	public Product getProduct(@PathVariable int id) {
		LOGGER.info("Finding product by ID: {}", id);
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product " + id + " not found"));
	}

	@PostMapping("/products/")
	@ResponseStatus(HttpStatus.CREATED)
	public Product createProduct(@RequestBody Product product) {
		return repository.save(product);
	}

	@PutMapping("/products/")
	@CacheEvict(value = "product-cache", key = "#product.id")
	public Product updateProduct(@RequestBody Product product) {
		return repository.save(product);
	}

	@DeleteMapping("/products/{id}")
	@CacheEvict("product-cache")
	public void deleteProduct(@PathVariable int id) {
		repository.deleteById(id);
	}

}
