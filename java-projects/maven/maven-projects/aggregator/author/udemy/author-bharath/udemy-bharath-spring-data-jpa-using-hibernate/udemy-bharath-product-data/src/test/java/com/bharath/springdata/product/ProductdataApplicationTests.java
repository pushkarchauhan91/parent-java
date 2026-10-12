
package com.bharath.springdata.product;

import com.bharath.springdata.product.entities.Product;
import com.bharath.springdata.product.repos.ProductRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProductdataApplicationTests {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private EntityManager entityManager;

	@BeforeEach
	void setUp() {
		repository.deleteAll();
		repository.flush();

		repository.saveAllAndFlush(List.of(
				createProduct(1, "Iphone", "Apple smartphone", 1000d),
				createProduct(2, "IWatch", "Apple smartwatch", 1500d),
				createProduct(3, "TV", "From Samsung Inc", 2000d),
				createProduct(4, "Monitor", "LG display", 800d)
		));

		entityManager.clear();
	}

	private Product createProduct(int id, String name,
								  String desc, Double price) {
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setDesc(desc);
		product.setPrice(price);
		return product;
	}

	@Test
	void contextLoads() {
		assertThat(repository).isNotNull();
	}

	@Test
	void testCreate() {
		Product product = createProduct(
				5, "MacBook", "Apple laptop", 2500d);

		Product saved = repository.saveAndFlush(product);

		assertThat(saved.getId()).isEqualTo(5);
		assertThat(repository.existsById(5)).isTrue();
	}

	@Test
	void testRead() {
		Product product = repository.findById(1).orElseThrow();

		assertThat(product).isNotNull();
		assertThat(product.getName()).isEqualTo("Iphone");
		assertThat(product.getDesc()).isEqualTo("Apple smartphone");
	}

	@Test
	void testUpdate() {
		Product product = repository.findById(1).orElseThrow();
		product.setPrice(1200d);

		repository.saveAndFlush(product);
		entityManager.clear();

		Product updated = repository.findById(1).orElseThrow();

		assertThat(updated.getPrice()).isEqualTo(1200d);
	}

	@Test
	void testDelete() {
		repository.deleteById(1);
		repository.flush();

		assertThat(repository.existsById(1)).isFalse();
	}

	@Test
	void testCount() {
		assertThat(repository.count()).isEqualTo(4);
	}

	@Test
	void testFindByName() {
		List<Product> products = repository.findByName("IWatch");

		assertThat(products)
				.hasSize(1)
				.extracting(Product::getName)
				.containsExactly("IWatch");
	}

	@Test
	void testFindByNameAndDesc() {
		List<Product> products =
				repository.findByNameAndDesc(
						"TV", "From Samsung Inc");

		assertThat(products)
				.hasSize(1)
				.extracting(Product::getName)
				.containsExactly("TV");
	}

	@Test
	void testFindByPriceGreaterThan() {
		List<Product> products =
				repository.findByPriceGreaterThan(1000d);

		assertThat(products)
				.extracting(Product::getName)
				.containsExactlyInAnyOrder("IWatch", "TV");
	}

	@Test
	void testFindByDescContains() {
		List<Product> products =
				repository.findByDescContains("Apple");

		assertThat(products)
				.extracting(Product::getName)
				.containsExactlyInAnyOrder("Iphone", "IWatch");
	}

	@Test
	void testFindByPriceBetween() {
		List<Product> products =
				repository.findByPriceBetween(500d, 2500d);

		assertThat(products).hasSize(4);
	}

	@Test
	void testFindByDescLike() {
		List<Product> products =
				repository.findByDescLike("%LG%");

		assertThat(products)
				.hasSize(1)
				.extracting(Product::getName)
				.containsExactly("Monitor");
	}

	@Test
	void testFindByIdsIn() {
		Pageable pageable = PageRequest.of(0, 2);

		List<Product> products =
				repository.findByIdIn(List.of(1, 2, 3), pageable);

		assertThat(products).hasSize(2);
		assertThat(products)
				.extracting(Product::getId)
				.allMatch(id -> List.of(1, 2, 3).contains(id));
	}

	@Test
	void testFindAllPaging() {
		Pageable pageable = PageRequest.of(0, 2);

		var results = repository.findAll(pageable);

		assertThat(results.getContent()).hasSize(2);
		assertThat(results.getTotalElements()).isEqualTo(4);
		assertThat(results.getTotalPages()).isEqualTo(2);
	}

	@Test
	void testFindAllSorting() {
		Sort sort = Sort.by(
				Sort.Order.desc("name"),
				Sort.Order.asc("price")
		);

		List<Product> products = repository.findAll(sort);

		assertThat(products)
				.extracting(Product::getName)
				.containsExactly("TV", "Monitor", "IWatch", "Iphone");
	}

	@Test
	void testFindAllPagingAndSorting() {
		Pageable pageable = PageRequest.of(
				0, 2, Sort.Direction.DESC, "name");

		var results = repository.findAll(pageable);

		assertThat(results.getContent())
				.extracting(Product::getName)
				.containsExactly("TV", "Monitor");
	}

	@Test
	void testCaching() {
		Product first = repository.findById(1).orElseThrow();
		Product second = repository.findById(1).orElseThrow();

		// Both queries use the same persistence context.
		assertThat(second).isSameAs(first);

		// Remove entity from the first-level cache.
		entityManager.detach(first);

		assertThat(entityManager.contains(first)).isFalse();

		Product third = repository.findById(1).orElseThrow();

		assertThat(third).isNotSameAs(first);
		assertThat(third.getId()).isEqualTo(first.getId());
	}
}
